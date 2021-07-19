package com.linran.zookeeper;

import static com.linran.constants.ZkConstants.ZK_CONNECT_TIMEOUT;
import static com.linran.constants.ZkConstants.ZK_NAMESPACE;
import static com.linran.constants.ZkConstants.ZK_SESSION_TIMEOUT;

import java.util.List;
import lombok.Getter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.cache.CuratorCache;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.zookeeper.CreateMode;

public final class CuratorClient {

  @Getter
  private final CuratorFramework client;

  public CuratorClient(
      String zkUrl
  ) {
    this.client = CuratorFrameworkFactory.builder()
        .connectString(zkUrl)
        .retryPolicy(new ExponentialBackoffRetry(1000, 3))
        .connectionTimeoutMs(ZK_CONNECT_TIMEOUT)
        .sessionTimeoutMs(ZK_SESSION_TIMEOUT)
        .namespace(ZK_NAMESPACE)
        .build();
    client.start();
  }

  public void create(final String path, final byte[] payload) throws Exception {
    client.create().creatingParentsIfNeeded().forPath(path, payload);
  }

  public void createEphemeral(final String path, final byte[] payload) throws Exception {
    client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
  }

  public void createEphemeralSequential(final String path, final byte[] payload) throws Exception {
    client.create().creatingParentsIfNeeded().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
  }

  public void setData(final String path, final byte[] payload) throws Exception {
    client.setData().forPath(path, payload);
  }

  public void delete(final String path) throws Exception {
    client.delete().deletingChildrenIfNeeded().forPath(path);
  }

  public void guaranteedDelete(final String path) throws Exception {
    client.delete().guaranteed().forPath(path);
  }

  public String getData(final String path) throws Exception {
    return new String(client.getData().forPath(path));
  }

  public List<String> getChildren(final String path) throws Exception {
    return client.getChildren().forPath(path);
  }

  public void addConnectionStateListener(ConnectionStateListener connectionStateListener) {
    client.getConnectionStateListenable().addListener(connectionStateListener);
  }


  public void watchPathChildrenNode(String path, CuratorCacheListener listener) throws Exception {
    CuratorCache curatorCache = CuratorCache.builder(client, path).build();
    curatorCache.listenable().addListener(listener);
    curatorCache.start();
  }

  public void close() {
    client.close();
  }
}
