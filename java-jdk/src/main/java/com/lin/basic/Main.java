package com.lin.basic;

import java.util.Date;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Semaphore;

public class Main {

  static Semaphore semaphore = new Semaphore(2);


  public static void main(String[] args) throws InterruptedException {
//    testCall();

    testWhile(false);

  }

  static void testWhile(boolean running) throws InterruptedException {
    while (true) {
      while (running) {
        while (running) {
          Thread.sleep(1000);
          System.out.println("test 1");
          running = false;
        }
        Thread.sleep(2000);
        running = false;
        System.out.println("test 2");
      }
    }
  }

  static void testCall() throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(2);

    for (int i = 0; i < 100; i++) {
//      System.out.println("exec i" + i);
      semaphore.acquire();
      executorService.submit(Main::call);
    }
    System.out.println("main over!");
  }

  static void call() {
    try {
//      semaphore.acquire();
      Thread.sleep(1000L);
      semaphore.release();
      System.out.println(new Date() + "call thread: " + Thread.currentThread().getId());
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
