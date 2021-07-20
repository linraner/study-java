package com.linran.connect;

import com.google.common.base.Preconditions;
import com.linran.handler.RpcClientHandler;
import com.linran.handler.RpcClientInitializer;
import com.linran.loadblacnce.RpcLoadBalanceRoundRobin;
import com.linran.protocol.RpcProtocol;
import com.linran.utils.CollectionUtils;
import com.linran.utils.ConvertTools;
import com.linran.utils.ServiceUtils;
import io.netty.bootstrap.Bootstrap;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.socket.nio.NioSocketChannel;
import java.net.InetSocketAddress;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.CopyOnWriteArraySet;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Condition;
import java.util.concurrent.locks.ReentrantLock;
import org.apache.commons.collections4.MapUtils;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectManager {

  private static final Logger log = LoggerFactory.getLogger(ConnectManager.class);
  private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 600L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));
  private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);
  private final Map<RpcProtocol, RpcClientHandler> connectServerNodeMap = new ConcurrentHashMap<>();

  private final CopyOnWriteArraySet<RpcProtocol> rpcProtoColSet = new CopyOnWriteArraySet<>();

  private final ReentrantLock lock = new ReentrantLock();
  private final Condition connected = lock.newCondition();
  private final RpcLoadBalanceRoundRobin loadBalance = new RpcLoadBalanceRoundRobin();
  private volatile boolean RUNNING = true;

  public ConnectManager() {
  }

  public static ConnectManager getInstance() {
    return StaticSingletonHolder.INSTANCE;
  }

  /**
   * zk 数据更新 触发更新
   * @param servers
   */
  public void updateConnectServer(List<RpcProtocol> servers) {
    if (CollectionUtils.isEmpty(servers)) {
      log.error("No available server! will close connect!");
      for (RpcProtocol rpcProtocol : rpcProtoColSet) {
        closeConnectServerNode(rpcProtocol);
      }
      return;
    }

    Set<RpcProtocol> serverSet = ConvertTools.toSet(servers);
    for (RpcProtocol rpcProtocol : serverSet) {
      if (!rpcProtoColSet.contains(rpcProtocol)) {
        connectServerNode(rpcProtocol);
      }
    }

    for (RpcProtocol rpcProtocol : rpcProtoColSet) {
      if (!serverSet.contains(rpcProtocol)) {
        closeConnectServerNode(rpcProtocol);
      }
    }
  }

  public void updateConnectServer(RpcProtocol server, CuratorCacheListener.Type type) {
    Preconditions.checkNotNull(server);
    Preconditions.checkNotNull(type);
    switch (type) {
      case NODE_CREATED:
        if (!rpcProtoColSet.contains(server)) {
          connectServerNode(server);
        }
        break;
      case NODE_CHANGED:
        log.warn("Reconnected server is :{}", server);
        closeConnectServerNode(server);
        connectServerNode(server);
        break;
      case NODE_DELETED:
        closeConnectServerNode(server);
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + type);
    }
  }

  public void removeHandler(RpcProtocol server) {
    Preconditions.checkNotNull(server);
    rpcProtoColSet.remove(server);
    connectServerNodeMap.remove(server);
    log.info("Remove a connect, host:{}, port:{}", server.getHost(), server.getPort());
  }

  public RpcClientHandler getHandle(String serviceKey) throws Exception {
    while (RUNNING && MapUtils.isEmpty(connectServerNodeMap)) {
      try {
        log.info("waitingForHandler...");
        waitingForHandler();
      } catch (InterruptedException e) {
        log.error("Waiting server is interrupted!");
      }
    }
    RpcProtocol rpcProtocol = loadBalance.route(serviceKey, connectServerNodeMap);
    RpcClientHandler handler = connectServerNodeMap.get(rpcProtocol);
    if (handler == null) {
      throw new Exception("No available provider found, serviceKey: " + serviceKey);
    }
    return handler;
  }

  public void stop() {
    RUNNING = false;
    for (RpcProtocol rpcProtocol : rpcProtoColSet) {
      closeConnectServerNode(rpcProtocol);
    }
    singleAvailableHandle();
    threadPoolExecutor.shutdown();
    eventLoopGroup.shutdownGracefully();
  }

  private void connectServerNode(RpcProtocol server) {
    Preconditions.checkNotNull(server);
    String host = server.getHost();
    Integer port = server.getPort();
    if (!ServiceUtils.isAvailableServer(server)) {
      log.warn("No server node, host:{}, port:{}", host, port);
      return;
    }
    rpcProtoColSet.add(server);
    log.info("New service node add host:{}, port:{}", host, port);
    InetSocketAddress remoteSocket = new InetSocketAddress(host, port);
    threadPoolExecutor.submit(() -> {
      Bootstrap bootstrap = new Bootstrap();
      bootstrap.group(eventLoopGroup)
          .channel(NioSocketChannel.class)
          .handler(new RpcClientInitializer());
      ChannelFuture channelFuture = bootstrap.connect(remoteSocket);
      channelFuture.addListener((ChannelFutureListener) future -> {
        if (future.isSuccess()) {
          log.info("Connect success, remote peer:{}", remoteSocket);
          RpcClientHandler handler = future.channel()
              .pipeline()
              .get(RpcClientHandler.class);
          connectServerNodeMap.put(server, handler);
          handler.setRpcProtocol(server);
          singleAvailableHandle();
        } else {
          log.error("Connect failed, remote server: {}", remoteSocket);
        }
      });
    });
  }

  private void closeConnectServerNode(RpcProtocol server) {
    Preconditions.checkNotNull(server);
    RpcClientHandler handler = connectServerNodeMap.get(server);
    if (handler != null) {
      handler.close();
    }
    connectServerNodeMap.remove(server);
    rpcProtoColSet.remove(server);
  }

  private void singleAvailableHandle() {
    lock.lock();
    try {
      connected.signalAll();
    } finally {
      lock.unlock();
    }
  }

  private void waitingForHandler() throws InterruptedException {
    lock.lock();
    try {
      connected.await(5000, TimeUnit.MILLISECONDS);
    } finally {
      lock.unlock();
    }
  }

  private static class StaticSingletonHolder {

    public static final ConnectManager INSTANCE = new ConnectManager();
  }


}
