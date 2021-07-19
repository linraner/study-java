package com.linran.testserver0;

import com.hello.HelloService;
import com.linran.client.spring.RpcClient;
import com.linran.handler.RpcFuture;
import com.linran.proxy.RpcService;

public class RpcClient0 {

  public static void main(String[] args) throws Exception {
    String zkAddress = "127.0.0.1:2181";
    RpcClient client0 = new RpcClient(zkAddress);
    RpcService asyncService = RpcClient.createAsyncService(HelloService.class, "");
    RpcFuture hello = asyncService.call("hello");
    System.out.println("resp" + hello.toString());
  }

}
