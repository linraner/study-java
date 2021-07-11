package com.linran.handler;

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
import java.util.concurrent.TimeUnit;

public final class RpcClientInitializer extends ChannelInitializer<SocketChannel> {

  @Override
  protected void initChannel(SocketChannel socketChannel) throws Exception {
    Serializer serializer = ProtostuffSerializer.class.newInstance();
    ChannelPipeline cp = socketChannel.pipeline();
    cp.addLast(new IdleStateHandler(0, 0, 30, TimeUnit.SECONDS));
    cp.addLast(new RpcEncoder(RpcRequest.class, serializer));
    cp.addLast(new LengthFieldBasedFrameDecoder(65536, 0, 4, 0, 0));
    cp.addLast(new RpcDecoder(RpcResponse.class, serializer));
    cp.addLast(new RpcClientHandler());
  }
}
