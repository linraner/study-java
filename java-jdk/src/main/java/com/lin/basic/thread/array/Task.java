package com.lin.basic.thread.array;

import java.util.Arrays;
import java.util.List;

public class Task {

  public void writeSync(List<Long> src) {
    try {
      Thread.sleep(1000);
      if (src.size() != TheadArrayTest.buffSize) {
        System.out.println("error src :" + Arrays.toString(src.toArray()));
      }
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }
}
