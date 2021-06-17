package com.linran.singleinstance;

public class SingleInstance4 {

  private SingleInstance4() {
  }

  public static SingleInstance4 getInstance() {
    return StaticSingletonHolder.INSTANCE;
  }

  // 静态内部类初始化
  static class StaticSingletonHolder {
    public static final SingleInstance4 INSTANCE = new SingleInstance4();
  }
}
