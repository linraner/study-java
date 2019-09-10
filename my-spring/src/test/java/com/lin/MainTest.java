package com.lin;

import com.lin.bean.BeanDefinition.impl.DefaultBeanDefinition;
import com.lin.bean.User;
import com.lin.bean.factory.impl.DefaultBeanFactory;
import org.junit.Test;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 17:26
 **/
public class MainTest {
    static DefaultBeanFactory factory = new DefaultBeanFactory();

    @Test
    public void test() throws Exception {
        DefaultBeanDefinition bd = new DefaultBeanDefinition();
        // 加载字节码
        bd.setClazz(User.class);
        bd.setSingleton(true);
        bd.setBeanFactoryName("TestFactory");
        bd.setStaticCreateBeanMethodName("createMethod");
        bd.setStaticCreateBeanMethodName("staticCreateMethod");

        List<Object> args = new LinkedList<>();
        args.add("lin");
        args.add(22);
        bd.setConstructorArg(args);
        bd.setBeanInitMethodName("init");

        Map<String, Object> values = new HashMap<>();
        values.put("name", "李白");
        bd.setPropertyKeyValue(values);
        factory.register(bd, "user");

        System.out.println(factory.doGetBean("user"));
    }

}
