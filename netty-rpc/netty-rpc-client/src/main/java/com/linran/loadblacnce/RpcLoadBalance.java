package com.linran.loadblacnce;

import com.linran.handler.RpcClientHandler;
import com.linran.protocol.RpcProtocol;
import com.linran.utils.CollectionsUtils;
import com.linran.utils.ServiceUtils;
import java.security.spec.ECField;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.apache.commons.collections4.MapUtils;

public interface RpcLoadBalance {

  RpcProtocol route(final String serviceKey, final Map<RpcProtocol, RpcClientHandler> connectNodesMap) throws Exception;

  default Map<String, List<RpcProtocol>> groupByServiceKey(Collection<RpcProtocol> connectNodes) {
    if (CollectionsUtils.isEmpty(connectNodes)) {
      return Collections.emptyMap();
    }
    Map<String, List<RpcProtocol>> targets = new HashMap<>();



  }

}
