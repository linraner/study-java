package com.linran.testserver0;

import com.hello.HelloService;
import com.linran.client.spring.RpcClient;
import com.linran.handler.AsyncCallback;
import com.linran.handler.RpcFuture;
import com.linran.proxy.RpcService;

public class RpcClient0 {

  public static void main(String[] args) throws Exception {
    String zkAddress = "127.0.0.1:2181";
    RpcClient client0 = new RpcClient(zkAddress);
    RpcService asyncService = client0.createAsyncService(HelloService.class, "");
    RpcFuture helloFuture = asyncService.call("hello");
    helloFuture.addCallback(new AsyncCallback() {
      @Override
      public void success(Object result) {
        System.out.println("success " + result);
      }

      @Override
      public void fail(Exception e) {
        System.out.println("failed " + e.toString());
      }
    });
    System.out.println("finish");
  }

}
