package com.linran.server.netty;

import com.linran.registry.ServiceRegistry;
import com.linran.registry.ServiceRegistryImpl;
import com.linran.server.ServerLifeCycle;
import com.linran.utils.ServiceUtils;
import com.linran.utils.ThreadPoolUtils;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioServerSocketChannel;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.ThreadPoolExecutor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class NettyServer implements ServerLifeCycle {

  private static final Logger log = LoggerFactory.getLogger(NettyServer.class);
  private final String serverAddress;
  private final ServiceRegistry serviceRegistry;
  private final Map<String, Object> serviceMap = new ConcurrentHashMap<>();
  private Thread runThread;

  private EventLoopGroup workerGroup;
  private EventLoopGroup bossGroup;

  public NettyServer(String serverAddress, String registryAddress) {
    this.serverAddress = serverAddress;
    this.serviceRegistry = new ServiceRegistryImpl(registryAddress);
  }


  @Override
  public void start() throws Exception {
    runThread = new Thread(new Runnable() {
      @Override
      public void run() {
        try {
          init();
        } catch (Exception e) {
          log.error("netty service start error: ", e);
          try {
            serviceRegistry.unregister();
          } finally {
            shutdown();
          }
        }
      }
    });
    runThread.start();
  }

  private ThreadPoolExecutor initFixThreadPool() {
    return ThreadPoolUtils.newFixedThreadPool(
        16,
        new LinkedBlockingDeque<>(1000),
        r -> new Thread(r, "netty-rpc-" + NettyServer.class.getSimpleName() + "-" + r.hashCode()),
        new ThreadPoolExecutor.AbortPolicy()
    );
  }

  public void addService(String interfaceName, String version, Object serviceBean) {
    log.info("add service, interface:{}, version:{}, bean:{}", interfaceName, version, serviceBean);
    serviceMap.put(ServiceUtils.getKey(interfaceName, version), serviceBean);
  }

  private void init() throws InterruptedException {
    final ThreadPoolExecutor threadPoolExecutor = initFixThreadPool();
    bossGroup = new NioEventLoopGroup();
    workerGroup = new NioEventLoopGroup();
    ServerBootstrap bootstrap = new ServerBootstrap();
    bootstrap.group(bossGroup, workerGroup).channel(NioServerSocketChannel.class)
        .childHandler(new RpcServerInitializer(serviceMap, threadPoolExecutor))
        .option(ChannelOption.SO_BACKLOG, 128)
        .childOption(ChannelOption.SO_KEEPALIVE, true);

    String[] arr = serverAddress.split(":");
    String host = arr[0];
    int port = Integer.parseInt(arr[1]);
    bootstrap.bind(host, port).sync();
  }

  private void shutdown() {
    try {
      workerGroup.shutdownGracefully();
      bossGroup.shutdownGracefully();
    } catch (Exception e) {
      log.error("netty server shutdown error, ", e);
    }
  }

  @Override
  public void stop() throws Exception {
    if (runThread != null && runThread.isAlive()) {
      runThread.interrupt();
    }
  }
}
