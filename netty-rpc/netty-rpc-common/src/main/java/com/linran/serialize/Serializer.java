package com.linran.serialize;

public interface Serializer {

  <T> byte[] serialize(T input);

  <T> Object deserialize(byte[] input, Class<T> clz);

}
