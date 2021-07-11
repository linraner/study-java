package com.linran.handler;

import com.linran.client.spring.RpcClient;
import com.linran.codec.RpcRequest;
import com.linran.codec.RpcResponse;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.Future;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;
import java.util.concurrent.locks.AbstractQueuedSynchronizer;
import java.util.concurrent.locks.ReentrantLock;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

public final class RpcFuture implements Future<Object> {

  private static final Logger log = LoggerFactory.getLogger(RpcFuture.class);
  private final List<AsyncCallback> pendingCallbacks = new ArrayList<>();
  private final ReentrantLock lock = new ReentrantLock();
  private final Sync sync;
  private final RpcRequest request;
  private final long startAt;
  private final long responseTimeThreshold = 5000;
  private RpcResponse response;

  public RpcFuture(RpcRequest request) {
    this.request = request;
    this.sync = new Sync();
    this.startAt = System.currentTimeMillis();
  }

  @Override
  public boolean cancel(boolean mayInterruptIfRunning) {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isCancelled() {
    throw new UnsupportedOperationException();
  }

  @Override
  public boolean isDone() {
    return sync.isDone();
  }

  @Override
  public Object get() throws InterruptedException, ExecutionException {
    sync.acquire(1);
    if (this.response != null) {
      return this.response.getResult();
    }
    return null;
  }


  public void done(RpcResponse response) {
    this.response = response;
    sync.release(1);
    invokeCallback();
    long costTime = System.currentTimeMillis() - startAt;
    if (costTime > responseTimeThreshold) {
      log.warn("Repose cost too slow, request:{}", request);
    }
  }

  private void invokeCallback() {
    lock.lock();
    try {
      for (AsyncCallback callback : pendingCallbacks) {
        runCallback(callback);
      }
    } finally {
      lock.unlock();
    }
  }


  public RpcFuture addCallback(AsyncCallback callback) {
    lock.lock();
    try {
      if (isDone()) {
        runCallback(callback);
      } else {
        pendingCallbacks.add(callback);
      }
    } finally {
      lock.unlock();
    }
    return this;
  }

  private void runCallback(final AsyncCallback callback) {
    final RpcResponse response = this.response;
    RpcClient.submit(() -> {
      if (!response.isError()) {
        callback.success(response.getResult());
      } else {
        callback.fail(new RuntimeException("Response error", new Throwable(response.getError())));
      }
    });
  }


  @Override
  public Object get(long timeout, TimeUnit unit) throws InterruptedException, ExecutionException, TimeoutException {
    boolean success = sync.tryAcquireNanos(1, unit.toNanos(timeout));
    if (success) {
      return get();
    } else {
      throw new RuntimeException("Get response timeout, request: " + request.toString());
    }
  }

  static
  class Sync extends AbstractQueuedSynchronizer {

    private static final long serialVersionUID = 1L;

    private final int done = 1;

    @Override
    protected boolean tryAcquire(int arg) {
      return getState() == done;
    }

    @Override
    protected boolean tryRelease(int arg) {
      int pending = 0;
      if (getState() == pending) {
        return compareAndSetState(pending, done);
      } else {
        return true;
      }
    }

    public boolean isDone() {
      return getState() == done;
    }
  }

}
