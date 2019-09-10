package com.lin.bean.factory.impl;

import com.lin.bean.BeanDefinition.BeanDefinition;
import com.lin.bean.BeanDefinition.BeanDefinitionRegistry;
import com.lin.bean.beanreference.BeanReference;
import com.lin.bean.factory.BeanFactory;
import org.apache.commons.lang3.StringUtils;
import org.apache.commons.logging.Log;
import org.apache.commons.logging.LogFactory;
import org.junit.Assert;

import java.io.Closeable;
import java.io.IOException;
import java.lang.reflect.Constructor;
import java.lang.reflect.Field;
import java.lang.reflect.Method;
import java.util.*;
import java.util.concurrent.ConcurrentHashMap;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 17:02
 * @Description: bean工厂实现
 * 实现的closeable接口用于销毁对象
 **/
public class DefaultBeanFactory implements BeanFactory, BeanDefinitionRegistry, Closeable {
    private Log logger = LogFactory.getLog(DefaultBeanFactory.class);
    // ConcurrentHashMap 并发
    private Map<String, BeanDefinition> bdMap = new ConcurrentHashMap<>();

    private Map<String, Object> beanMap = new ConcurrentHashMap<>();

    // 记录正在创建的bean
    private ThreadLocal<Set<String>> initiableBeans = new ThreadLocal<>();

    // 记录观察者
//    private List

    @Override
    public void register(BeanDefinition bd, String beanName) {
        Assert.assertNotNull("BeanDefinition不能为空 ", bd);
        Assert.assertNotNull("beanName不能为空 beanName", beanName);

        if (bdMap.containsKey(beanName)) {
            logger.info("[" + beanName + "]已经存在");
        }

        if (!bd.validate()) {
            logger.error("BeanDefinition不合法");
            return;
        }
        bdMap.put(beanName, bd);
    }

    @Override
    public boolean containsBeanDefinition(String beanName) {
        return bdMap.containsKey(beanName);
    }

    @Override
    public BeanDefinition getBeanDefinition(String beanName) {
        return bdMap.getOrDefault(beanName, null);
    }

    @Override
    public Object getBean(String beanName) throws Exception {
        return doGetBean(beanName);
    }

    @Override
    public String[] getBeanNameForType(Class<?> clazz) {
        // TODO
        return new String[0];
    }

    @Override
    public Map<String, Object> getBeansForType(Class<?> clazz) {
        // TODO
        return null;
    }

    @Override
    public Class getType(String beanName) {
        Object o = beanMap.get(beanName);
        return ((BeanDefinition) o).getBeanClass();
    }

