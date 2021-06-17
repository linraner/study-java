package com.linran.singleinstance;

public class SingleInstance0 {

  private static SingleInstance0 INSTANCE;

  private SingleInstance0() {

  }

  // 线程不安全
  public static SingleInstance0 getInstance() {
    if (INSTANCE == null) {
      INSTANCE =  new SingleInstance0();
    }
    return INSTANCE;
  }
}
