package com.linran.handler;

public interface AsyncCallback {

  void success(Object result);

  void fail(Exception e);

}
