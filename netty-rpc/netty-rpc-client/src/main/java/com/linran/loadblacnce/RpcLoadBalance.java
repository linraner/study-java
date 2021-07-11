package com.linran.loadblacnce;

import com.linran.handler.RpcClientHandler;
import com.linran.protocol.RpcProtocol;
import com.linran.protocol.ServiceInfo;
import com.linran.utils.CollectionUtils;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public interface RpcLoadBalance {

  RpcProtocol route(final String serviceKey, final Map<RpcProtocol, RpcClientHandler> connectNodesMap) throws Exception;

  default Map<String, List<RpcProtocol>> groupByServiceKey(Collection<RpcProtocol> connectNodes) {
    if (CollectionUtils.isEmpty(connectNodes)) {
      return Collections.emptyMap();
    }
    // fixme: this is a bug??
    Map<String, List<RpcProtocol>> targets = new HashMap<>();
    for (RpcProtocol connectNode : connectNodes) {
      if (CollectionUtils.isEmpty(connectNode.getServiceInfos())) {
        continue;
      }
      for (ServiceInfo serviceInfo : connectNode.getServiceInfos()) {
        String key = serviceInfo.makeKey();
        List<RpcProtocol> rpcProtocols = targets.getOrDefault(key, new ArrayList<>());
        rpcProtocols.add(connectNode);
        targets.put(key, rpcProtocols);
      }
    }
    return targets;
  }

}
