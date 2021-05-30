package com.linran.chain.sample0;

public class Client {

  public static void main(String[] args) {
    Handle handle0 = new Handle0();
    Handle handle1 = new Handle1();
    handle0.setNextHandle(handle1);

    handle0.handlerRequest("0");
  }

}
