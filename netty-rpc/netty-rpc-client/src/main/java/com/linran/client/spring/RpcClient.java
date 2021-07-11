package com.linran.client.spring;

import com.linran.discovery.ServiceDiscovery;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public class RpcClient implements ApplicationContextAware, DisposableBean {

  private static final Logger log = LoggerFactory.getLogger(RpcClient.class);

  private ServiceDiscovery serviceDiscovery;
  private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));


  @Override
  public void destroy() throws Exception {

  }

  public static void submit(Runnable task) {
    threadPoolExecutor.submit(task);
  }

  @Override
  public void setApplicationContext(ApplicationContext applicationContext) throws BeansException {

  }
}
