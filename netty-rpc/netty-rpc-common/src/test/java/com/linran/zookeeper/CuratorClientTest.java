package com.linran.zookeeper;

import com.linran.BaseTest;
import org.apache.zookeeper.CreateMode;
import org.junit.Before;
import org.junit.Ignore;
import org.junit.Test;

@Ignore
public class CuratorClientTest extends BaseTest {

  static final String TEST_PATH = "/test";

  private CuratorClient curatorClient;

  @Before
  public void setUp() throws Exception {
    initZkClient(false);
    curatorClient = super.curatorClient;
  }


  @Test
  public void createEphemeral() throws Exception {
    byte[] data = "asdas".getBytes();
    curatorClient.createEphemeralSequential(TEST_PATH, data);
  }

  @Test
  public void create() throws Exception {
    curatorClient.create(TEST_PATH, "asdasd".getBytes());
  }

  @Test
  @Ignore
  public void testCreateData() throws Exception {
    curatorClient.getClient().create().withMode(CreateMode.EPHEMERAL).forPath(TEST_PATH, "asdasd".getBytes());
  }

  @Test
  public void testCreateEphemeral() {

  }
}