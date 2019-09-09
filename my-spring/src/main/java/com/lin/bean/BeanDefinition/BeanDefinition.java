package com.lin.bean.BeanDefinition;

import org.apache.commons.lang3.StringUtils;

import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 16:49
 * bean定义接口 提供bean的信息
 **/
public interface BeanDefinition {
    String SINGLETON = "singleton";

    String PROTOTYPE = "prototype";
    /**
     * 获取bean字节码对象
     *
     * @return
     */
    Class<?> getBeanClass();

    String getBeanName();

    /**
     * 获取创建bean工程名
     *
     * @return
     */
    String getBeanFactory();

    String getCreateBeanMethod();

    String getStaticCreateBeanMethod();

    String getBeanInitMethodName();

    String getBeanDestoryMethodName();

    /**
     * 获取bean类型
     *
     * @return
     */
    String getScope();

    boolean isSingleton();

    boolean isPrototype();

    /**
     * 校验bean定义是否正确
     *
     * @return
     */
    default boolean validate() {
        if (getBeanClass() == null) {
            return !StringUtils.isBlank(getBeanFactory()) || !StringUtils.isBlank(getCreateBeanMethod());
        }
        return true;
    }

    /**
     * 传入构造参数
     *
     * @return
     */
    List<?> getConstructorArg();

    // ? 为缓存响应的调用方法
    Constructor<?> getConstructor();

    void setConstructor(Constructor<?> constructor);

    Method getFactoryMethod();

    void setFactoryMethod(Method factoryMethod);

    // 属性依赖
    Map<String, Object> getPropertyKeyValue();

    void setPropertyKeyValue(Map<String, Object> properties);

}
