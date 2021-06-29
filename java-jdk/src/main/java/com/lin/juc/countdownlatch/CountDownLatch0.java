package com.lin.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

public class CountDownLatch0 {

  public static void main(String[] args) throws InterruptedException {
    int size = 12;
    int total = 12;
    final CountDownLatch countDownLatch = new CountDownLatch(size);
    ExecutorService executorService = Executors.newFixedThreadPool(size);
    for (int i = 0; i < 12; i++) {
      int finalI = i;
      executorService.submit(() -> {
        try {
          System.out.println("handle " + finalI);
          Thread.sleep(1000);
          countDownLatch.countDown();
        } catch (InterruptedException e) {
          e.printStackTrace();
        }
      });
    }
    countDownLatch.await();
    executorService.shutdownNow();
    System.out.println("done ");
  }


}
