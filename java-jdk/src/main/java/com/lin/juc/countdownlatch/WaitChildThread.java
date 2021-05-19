package com.lin.juc.countdownlatch;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-04 10:38
 * @Description: 线程池等待子线程完成任务
 **/
public class WaitChildThread {

  public static void main(String[] args) throws InterruptedException {
    long start = System.currentTimeMillis();
    ExecutorService executorService = Executors.newFixedThreadPool(4);
    for (int i = 0; i < 4; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          Thread t = Thread.currentThread();
          System.out.println("Thread id: " + t.getId() + " Thread ame:" + t.getName());
        }
      });
    }
    executorService.shutdown();
    while (!executorService.awaitTermination(5, TimeUnit.SECONDS)) {
      // 用于等待子线程 每5s循环一次
    }
        /*
        while (!executorService.awaitTermination(5, TimeUnit.SECONDS)) //不过有超时机制设置每隔多久循环一次
        <=>
        while (!executorService.isTerminated())
        */
    long end = System.currentTimeMillis();
    System.out.println("子线程执行时间: " + (end - start));

  }
}
