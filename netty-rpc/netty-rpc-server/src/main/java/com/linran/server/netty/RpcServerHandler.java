package com.linran.server.netty;

import com.google.common.annotations.VisibleForTesting;
import com.linran.codec.RpcRequest;
import com.linran.codec.RpcResponse;
import com.linran.utils.ServiceUtils;
import io.netty.channel.ChannelFutureListener;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import java.util.Map;
import java.util.concurrent.ThreadPoolExecutor;
import lombok.Setter;
import net.sf.cglib.core.Signature;
import net.sf.cglib.reflect.FastClass;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public class RpcServerHandler extends SimpleChannelInboundHandler<RpcRequest> {

  private static final Logger log = LoggerFactory.getLogger(RpcServerHandler.class);


  private final Map<String, Object> handleMap;
  private final ThreadPoolExecutor threadPoolExecutor;

  public RpcServerHandler(
      final Map<String, Object> handleMap,
      final ThreadPoolExecutor threadPoolExecutor
  ) {
    this.handleMap = handleMap;
    this.threadPoolExecutor = threadPoolExecutor;
  }

  @Override
  protected void channelRead0(ChannelHandlerContext ctx, RpcRequest req) throws Exception {
    threadPoolExecutor.execute(() -> {
      log.info("request id :{}", req.getRequestId());
      RpcResponse resp = new RpcResponse();
      resp.setRequestId(req.getRequestId());
      try {
        Object result = handle(req);
        resp.setResult(result);
      } catch (Throwable throwable) {
        resp.setError(throwable.toString());
        log.error("handle server method error", throwable);
      }
      ctx.writeAndFlush(resp)
          .addListener((ChannelFutureListener) channelFuture -> log.info("netty sending response " + req.getRequestId()));
    });
  }

  @VisibleForTesting
  public Object handle(RpcRequest req) throws Throwable {
    String className = req.getClassName();
    String version = req.getVersion();
    String serviceKey = ServiceUtils.getKey(className, version);
    Object serviceBean = handleMap.get(serviceKey);
    if (serviceBean == null) {
      log.error("can't find bean is :{}, version:{}", className, version);
      return null;
    }
    Class<?> clz = serviceBean.getClass();
    String methodName = req.getMethodName();
    Class<?>[] paramTypes = req.getParamTypes();
    Object[] params = req.getParams();
    FastClass fastClass = FastClass.create(clz);
    int methodIndex = fastClass.getIndex(methodName, paramTypes);
    return fastClass.invoke(
        methodIndex,
        serviceBean,
        params
    );
  }
}
