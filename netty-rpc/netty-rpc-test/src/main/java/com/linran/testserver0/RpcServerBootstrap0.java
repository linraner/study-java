package com.linran.testserver0;

import com.hello.HelloService;
import com.linran.RpcServer;

public class RpcServerBootstrap0 {

  public static void main(String[] args) throws Exception {
    String serverAddress = "127.0.0.1:18080";
    String registryAddress = "127.0.0.1:2181";
    RpcServer server = new RpcServer(serverAddress, registryAddress);
    HelloService helloService = new HelloService0();
    server.addService(HelloService.class.getSimpleName(), "", helloService);
    server.start();
  }

}
