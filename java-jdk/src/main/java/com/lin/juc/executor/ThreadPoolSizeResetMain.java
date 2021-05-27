package com.lin.juc.executor;

import java.util.concurrent.LinkedBlockingDeque;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;

public class ThreadPoolSizeResetMain {

  private static final int CORE_SIZE = 4;
  private static final int MAX_SIZE = 4;

  private static final Semaphore semaphore = new Semaphore(CORE_SIZE);

  private static final ThreadPoolExecutor executor = new ThreadPoolExecutor(CORE_SIZE, MAX_SIZE, 1000, TimeUnit.SECONDS, new LinkedBlockingDeque<>());

  public static void main(String[] args) throws InterruptedException {

    for (int i = 0; i < Integer.MAX_VALUE; i++) {
      semaphore.acquire();
      executor.submit(ThreadPoolSizeResetMain::handle);
    }
  }

  private static void handle() {
    try {
      Thread.sleep(1000);
    } catch (InterruptedException e) {
      e.printStackTrace();
    } finally {
      semaphore.release();
    }
  }

  // jdk8 bug.
  private static void resetThreadPoolSize(final int newPoolSize) {
    if (newPoolSize > executor.getMaximumPoolSize()) {
      executor.setMaximumPoolSize(newPoolSize);
      executor.setCorePoolSize(newPoolSize);
    } else {
      executor.setCorePoolSize(newPoolSize);
      executor.setMaximumPoolSize(newPoolSize);
    }
  }
}
