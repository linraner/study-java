package com.linran.discovery;

import static com.linran.constants.ZkConstants.ZK_RPC_REGISTRY_PATH;

import com.linran.connect.ConnectManager;
import com.linran.protocol.RpcProtocol;
import com.linran.zookeeper.CuratorClient;
import java.nio.charset.StandardCharsets;
import java.util.LinkedList;
import java.util.List;
import org.apache.curator.framework.recipes.cache.ChildData;
import org.apache.curator.framework.recipes.cache.CuratorCacheListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceDiscovery {

  private static final Logger log = LoggerFactory.getLogger(ServiceDiscovery.class);

  private final CuratorClient curatorClient;

  public ServiceDiscovery(String registryAddress) {
    this.curatorClient = new CuratorClient(registryAddress);
    discovery();
  }


  private void discovery() {
    try {
      log.info("init service info");
      findAndUpdate();
      curatorClient.watchPathChildrenNode(
          ZK_RPC_REGISTRY_PATH,
          (type, oldData, data) -> {
            switch (type) {
              case NODE_CREATED:
              case NODE_CHANGED:
              case NODE_DELETED:
                updateChildData(data, type);
                break;
            }
          }
      );

    } catch (Exception e) {
      log.error("watch node error, ", e);
    }
  }

  private void findAndUpdate() {
    try {
      List<String> children = curatorClient.getChildren(ZK_RPC_REGISTRY_PATH);
      List<RpcProtocol> rpcProtocols = new LinkedList<>();
      for (String child : children) {
        log.info("child :{}", child);
        String data = curatorClient.getData(ZK_RPC_REGISTRY_PATH + "/" + child);
        RpcProtocol rpcProtocol = RpcProtocol.fromJson(data);
        rpcProtocols.add(rpcProtocol);
      }

      updateConnectServer(rpcProtocols);

    } catch (Exception e) {
      log.error("fetch node error: ", e);
    }
  }


  private void updateChildData(ChildData childData, CuratorCacheListener.Type type) {
    String path = childData.getPath();
    String data = new String(childData.getData(), StandardCharsets.UTF_8);
    log.info("child data type:{}, update path :{}, data:{}", type, path, data);
    RpcProtocol rpcProtocol = RpcProtocol.fromJson(data);
    updateConnectServer(rpcProtocol, type);
  }
  // =============== manager =========================

  private void updateConnectServer(List<RpcProtocol> rpcProtocols) {
    ConnectManager.getInstance().updateConnectServer(rpcProtocols);
  }

  private void updateConnectServer(RpcProtocol rpcProtocol, CuratorCacheListener.Type type) {
    ConnectManager.getInstance().updateConnectServer(rpcProtocol, type);
  }
  // =============== manager end =========================


  public void stop() {

  }
}
