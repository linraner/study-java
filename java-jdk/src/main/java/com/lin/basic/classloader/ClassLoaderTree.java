package com.lin.basic.classloader;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-22 12:54
 * @Description:
 **/
public class ClassLoaderTree {

  public static void main(String[] args) {
    ClassLoader loader = ClassLoaderTree.class.getClassLoader();
    while (loader != null) {
      System.out.println("loader = " + loader.toString());
      loader = loader.getParent();
    }
  }
}
