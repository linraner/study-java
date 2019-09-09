package com.lin.juc;

import java.util.concurrent.*;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-09 09:12
 * @Description UUID并行情况下可能会出现重复
 **/
public class ThreadUUIDDemo {
    static CyclicBarrier cyclicBarrier = new CyclicBarrier(10);
    static CountDownLatch countDownLatch = new CountDownLatch(10);

    public static void main(String[] args) {
        ThreadUUIDDemo threadUUIDDemo = new ThreadUUIDDemo();
        threadUUIDDemo.testRun();
    }

    private void testRun() {
        ExecutorService executorService = Executors.newFixedThreadPool(10);
        for (int i = 0; i < 10; i++) {
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
}
