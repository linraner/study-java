package com.lin.basic.reflect;

import java.lang.reflect.Method;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-25 12:52
 * @Description:
 **/
public class ReflectDemo {

  public static void main(String[] args) throws IllegalAccessException, InstantiationException, NoSuchMethodException {
    Class<?> clazz = MethodClass.class;
    Object object = clazz.newInstance();
    Method[] methods = clazz.getMethods();
    Method[] declaredMethods = clazz.getDeclaredMethods();
    Method method = clazz.getMethod("add", int.class, int.class);

    for (Method m : methods) {
      System.out.println("method = " + m);
    }

    for (Method m : declaredMethods) {
      System.out.println("declaredMethod = " + m);
    }

  }
}

class MethodClass {

  public final int ans = 3;

  public int add(int a, int b) {
    return a + b;
  }

  public int sub(int a, int b) {
    return a + b;
  }
}
