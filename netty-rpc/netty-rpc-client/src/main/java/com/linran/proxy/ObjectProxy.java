package com.linran.proxy;

import com.linran.codec.RpcRequest;
import com.linran.connect.ConnectManager;
import com.linran.function.SerializableFunction;
import com.linran.handler.RpcClientHandler;
import com.linran.handler.RpcFuture;
import com.linran.utils.ServiceUtils;
import java.lang.reflect.InvocationHandler;
import java.lang.reflect.Method;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class ObjectProxy<T, P> implements InvocationHandler, RpcService<T, P, SerializableFunction<T>> {

  private static final Logger log = LoggerFactory.getLogger(ObjectProxy.class);

  private final Class<T> clz;
  private final String version;

  public ObjectProxy(Class<T> clz, String version) {
    this.clz = clz;
    this.version = version;
  }

  @Override
  public RpcFuture call(String methodName, Object... args) throws Exception {
    String className = this.clz.getName();
    String serviceKey = ServiceUtils.getKey(className, version);
    Class<?>[] paramTypes = new Class[args.length];
    for (int i = 0; i < args.length; i++) {
      paramTypes[i] = args[i].getClass();
    }
    RpcRequest request = RpcRequest.newInvoker(
        className,
        methodName,
        paramTypes,
        args,
        version
    );
    RpcClientHandler handler = ConnectManager.getInstance().getHandle(serviceKey);
    if (log.isDebugEnabled()) {
      log.debug("call request:{}", request);
    }
    return handler.sendRequest(request);
  }

  @Override
  public RpcFuture call(SerializableFunction<T> tSerializableFunction, Object... args) throws Exception {
    String className = this.clz.getName();
    String serviceKey = ServiceUtils.getKey(className, version);
    RpcRequest request = RpcRequest.newInvoker(
        className,
        tSerializableFunction.getName(),
        getParamTypes(args),
        args,
        version
    );
    RpcClientHandler handle = ConnectManager.getInstance().getHandle(serviceKey);
    if (log.isDebugEnabled()) {
      log.debug("call request:{}", request);
    }
    return handle.sendRequest(request);
  }

  @Override
  public Object invoke(Object proxy, Method method, Object[] args) throws Throwable {
    String methodName = method.getName();
    String className = method.getDeclaringClass().getName();
    Class<?>[] parameterTypes = method.getParameterTypes();
    if (method.getDeclaringClass() == Object.class) {
      if ("equals".equals(methodName)) {
        return proxy == args[0];
      }
      if ("hashCode".equals(methodName)) {
        return System.identityHashCode(proxy);
      }
      if ("toString".equals(methodName)) {
        return proxy.getClass().getName() + "@" + Integer.toHexString(System.identityHashCode(proxy))
            + ", with InvocationHandler " + this;
      }
    }

    RpcRequest request = RpcRequest.newInvoker(
        className,
        methodName,
        parameterTypes,
        args,
        version
    );

    if (log.isDebugEnabled()) {
      log.debug("Invoke request :{}", request);
    }
    String serviceKey = ServiceUtils.getKey(className, version);
    RpcClientHandler handler = ConnectManager.getInstance().getHandle(serviceKey);
    RpcFuture future = handler.sendRequest(request);
    return future.get();
  }

  private Class<?>[] getParamTypes(Object... arg) {
    Class<?>[] targets = new Class[arg.length];
    for (int i = 0; i < arg.length; i++) {
      targets[i] = arg[i].getClass();
    }
    return targets;
  }
}
