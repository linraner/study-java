package com.linran.registry;

import static com.linran.constants.ZkConstants.ZK_DATA_PATH;

import com.linran.protocol.RpcProtocol;
import com.linran.protocol.ServiceInfo;
import com.linran.utils.CollectionsUtils;
import com.linran.utils.ServiceUtils;
import com.linran.zookeeper.CuratorClient;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Set;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.state.ConnectionState;
import org.apache.curator.framework.state.ConnectionStateListener;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ServiceRegistryImpl implements ServiceRegistry {

  private static final Logger log = LoggerFactory.getLogger(ServiceRegistryImpl.class);
  private final List<String> paths = new ArrayList<>();
  private final CuratorClient curatorClient;

  public ServiceRegistryImpl(String registryAddress) {
    this.curatorClient = new CuratorClient(registryAddress);
  }

  @Override
  public void register(String host, int port, Map<String, Object> serviceMap) {
    List<ServiceInfo> serviceInfos = getServiceInfos(serviceMap);
    if (CollectionsUtils.isEmpty(serviceInfos)) {
      log.warn("no service info registry");
      return;
    }
    log.info("registry service size:{}", serviceInfos.size());
    try {
      RpcProtocol protocol = RpcProtocol.of(host, port, serviceInfos);
      byte[] data = protocol.getRpcProtocolData();
      String zkPath = getZkPath(protocol);
      curatorClient.createEphemeralSequential(zkPath, data);
    } catch (Exception e) {
      log.error("registry server error, host: {}, port:{}, server:{}", host, port, serviceMap, e);
    }

    curatorClient.addConnectionStateListener(new ConnectionStateListener() {
      @Override
      public void stateChanged(CuratorFramework curatorFramework, ConnectionState connectionState) {
        if (ConnectionState.RECONNECTED == connectionState) {
          log.info("server will reconnect, state:{}", connectionState);
          register(host, port, serviceMap);
        }
      }
    });

  }

  private String getZkPath(RpcProtocol protocol) {
    return ZK_DATA_PATH + "-" + protocol.hashCode();
  }

  private List<ServiceInfo> getServiceInfos(Map<String, Object> serviceMap) {
    // todo: logger
    Set<String> keys = serviceMap.keySet();
    return ServiceUtils.getServiceInfos(new ArrayList<>(keys));
  }

  @Override
  public void unregister() {
    log.info("unregister all service!");
    try {
      for (String path : paths) {
        try {
          curatorClient.delete(path);
        } catch (Exception e) {
          log.error("delete server path error, path:{} ", path, e);
        }
      }
    } finally {
      curatorClient.close();
    }
  }
}
