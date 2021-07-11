package com.linran.codec;

import java.io.Serializable;

public  class RpcRequest implements Serializable {

  private static final long serialVersionUID = 6075550534864995305L;

  public static RpcRequest PING = new RpcRequest("REQUEST_PING");

  private String requestId;
  private String className;
  private String methodName;
  private Class<?>[] paramTypes;
  private Object[] params;
  private String version;

  public RpcRequest(String requestId) {
    this.requestId = requestId;
  }

  public String getRequestId() {
    return requestId;
  }

  public void setRequestId(String requestId) {
    this.requestId = requestId;
  }

  public String getClassName() {
    return className;
  }

  public void setClassName(String className) {
    this.className = className;
  }

  public String getMethodName() {
    return methodName;
  }

  public void setMethodName(String methodName) {
    this.methodName = methodName;
  }

  public Class<?>[] getParamTypes() {
    return paramTypes;
  }

  public void setParamTypes(Class<?>[] paramTypes) {
    this.paramTypes = paramTypes;
  }

  public Object[] getParams() {
    return params;
  }

  public void setParams(Object[] params) {
    this.params = params;
  }

  public String getVersion() {
    return version;
  }

  public void setVersion(String version) {
    this.version = version;
  }
}
