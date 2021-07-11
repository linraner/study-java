package com.linran.loadblacnce;

import com.linran.handler.RpcClientHandler;
import com.linran.protocol.RpcProtocol;
import com.linran.utils.Assert;
import com.linran.utils.CollectionUtils;
import java.util.List;
import java.util.Map;
import java.util.concurrent.atomic.LongAdder;

public final class RpcLoadBalanceRoundRobin implements RpcLoadBalance {

  public RpcLoadBalanceRoundRobin() {
  }

  private static final LongAdder roundRobinStep = new LongAdder();

  @Override
  public RpcProtocol route(String serviceKey, Map<RpcProtocol, RpcClientHandler> connectNodesMap) throws Exception {
    Map<String, List<RpcProtocol>> connectMap = groupByServiceKey(connectNodesMap.keySet());
    List<RpcProtocol> rpcProtocols = connectMap.get(serviceKey);
    return roundRobinGet(rpcProtocols);
  }

  private RpcProtocol roundRobinGet(List<RpcProtocol> rpcProtocols) {
    Assert.isTrue(CollectionUtils.isNotEmpty(rpcProtocols), "Address not null");
    int size = rpcProtocols.size();
    return rpcProtocols.get((int) (incrAndGet() % size));
  }

  private long incrAndGet() {
    roundRobinStep.increment();
    long step = roundRobinStep.longValue();
    if (step > 0xfffffff) {
      roundRobinStep.reset();
    }
    return roundRobinStep.longValue();
  }

}
