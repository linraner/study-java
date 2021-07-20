package com.linran.server.netty;

import com.linran.codec.RpcRequest;
import java.util.Map;
import java.util.UUID;
import org.junit.Before;
import org.junit.Test;

public class RpcServerHandlerTest {

  private  RpcServerHandler rpcServerHandler;

  @Before
  public void setUp() throws Exception {
    rpcServerHandler = new RpcServerHandler();

  }

  @Test
  public void testHandle() throws Throwable {
    Object handle = rpcServerHandler.handle(buildReq());
    System.out.println(handle);
  }

  private RpcRequest buildReq() {
      RpcRequest rpcRequest = new RpcRequest();
      rpcRequest.setRequestId(UUID.randomUUID().toString());
      rpcRequest.setClassName();
      rpcRequest.setMethodName();
      rpcRequest.setParamTypes();
      rpcRequest.setParams();
      rpcRequest.setVersion();
      return rpcRequest;
  }


}