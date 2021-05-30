package com.linran.chain.sample0;

public class Handle0 extends Handle{


  @Override
  public void handlerRequest(String request) {
    if ("0".equals(request)) {
      System.out.println("handle 0");
    }
    handleNext(request);
  }
}
