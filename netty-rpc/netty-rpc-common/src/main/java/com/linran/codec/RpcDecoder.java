package com.linran.codec;

import com.linran.serialize.Serializer;
import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.ByteToMessageDecoder;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RpcDecoder extends ByteToMessageDecoder {

  private static final Logger log = LoggerFactory.getLogger(RpcEncoder.class);

  private final Class<?> clz;
  private final Serializer serializer;

  public RpcDecoder(Class<?> clz, Serializer serializer) {
    this.clz = clz;
    this.serializer = serializer;
  }

  @Override
  protected void decode(ChannelHandlerContext ctx, ByteBuf in, List<Object> out) throws Exception {
    if (in.readableBytes() < 4) {
      return;
    }
    in.markReaderIndex();
    int len = in.readInt();
    if (in.readableBytes() < len) {
      in.resetReaderIndex();
      return;
    }
    byte[] data = new byte[len];
    in.readBytes(data);
    try {
      out.add(serializer.deserialize(data, clz));
    } catch (Exception e) {
      log.error("decode error, in:{} ", in, e);
    }
  }
}
