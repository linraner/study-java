package com.linran.constants;

public interface ZkConstants {

  /**
   * zk session ms
   */
  int ZK_SESSION_TIMEOUT = 5000;

  /**
   * zk timeout ms
   */
  int ZK_CONNECT_TIMEOUT = 5000;

  // =============== retry =======================
  int ZK_RETRY_INTERVAL = 1000;
  int ZK_RETRY_COUNT = 3;
  // =============== retry end =======================

  // =============== zk path =======================
  String ZK_RPC_REGISTRY_PATH = "/zookeeper/netty-rpc-registry/";
  String ZK_DATA_PATH = ZK_RPC_REGISTRY_PATH + "/data";
  String ZK_NAMESPACE = "netty-tpc";
  // =============== zk path end =======================

}