    @Override
    public void close() throws IOException {
        Set<Map.Entry<String, BeanDefinition>> entries = bdMap.entrySet();
        for (Map.Entry<String, BeanDefinition> entry : entries) {
            BeanDefinition value = entry.getValue();
            String destoryMethodName = value.getBeanDestoryMethodName();
            try {
                Method method = value.getBeanClass().getMethod(destoryMethodName, null);
                method.invoke(value.getBeanClass(), null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }

    }

    /**
     * 获取bean
     *
     * @param beanName
     * @return
     * @throws Exception
     */
    public Object doGetBean(String beanName) throws Exception {
        if (!beanMap.containsKey(beanName)) {
            logger.info("[beanName: " + beanName + "]不存在");
        }

        // 记录正在创建的bean
        Set<String> beans = this.initiableBeans.get();
        if (beans == null) {
            beans = new HashSet<>();
            this.initiableBeans.set(beans);
        }

        //检查循环依赖
        if (beans.contains(beanName)) {
            throw new Exception("检测到" + beanName + "存在循环依赖：" + beans);
        }
        // 记录
        beans.add(beanName);
        Object intsance = beanMap.get(beanName);
        if (intsance != null) {
            return intsance;
        }
        // 不存在 进行创建 FIX:
        if (!this.bdMap.containsKey(beanName) || this.bdMap.get(beanName) == null) {
            logger.info("不存在名为：[" + beanName + "]的bean定义,即将进行创建");
        }
        BeanDefinition bd = this.bdMap.get(beanName);

        Class<?> beanClass = bd.getBeanClass();
        if (beanClass != null) {
            intsance = createBeanByConstruct(bd);
            if (intsance == null) {
                intsance = createBeanByStaticFactoryMethod(bd);
            }
        } else if (intsance == null && StringUtils.isNotBlank(bd.getStaticCreateBeanMethod())) {
            intsance = createBeanByStaticFactoryMethod(bd);
        }
        this.doInit(bd, intsance);

        // 添加属性依赖
        this.parsePropertyValues(bd, intsance);
        // 创建完成 移除bean记录
        beans.remove(beanName);

        // TODO aop处理

        if (intsance != null && bd.isSingleton()) {
            beanMap.put(beanName, intsance);
        }
        return intsance;
    }

    /**
     * 构造初始化
     *
     * @param bd
     * @param instance
     */
    private void doInit(BeanDefinition bd, Object instance) {
        Class<?> beanClass = instance.getClass();
        if (StringUtils.isNotBlank(bd.getBeanInitMethodName())) {
            try {
                Method method = beanClass.getMethod(bd.getBeanInitMethodName(), null);
                method.invoke(instance, null);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
    }

    /**
     * 构造方法创建实例
     *
     * @param bd
     * @return
     */
    private Object createBeanByConstruct(BeanDefinition bd) {
        Object instance = null;
        try {
            // 构造参数列表
            List<?> constructorArg = bd.getConstructorArg();
            Object[] objects = parseConstructorArgs(constructorArg);
            // 匹配构造参数
            Constructor<?> constructor = matchConstructor(bd, objects);
            if (constructor != null) {
                instance = constructor.newInstance(objects);
            } else {
                instance = bd.getBeanClass().newInstance();
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 解析构造参数
     *
     * @param constructorArgs
     * @return
     * @throws Exception
     */
    private Object[] parseConstructorArgs(List constructorArgs) throws Exception {
        if (constructorArgs == null || constructorArgs.size() == 0) {
            return null;
        }
        Object[] args = new Object[constructorArgs.size()];
        for (int i = 0; i < constructorArgs.size(); i++) {
            Object arg = constructorArgs.get(i);
            Object value = null;
            if (arg instanceof BeanReference) {
                String beanName = ((BeanReference) arg).getBeanName();
                value = this.doGetBean(beanName);
            } else if (arg instanceof List) {
                value = parseListArg((List) arg);
            } else if (arg instanceof Map) {
                //todo 处理map
            } else if (arg instanceof Properties) {
                //todo 处理属性文件
            } else {
                value = arg;
            }
            args[i] = value;

        }
        return args;
    }

    /**
     * @param arg
     * @return
     * @throws Exception
     */
    private List parseListArg(List arg) throws Exception {
        List param = new LinkedList();
        for (Object value : arg) {
            Object res = new Object();
            if (arg instanceof BeanReference) {
                String beanName = ((BeanReference) value).getBeanName();
                res = this.doGetBean(beanName);
            } else if (arg instanceof List) {
                // 递归处理list
                res = parseListArg(arg);
            } else if (arg instanceof Map) {
                // TODO
            } else if (arg instanceof Properties) {
                res = arg;
            }
            param.add(res);
        }
        return param;
    }

    /**
     * 匹配构造方法
     *
     * @param bd
     * @param args
     * @return
     * @throws Exception
     */
    private Constructor<?> matchConstructor(BeanDefinition bd, Object[] args) throws Exception {
        // 先进行精确匹配 如果能匹配到相应的构造方法 则后续不用进行
        if (args == null) {
            return bd.getBeanClass().getConstructor(null);
        }
        // 如果已经缓存直接返回
        if (bd.getConstructor() != null) {
            return bd.getConstructor();
        }

        int len = args.length;
        Class[] param = new Class[len];
        for (int i = 0; i < len; i++) {
            param[i] = args[i].getClass();
        }
        // 匹配
        Constructor constructor = null;
        try {
            constructor = bd.getBeanClass().getConstructor(param);
        } catch (Exception e) {
            //这里上面的代码如果没匹配到会抛出空指针异常
            //为了代码继续执行 这里我们来捕获 但是不需要做其他任何操作
        }
        if (constructor != null) {
            return constructor;
        }

        // 未匹配到 继续匹配
        List<Constructor> firstFilterAfter = new LinkedList<>();
        Constructor[] constructors = bd.getBeanClass().getConstructors();
        // 按照参数个数匹配
        for (Constructor cons : constructors) {
            if (cons.getParameterCount() == len) {
                firstFilterAfter.add(cons);
            }
        }
        if (firstFilterAfter.size() == 1) {
            return firstFilterAfter.get(0);
        }
        if (firstFilterAfter.size() == 0) {
            throw new Exception("不存在对应的构造函数：" + args);
        }
        // 按照参数类型匹配
        // 获取所有参数类型
        boolean isMatch = true;
        for (int i = 0; i < firstFilterAfter.size(); i++) {
            Class[] types = firstFilterAfter.get(i).getParameterTypes();
            for (int j = 0; j < types.length; j++) {
                if (types[j].isAssignableFrom(args[j].getClass())) {
                    isMatch = false;
                    break;
                }
            }
            if (isMatch) {
                // 对原型bean缓存方法
                if (bd.isPrototype()) {
                    bd.setConstructor(firstFilterAfter.get(i));
                }
                return firstFilterAfter.get(i);
            }
        }
        // 未能匹配到
        throw new Exception("不存在对应的构造函数: " + args);
    }

    private void parsePropertyValues(BeanDefinition bd, Object instance) throws Exception {
        Map<String, Object> propertyKeyValue = bd.getPropertyKeyValue();
        if (propertyKeyValue == null || propertyKeyValue.size() == 0) {
            return;
        }
        Class<?> clazz = instance.getClass();
        Set<Map.Entry<String, Object>> entries = propertyKeyValue.entrySet();
        for (Map.Entry<String, Object> entry : entries) {
            // 获取指定字段信息
            Field field = clazz.getDeclaredField(entry.getKey());
            // 访问权限设置为true
            field.setAccessible(true);
            Object arg = entry.getValue();
            Object value = null;
            if (arg instanceof BeanDefinition) {
                String beanName = ((BeanDefinition) arg).getBeanName();
                value = this.doGetBean(beanName);
            } else if (arg instanceof List) {
                List param = parseListArg((List) arg);
                value = param;
            } else if (arg instanceof Map) {
                // TODO
            } else if (arg instanceof Properties) {
                // TODO
            } else {
                value = arg;
            }
            field.set(instance, value);
        }
    }

    /**
     * 静态方法创建实例
     *
     * @param bd
     * @return
     */
    private Object createBeanByStaticFactoryMethod(BeanDefinition bd) {
        Object instance = null;
        try {
            Class<?> beanClass = bd.getBeanClass();
            Method method = beanClass.getMethod(bd.getStaticCreateBeanMethod());
            instance = method.invoke(beanClass, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

    /**
     * 普通工厂模式创建实例
     *
     * @param bd
     * @return
     */
    private Object createBeanByFactoryMethod(BeanDefinition bd) {
        Object instance = null;
        try {
            // 获取工厂类
            Object factory = doGetBean(bd.getBeanFactory());
            Method method = factory.getClass().getMethod(bd.getCreateBeanMethod());
            instance = method.invoke(factory, null);
        } catch (Exception e) {
            e.printStackTrace();
        }
        return instance;
    }

}
