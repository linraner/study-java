package com.linran.testserver0;

import com.hello.HelloService;
import com.linran.codec.RpcRequest;
import com.linran.server.netty.RpcServerHandler;
import com.linran.utils.ServiceUtils;
import com.linran.utils.ThreadPoolUtils;
import java.util.Map;
import java.util.UUID;
import java.util.concurrent.ConcurrentHashMap;
import org.junit.Before;
import org.junit.Test;

public class RpcServerTest0 {

  private RpcServerHandler rpcServerHandler;

  @Before
  public void setUp() throws Exception {
    rpcServerHandler = new RpcServerHandler(getBeanMap(), ThreadPoolUtils.newFixedThreadPool(10));
  }

  private Map<String, Object> getBeanMap() {
    Map<String, Object> beanMap = new ConcurrentHashMap<>();
    beanMap.put(ServiceUtils.getKey(HelloService.class.getName(), ""), new HelloService0());
    return beanMap;
  }

  @Test
  public void testHandle() throws Throwable {
    Object handle = rpcServerHandler.handle(buildReq());
    System.out.println(handle);
  }

  private RpcRequest buildReq() {
    RpcRequest rpcRequest = new RpcRequest();
    rpcRequest.setRequestId(UUID.randomUUID().toString());
    rpcRequest.setClassName(HelloService.class.getName());
    rpcRequest.setMethodName("hello");
    rpcRequest.setParamTypes(new Class[0]);
    rpcRequest.setParams(new Object[0]);
    rpcRequest.setVersion("");
    return rpcRequest;
  }

}
