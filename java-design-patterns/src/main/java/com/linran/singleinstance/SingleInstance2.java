package com.linran.singleinstance;

public class SingleInstance2 {

  private static SingleInstance2 INSTANCE;

  private SingleInstance2() {

  }

  public static  SingleInstance2 getInstance() {
    // 减小锁的粒度
    if (INSTANCE == null) {
      synchronized (SingleInstance2.class) {
        if (INSTANCE == null) {
          INSTANCE = new SingleInstance2();
        }
      }
    }
    return INSTANCE;
  }
}
