package com.linran.handler;

import static io.netty.buffer.Unpooled.EMPTY_BUFFER;

import com.linran.codec.RpcRequest;
import com.linran.codec.RpcResponse;
import com.linran.connect.ConnectManager;
import com.linran.protocol.RpcProtocol;
import io.netty.channel.Channel;
import io.netty.channel.ChannelFuture;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import io.netty.handler.timeout.IdleStateEvent;
import java.net.SocketAddress;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RpcClientHandler extends SimpleChannelInboundHandler<RpcResponse> {

  private static final Logger log = LoggerFactory.getLogger(RpcClientHandler.class);
  private final Map<String, RpcFuture> PENDING_RPC_MAP = new ConcurrentHashMap<>();
  private volatile Channel channel;
  private SocketAddress remoteAddress;
  private RpcProtocol rpcProtocol;


  @Override
  protected void channelRead0(ChannelHandlerContext ctx, RpcResponse response) throws Exception {
    String requestId = response.getRequestId();
    log.debug("Recv response: {}", response);
    RpcFuture future = PENDING_RPC_MAP.get(requestId);
    if (future != null) {
      PENDING_RPC_MAP.remove(requestId);
      future.done(response);
    } else {
      log.warn("Can not get pending response for request id: " + requestId);
    }
  }

  @Override
  public void channelActive(ChannelHandlerContext ctx) throws Exception {
    super.channelActive(ctx);
    this.remoteAddress = this.channel.remoteAddress();
  }

  @Override
  public void channelInactive(ChannelHandlerContext ctx) throws Exception {
    super.channelInactive(ctx);
    ConnectManager.getInstance().removeHandler(rpcProtocol);
  }

  @Override
  public void channelRegistered(ChannelHandlerContext ctx) throws Exception {
    super.channelRegistered(ctx);
    this.channel = ctx.channel();
  }

  @Override
  public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
    super.exceptionCaught(ctx, cause);
    log.error("Client exceptionCaught " + cause.getMessage());
    ctx.close();
  }

  @Override
  public void userEventTriggered(ChannelHandlerContext ctx, Object evt) throws Exception {
    if (evt instanceof IdleStateEvent) {
      sendRequest(RpcRequest.PING);
      log.debug("Client ping , remote:{}", remoteAddress);
    } else {
      super.userEventTriggered(ctx, evt);
    }
  }

  public void close() {
    channel.writeAndFlush(EMPTY_BUFFER)
        .addListener(ChannelFutureListener.CLOSE);
  }

  public RpcFuture sendRequest(RpcRequest request) {
    RpcFuture future = new RpcFuture(request);
    PENDING_RPC_MAP.put(request.getRequestId(), future);
    try {
      ChannelFuture channelFuture = channel.writeAndFlush(request).sync();
      if (!channelFuture.isSuccess()) {
        log.error("Send request {}, error", request);
      }
    } catch (InterruptedException e) {
      log.error("Send request exception", e);
    }
    return future;
  }

  public void setRpcProtocol(RpcProtocol rpcProtocol) {
    this.rpcProtocol = rpcProtocol;
  }
}
