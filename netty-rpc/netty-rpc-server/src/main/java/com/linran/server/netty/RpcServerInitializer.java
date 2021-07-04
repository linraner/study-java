package com.linran.server.netty;

import com.linran.codec.RpcDecoder;
import com.linran.codec.RpcEncoder;
import com.linran.codec.RpcRequest;
import com.linran.codec.RpcResponse;
import com.linran.serialize.Serializer;
import com.linran.serialize.protostuff.ProtostuffSerializer;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelPipeline;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.codec.LengthFieldBasedFrameDecoder;
import io.netty.handler.timeout.IdleStateHandler;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class RpcServerInitializer extends ChannelInitializer<SocketChannel> {

  private final Map<String, Object> handlerMap;
  private final ThreadPoolExecutor threadPoolExecutor;

  public RpcServerInitializer(
      final Map<String, Object> handlerMap,
      final ThreadPoolExecutor threadPoolExecutor
  ) {
    this.handlerMap = handlerMap;
    this.threadPoolExecutor = threadPoolExecutor;
  }

  @Override
  protected void initChannel(SocketChannel channel) throws Exception {
    Serializer serializer = ProtostuffSerializer.class.newInstance();
    ChannelPipeline cp = channel.pipeline();
    cp.addLast(new IdleStateHandler(0, 0, 500, TimeUnit.SECONDS));
    cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
    cp.addLast(new RpcDecoder(RpcRequest.class, serializer));
    cp.addLast(new RpcEncoder(RpcResponse.class, serializer));
    cp.addLast(new RpcServerHandler(handlerMap, threadPoolExecutor));
  }
}
