package com.lin.bean.BeanDefinition.impl;

import com.lin.bean.BeanDefinition.BeanDefinition;
import java.lang.reflect.Constructor;
import java.lang.reflect.Method;
import java.util.List;
import java.util.Map;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-08 17:12
 **/
public class DefaultBeanDefinition implements BeanDefinition {

  private Class<?> clazz;

  private String beanName;
  private String beanFactoryName;

  private String createBeanMethodName;
  private String staticCreateBeanMethodName;
  private String beanInitMethodName;
  private String beanDestoryMethodName;

  private boolean isSingleton;

  private Constructor constructor;
  private Method method;

  private List<?> constructorArg;
  private Map<String, Object> values;

  public void setClazz(Class<?> clazz) {
    this.clazz = clazz;
  }

  public void setBeanFactoryName(String beanFactoryName) {
    this.beanFactoryName = beanFactoryName;
  }

  public void setCreateBeanMethodName(String createBeanMethodName) {
    this.createBeanMethodName = createBeanMethodName;
  }

  public void setStaticCreateBeanMethodName(String staticCreateBeanMethodName) {
    this.staticCreateBeanMethodName = staticCreateBeanMethodName;
  }

  public void setMethod(Method method) {
    this.method = method;
  }

  public void setValues(Map<String, Object> values) {
    this.values = values;
  }

  @Override
  public Class<?> getBeanClass() {
    return this.clazz;
  }

  @Override
  public String getBeanName() {
    return this.beanName;
  }

  public void setBeanName(String beanName) {
    this.beanName = beanName;
  }

  @Override
  public String getBeanFactory() {
    return this.beanFactoryName;
  }

  @Override
  public String getCreateBeanMethod() {
    return this.createBeanMethodName;
  }

  @Override
  public String getStaticCreateBeanMethod() {
    return this.staticCreateBeanMethodName;
  }

  @Override
  public String getBeanInitMethodName() {
    return this.beanInitMethodName;
  }

  public void setBeanInitMethodName(String beanInitMethodName) {
    this.beanInitMethodName = beanInitMethodName;
  }

  @Override
  public String getBeanDestoryMethodName() {
    return this.beanDestoryMethodName;
  }

  public void setBeanDestoryMethodName(String beanDestoryMethodName) {
    this.beanDestoryMethodName = beanDestoryMethodName;
  }

  @Override
  public String getScope() {
    return this.isSingleton ? BeanDefinition.SINGLETON : BeanDefinition.PROTOTYPE;
  }

  @Override
  public boolean isSingleton() {
    return this.isSingleton;
  }

  public void setSingleton(boolean singleton) {
    isSingleton = singleton;
  }

  @Override
  public boolean isPrototype() {
    return !this.isSingleton;
  }

  @Override
  public List<?> getConstructorArg() {
    return this.constructorArg;
  }

  public void setConstructorArg(List<?> constructorArg) {
    this.constructorArg = constructorArg;
  }

  @Override
  public Constructor<?> getConstructor() {
    return this.constructor;
  }

  @Override
  public void setConstructor(Constructor<?> constructor) {
    this.constructor = constructor;
  }

  @Override
  public Method getFactoryMethod() {
    return this.method;
  }

  @Override
  public void setFactoryMethod(Method factoryMethod) {
    this.method = factoryMethod;
  }

  @Override
  public Map<String, Object> getPropertyKeyValue() {
    return this.values;
  }

  @Override
  public void setPropertyKeyValue(Map<String, Object> properties) {
    this.values = properties;
  }
}
