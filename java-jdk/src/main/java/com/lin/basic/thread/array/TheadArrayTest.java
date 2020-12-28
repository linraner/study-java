package com.lin.basic.thread.array;

import java.util.ArrayList;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;
import org.apache.commons.lang3.time.StopWatch;

public class TheadArrayTest {

  private static final Task task = new Task();
  public static int buffSize = 1_000;

  public static void main(String[] args) throws InterruptedException {
    ExecutorService executorService = Executors.newFixedThreadPool(200);
    StopWatch watch = new StopWatch();
    watch.start();
    loadData(executorService);
    executorService.shutdown();
    while (!executorService.awaitTermination(10, TimeUnit.SECONDS)) {
    }
    watch.stop();
    // 100w/1000/200*1= 5s
    System.out.println("task finish: " + watch.getTime() / 1000 + "s");
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

