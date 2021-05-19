package com.lin.juc.commondemo;

import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.ConcurrentSkipListSet;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.CyclicBarrier;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 09:12
 * @Description UUID并行情况下可能会出现重复
 **/
public class ThreadUUIDDemo {

  final static int capacity = 100;
  static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
  static CountDownLatch countDownLatch = new CountDownLatch(capacity + 1);
  static ExecutorService executorService = Executors.newFixedThreadPool(5);

  //    static Set<String> set = new HashSet<>(capacity);
  static ConcurrentSkipListSet set = new ConcurrentSkipListSet();

  public static void main(String[] args) throws InterruptedException {
    ThreadUUIDDemo threadUUIDDemo = new ThreadUUIDDemo();
    threadUUIDDemo.testRun2();

    System.out.println("size: " + set.size());
  }

  private void testRun1() {
    for (int i = 0; i < capacity; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          try {
            cyclicBarrier.await();
            String UUID = java.util.UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println("thread " + Thread.currentThread().getName() + " UUID: " + UUID + " do time: " + System.currentTimeMillis());
          } catch (InterruptedException | BrokenBarrierException e) {
            e.printStackTrace();
          }

        }
      });
    }
    executorService.shutdown();
  }

  private void testRun2() throws InterruptedException {
    for (int i = 0; i < capacity; i++) {
      executorService.submit(new Runnable() {
        @Override
        public void run() {
          try {
            String UUID = java.util.UUID.randomUUID().toString().replaceAll("-", "");
            System.out.println("thread " + Thread.currentThread().getName() + " UUID: " + UUID + " do time: " + System.currentTimeMillis());
            set.add(UUID);
            countDownLatch.await(1, TimeUnit.SECONDS);
          } catch (InterruptedException e) {
            e.printStackTrace();
          }
        }
      });
      countDownLatch.countDown();
    }
    executorService.shutdown();
  }
}
