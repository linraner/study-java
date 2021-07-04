package com.linran.codec;

import com.linran.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RpcEncoder extends MessageToByteEncoder {

  private static final Logger log = LoggerFactory.getLogger(RpcEncoder.class);

  private final Class<?> clz;
  private final Serializer serializer;

  public RpcEncoder(Class<?> clz, Serializer serializer) {
    this.clz = clz;
    this.serializer = serializer;
  }


  @Override
  protected void encode(ChannelHandlerContext ctx, Object in, ByteBuf out) throws Exception {
    if (clz.isInstance(in)) {
      try {
        byte[] data = serializer.serialize(in);
        out.writeInt(data.length);
        out.writeBytes(data);
      } catch (Exception e) {
        log.error("encode error: ", e);
      }
    } else {
      log.warn("encode obj instance is error");
    }


  }
}
