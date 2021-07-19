package com.linran;

import com.linran.zookeeper.CuratorClient;

public class BaseTest {

  protected CuratorClient curatorClient;

  public void initZkClient(boolean remote) {
    String zkAddress = remote ? "59.110.225.203:2181" : "localhost:2181";
    curatorClient = new CuratorClient(zkAddress);
  }


}
