package com.linran.chain.sample0;

public class Handle1 extends Handle {

  @Override
  public void handlerRequest(String request) {
    if ("1".equals(request)) {
      System.out.println("handle 1");
    }
    handleNext(request);
  }
}
