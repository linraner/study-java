package com.linran.proxy;

import com.linran.function.SerializableFunction;
import com.linran.handler.RpcFuture;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ObjectProxy<T, P> implements InvocationHandler, RpcService<T, P, SerializableFunction<T>> {

  private static final Logger log = LoggerFactory.getLogger(ObjectProxy.class);

  private Class<T> clz;
  private String version;

  public ObjectProxy(Class<T> clz, String version) {
    this.clz = clz;
    this.version = version;
  }

  @Override
  public RpcFuture call(String funcName, Object... args) throws Exception {
    // todo invoke func
    return null;
  }

  @Override
  public RpcFuture call(SerializableFunction<T> tSerializableFunction, Object... args) throws Exception {
    return null;
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    return null;
  }
}
