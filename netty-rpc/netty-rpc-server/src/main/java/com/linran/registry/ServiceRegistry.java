package com.linran.registry;

import java.util.Map;

public interface ServiceRegistry {

  void register(String host, int port, Map<String, Object> serviceMap);

  void unregister();

}
