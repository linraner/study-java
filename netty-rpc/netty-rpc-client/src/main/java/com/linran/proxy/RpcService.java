package com.linran.proxy;

import com.linran.function.SerializableFunction;
import com.linran.handler.RpcFuture;

public interface RpcService<T, P, FN extends SerializableFunction<T>> {

  RpcFuture call(String funcName, Object... args) throws Exception;

  RpcFuture call(FN fn, Object... args) throws Exception;

}