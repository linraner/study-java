package com.linran.utils;

import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class JsonUtils {

  private static final Logger log = LoggerFactory.getLogger(JsonUtils.class);

  private static final ObjectMapper OBJECT_MAPPER = new ObjectMapper();


  public static String serialize(Object obj) {
    try {
      return OBJECT_MAPPER.writeValueAsString(obj);
    } catch (JsonProcessingException e) {
      log.error("serialize json error, obj:{}", obj, e);
      return null;
    }
  }

  public static <T> T deserialize(String jsonStr, Class<T> clz) {
    try {
      return OBJECT_MAPPER.readValue(jsonStr, clz);
    } catch (JsonProcessingException e) {
      log.error("deserialize json error, jsonStr:{}", jsonStr, e);
      return null;
    }
  }


}
