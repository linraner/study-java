package com.linran.zookeeper;

import static com.linran.constants.ZkConstants.ZK_CONNECT_TIMEOUT;
import static com.linran.constants.ZkConstants.ZK_NAMESPACE;
import static com.linran.constants.ZkConstants.ZK_SESSION_TIMEOUT;

import java.util.List;
import lombok.Getter;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
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
  }

  public static void create(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
    client.create().creatingParentsIfNeeded().forPath(path, payload);
  }

  public static void createEphemeral(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
    client.create().withMode(CreateMode.EPHEMERAL).forPath(path, payload);
  }

  public static String createEphemeralSequential(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
    return client.create().withProtection().withMode(CreateMode.EPHEMERAL_SEQUENTIAL).forPath(path, payload);
  }

  public static void setData(final CuratorFramework client, final String path, final byte[] payload) throws Exception {
    client.setData().forPath(path, payload);
  }

  public static void delete(final CuratorFramework client, final String path) throws Exception {
    client.delete().deletingChildrenIfNeeded().forPath(path);
  }

  public static void guaranteedDelete(final CuratorFramework client, final String path) throws Exception {
    client.delete().guaranteed().forPath(path);
  }

  public static String getData(final CuratorFramework client, final String path) throws Exception {
    return new String(client.getData().forPath(path));
  }

  public static List<String> getChildren(final CuratorFramework client, final String path) throws Exception {
    return client.getChildren().forPath(path);
  }

}
