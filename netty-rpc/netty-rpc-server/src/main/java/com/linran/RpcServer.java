package com.linran;

import com.linran.annotation.RpcService;
import com.linran.server.netty.NettyServer;
import java.util.Map;
import org.apache.commons.collections4.MapUtils;
import org.springframework.beans.BeansException;
import org.springframework.beans.factory.DisposableBean;
import org.springframework.beans.factory.InitializingBean;
import org.springframework.context.ApplicationContext;
import org.springframework.context.ApplicationContextAware;

public final class RpcServer extends NettyServer implements ApplicationContextAware, InitializingBean, DisposableBean {

  public RpcServer(String serverAddress, String registryAddress) {
    super(serverAddress, registryAddress);
  }

  @Override
  public void setApplicationContext(ApplicationContext ctx) throws BeansException {
    Map<String, Object> beanMap = ctx.getBeansWithAnnotation(RpcService.class);
    if (MapUtils.isNotEmpty(beanMap)) {
      for (Object bean : beanMap.values()) {
        RpcService rpcService = bean.getClass().getAnnotation(RpcService.class);
        String interfaceName = rpcService.value().getName();
        String version = rpcService.version();
        super.addService(interfaceName, version, bean);
      }
    }
  }

  @Override
  public void destroy() throws Exception {
    super.stop();
  }

  @Override
  public void afterPropertiesSet() throws Exception {
    super.start();
  }
}
