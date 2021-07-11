package com.linran.function;

@FunctionalInterface
public interface Function0<T, P> extends SerializableFunction {

  Object apply(T t, P p);

}
