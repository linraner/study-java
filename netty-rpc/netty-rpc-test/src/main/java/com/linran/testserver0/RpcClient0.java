package com.linran.testserver0;

import com.hello.HelloService;
import com.hello.QueryUserRequest;
import com.linran.client.spring.RpcClient;
import com.linran.handler.AsyncCallback;
import com.linran.handler.RpcFuture;
import com.linran.proxy.RpcService;
import java.util.Date;
import java.util.concurrent.CountDownLatch;
import lombok.extern.slf4j.Slf4j;
import org.junit.Before;
import org.junit.Test;

@Slf4j
public class RpcClient0 {

  private final String zkAddress = "127.0.0.1:2181";
  private final RpcClient rpcClient = new RpcClient(zkAddress);
  private final RpcService hellowAsyncService = rpcClient.createAsyncService(HelloService.class, "");


  @Before
  public void setUp() {

  }

  @Test
  public void testAsync() throws Exception {
    RpcFuture helloFuture = hellowAsyncService.call("hello");
    final int count = 10;
    final CountDownLatch countDownLatch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      final int finalI = i;
      helloFuture.addCallback(new AsyncCallback() {
        @Override
        public void success(Object result) {
          log.info("i:{}, success:{}", finalI, result);
          countDownLatch.countDown();
        }

        @Override
        public void fail(Exception e) {
          log.info("i:{}, failed: ", finalI, e);
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    rpcClient.stop();
    System.out.println("stop...");
  }

  @Test
  public void testAsyncWithArgs() throws Exception {
    RpcFuture helloFuture = hellowAsyncService.call("hello", new Date());
    final int count = 10;
    final CountDownLatch countDownLatch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      final int finalI = i;
      helloFuture.addCallback(new AsyncCallback() {
        @Override
        public void success(Object result) {
          log.info("i:{}, success:{}", finalI, result);
          countDownLatch.countDown();
        }

        @Override
        public void fail(Exception e) {
          log.info("i:{}, failed: ", finalI, e);
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    rpcClient.stop();
    System.out.println("stop...");
  }

  @Test
  public void testCallWithObjectRequest() throws Exception {
    RpcFuture helloFuture = hellowAsyncService.call("queryUser", buildQueryUserReq());
    final int count = 10;
    final CountDownLatch countDownLatch = new CountDownLatch(count);
    for (int i = 0; i < count; i++) {
      final int finalI = i;
      helloFuture.addCallback(new AsyncCallback() {
        @Override
        public void success(Object result) {
          log.info("i:{}, success:{}", finalI, result);
          countDownLatch.countDown();
        }

        @Override
        public void fail(Exception e) {
          log.info("i:{}, failed: ", finalI, e);
          countDownLatch.countDown();
        }
      });
    }
    countDownLatch.await();
    rpcClient.stop();
    System.out.println("stop...");
  }

  private QueryUserRequest buildQueryUserReq() {
    QueryUserRequest queryUserRequest = new QueryUserRequest();
    queryUserRequest.setStart(0);
    queryUserRequest.setLimit(10);
    return queryUserRequest;
  }

}
