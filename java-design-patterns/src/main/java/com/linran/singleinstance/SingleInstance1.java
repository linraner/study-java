package com.linran.singleinstance;

public class SingleInstance1 {

  private static SingleInstance1 INSTANCE;

  private SingleInstance1() {

  }

  public static synchronized SingleInstance1 getInstance() {
    if (INSTANCE == null) {
      INSTANCE =  new SingleInstance1();
    }
    return INSTANCE;
  }
}
