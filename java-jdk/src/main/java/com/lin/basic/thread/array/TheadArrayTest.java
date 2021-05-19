package com.lin.basic.thread.array;

import java.time.Duration;
import java.time.Instant;
import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class TheadArrayTest {

  private static final Task task = new Task();
  public static int buffSize = 1_000;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(200);
    Instant begin = Instant.now();
    loadData(executorService);
    executorService.shutdown();
    while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
    }
    // 100w/1000/200*1= 5s
    System.out.println("task finish: " + Duration.between(begin,
        Instant.now()).toMillis() + "ms");
  }

  static void loadData(ExecutorService executorService) {
    List<Long> buff = new ArrayList<>(buffSize);
    for (int i = 0; i < 1_000_000; i++) {
      saveBuff(i, buff, executorService);
    }
  }

  static void saveBuff(long src, List<Long> buff, ExecutorService executorService) {
    buff.add(src);
    if (buff.size() >= buffSize) {
      List<Long> target = new ArrayList<>(buff);
      executorService.submit(() -> task.writeSync(target));
      buff.clear();
    }
  }
}

