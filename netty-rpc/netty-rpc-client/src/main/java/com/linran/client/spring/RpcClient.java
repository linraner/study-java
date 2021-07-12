package com.linran.client.spring;

import com.linran.annotation.RpcAutowired;
import com.linran.connect.ConnectManager;
import com.linran.discovery.ServiceDiscovery;
import com.linran.proxy.ObjectProxy;
import com.linran.proxy.RpcService;
import java.lang.reflect.Field;
import java.lang.reflect.Proxy;
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
  private final static ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(16, 16, 600L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
  private final ServiceDiscovery serviceDiscovery;

  public RpcClient(String address) {
    this.serviceDiscovery = new ServiceDiscovery(address);
  }

  public static void submit(Runnable task) {
    threadPoolExecutor.submit(task);
  }

  @SuppressWarnings(value = "unchecked")
  public static <T, P> T createService(Class<T> interfaceClass, String version) {
    return (T) Proxy.newProxyInstance(
        interfaceClass.getClassLoader(),
        new Class<?>[]{interfaceClass},
        new ObjectProxy<T, P>(interfaceClass, version)
    );
  }

  public static <T, P> RpcService createAsyncService(Class<T> interfaceClass, String version) {
    return new ObjectProxy<T, P>(interfaceClass, version);
  }

  public void stop() {
    threadPoolExecutor.shutdown();
    serviceDiscovery.stop();
    ConnectManager.getInstance().stop();
  }

  @Override
  public void destroy() throws Exception {
    stop();
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    String[] beanNames = ctx.getBeanDefinitionNames();
    for (String beanName : beanNames) {
      Object bean = ctx.getBean(beanName);
      Field[] fields = bean.getClass().getDeclaredFields();
      try {
        for (Field field : fields) {
          RpcAutowired rpcAutowired = field.getAnnotation(RpcAutowired.class);
          if (rpcAutowired != null) {
            String version = rpcAutowired.version();
            field.setAccessible(true);
            field.set(bean, createService(field.getType(), version));
          }
        }
      } catch (IllegalAccessException e) {
        log.error(e.toString());
      }
    }
  }
}
