package com.linran.singleinstance;

// 枚举初始化单例
public class SingleInstance5 {

  private SingleInstance5() {
  }

  public static Single getInstance() {
    return Single.SINGLE;
  }

  public static void main(String[] args) {
    Single single = getInstance();
    single.process();
  }

  enum Single {
    SINGLE,
    ;

    Single() {
    }

    public void process() {
      System.out.println("process");
    }

  }

}
