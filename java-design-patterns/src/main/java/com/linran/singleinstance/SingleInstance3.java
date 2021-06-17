package com.linran.singleinstance;

// 饿汉式 要初始化 大部分饿汉式就可以了
public class SingleInstance3 {

  private final static SingleInstance3 INSTANCE = new SingleInstance3();

  private SingleInstance3() {
  }

  public static SingleInstance3 getInstance() {
    return INSTANCE;
  }
}
