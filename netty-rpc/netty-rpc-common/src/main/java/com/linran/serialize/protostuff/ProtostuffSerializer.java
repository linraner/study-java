package com.linran.serialize.protostuff;

import com.dyuproject.protostuff.LinkedBuffer;
import com.dyuproject.protostuff.ProtostuffIOUtil;
import com.dyuproject.protostuff.Schema;
import com.dyuproject.protostuff.runtime.RuntimeSchema;
import com.linran.serialize.Serializer;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.springframework.objenesis.Objenesis;
import org.springframework.objenesis.ObjenesisStd;

public final class ProtostuffSerializer implements Serializer {

  private static final Map<Class<?>, Schema<?>> cachedSchema = new ConcurrentHashMap<Class<?>, Schema<?>>();

  private static final Objenesis objenesis = new ObjenesisStd(true);

  @SuppressWarnings("unchecked")
  private static <T> Schema<T> getSchema(Class<T> cls) {
    Schema<T> schema = (Schema<T>) cachedSchema.get(cls);
    if (schema == null) {
      schema = RuntimeSchema.createFrom(cls);
      cachedSchema.put(cls, schema);
    }
    return schema;
  }

  @Override
  @SuppressWarnings("unchecked")
  public <T> byte[] serialize(T obj) {
    Class<T> cls = (Class<T>) obj.getClass();
    LinkedBuffer buffer = LinkedBuffer.allocate(LinkedBuffer.DEFAULT_BUFFER_SIZE);
    try {
      Schema<T> schema = getSchema(cls);
      return ProtostuffIOUtil.toByteArray(obj, schema, buffer);
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage(), e);
    } finally {
      buffer.clear();
    }
  }

  @Override
  public <T> Object deserialize(byte[] input, Class<T> clz) {
    try {
      T message = objenesis.newInstance(clz);
      Schema<T> schema = getSchema(clz);
      ProtostuffIOUtil.mergeFrom(input, message, schema);
      return message;
    } catch (Exception e) {
      throw new IllegalStateException(e.getMessage(), e);
    }
  }
}
