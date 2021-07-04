package com.linran.serialize;

import com.linran.serialize.protostuff.ProtostuffSerializer;

public final class SerializerHolder {

  private static final Serializer serializer = new ProtostuffSerializer();

  public static Serializer serializerImpl() {
    return serializer;
  }

}
