package com.lin.basic;

import com.google.common.util.concurrent.ThreadFactoryBuilder;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.ExecutionException;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.Future;
import java.util.concurrent.Semaphore;
import java.util.concurrent.ThreadPoolExecutor;
import java.util.concurrent.TimeUnit;
import java.util.concurrent.TimeoutException;

public class Main {

  static Semaphore semaphore = new Semaphore(2);


  public static void main(String[] args) throws InterruptedException {
//    testCall();

//    testWhile(true);

    testExecService();
  }

  static void testWhile(boolean running) throws InterruptedException {
    while (running) {
      while (running) {
        Thread.sleep(1000);
        System.out.println("test 1");
        running = false;
      }
      Thread.sleep(2000);
      System.out.println("test 2");
      Thread.sleep(1000);
      running = true;
    }
  }

  static void testExecService() {
    ThreadPoolExecutor poolExecutor = new ThreadPoolExecutor(
        1,
        1,
        0,
        TimeUnit.SECONDS,
        new ArrayBlockingQueue<>(1),
        new ThreadFactoryBuilder().setNameFormat("test" + "-%d").build(),
        new ThreadPoolExecutor.DiscardPolicy());

    List<Future<?>> futures = new ArrayList<>(10);
    for (int i = 0; i < 10; i++) {
      Future<?> f = poolExecutor.submit(() -> testTask());
      futures.add(f);
    }

    futures.forEach(x -> {
      try {
        System.out.println(x.get(100, TimeUnit.MILLISECONDS));
      } catch (InterruptedException | ExecutionException | TimeoutException e) {
        System.out.println("time out");
      }
    });

    System.out.println("exec end");

  }

  static String testTask() {
    System.out.println("exec ing");
    try {
      Thread.sleep(100);
      return "test";
    } catch (InterruptedException e) {
      e.printStackTrace();
      return "except";
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
