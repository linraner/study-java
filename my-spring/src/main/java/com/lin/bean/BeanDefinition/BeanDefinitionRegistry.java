package com.lin.bean.BeanDefinition;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 17:00
 * @Description:
 **/
public interface BeanDefinitionRegistry {
    /**
     * 注册bean到bean工厂
     * @param bd
     * @param beanName
     */
    void register(BeanDefinition bd, String beanName);

    boolean containsBeanDefinition(String beanName);

    BeanDefinition getBeanDefinition(String beanName);
}
