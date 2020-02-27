package com.lin.interview.bytedance;

import lombok.AllArgsConstructor;

public class Thread1 {

  public static void main(String[] args) {
    String[] b = {"a", "b", "c", "d"};
    String[] a = {"1", "2", "3", "4"};
    Object object = new Object();
    new PrintThread<>(a, object).start();
    new PrintThread<>(b, object).start();
  }

  @AllArgsConstructor
  private static class PrintThread<T> extends Thread {

    private T[] nums;
    private Object object;

    public void run() {
      try {
        for (int i = 0; i < nums.length; i++) {
          synchronized (object) {
            System.out.println(Thread.currentThread().getName() + " " + nums[i]);
            object.notify();
            object.wait();
          }
        }
      } catch (Exception e) {
        e.printStackTrace();
      } finally {
        synchronized (object) {
          object.notify();
        }
      }
    }
  }

}
