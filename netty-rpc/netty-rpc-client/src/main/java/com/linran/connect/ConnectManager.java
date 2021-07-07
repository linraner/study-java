package com.linran.connect;

import com.linran.utils.ThreadPoolUtils;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import java.util.concurrent.LinkedBlockingQueue;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.ThreadUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ConnectManager {

  private static final Logger log = LoggerFactory.getLogger(ConnectManager.class);

  private final EventLoopGroup eventLoopGroup = new NioEventLoopGroup(4);

  private static final ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(4, 8, 600L, TimeUnit.SECONDS, new LinkedBlockingQueue<Runnable>(1000));


}
