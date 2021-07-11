package com.linran.function;

@FunctionalInterface
public interface Function1<T, P1, P2> extends SerializableFunction {

  Object apply(T t, P1 p1, P2 p2);

}
