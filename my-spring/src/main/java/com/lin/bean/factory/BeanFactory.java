package com.lin.bean.factory;

import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 18:07
 **/
public interface BeanFactory {

  Object getBean(String beanName) throws Exception;

  String[] getBeanNameForType(Class<?> clazz);

  Map<String, Object> getBeansForType(Class<?> clazz);

  Class getType(String beanName);


}
