package com.linran.chain.sample0;

public abstract class Handle {

  protected Handle nextHandle;

  public void setNextHandle(Handle successor) {
    this.nextHandle = successor;
  }

  public abstract void handlerRequest(String request);

  public void handleNext(String request) {
    if (nextHandle != null) {
      this.nextHandle.handlerRequest(request);
    }
  }
}
