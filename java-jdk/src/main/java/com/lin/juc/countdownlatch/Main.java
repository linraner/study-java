package com.lin.juc.countdownlatch;

import java.util.concurrent.CountDownLatch;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-03 22:57
 * @Description:
 **/
public class Main {
    public static void main(String[] args) {
//        Stopwatch stopwatch = Stopwatch.createStarted();
//        stopwatch.start();
//        // 创建一个初始值为5的倒数计数器
//        CountDownLatch countDownLatch = new CountDownLatch(5);
//        for (int i = 0; i < 5; i++) {
//            Thread thread = new TestThread(countDownLatch);
//            thread.start();
//        }
//        try {
//            // 阻塞当前线程，直到倒数计数器倒数到0
//            countDownLatch.await();
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
//        stopwatch.stop();
//        System.out.println("子线程执行时长：" + stopwatch.elapsed());
        long start = System.currentTimeMillis();

        // 创建一个初始值为5的倒数计数器
        CountDownLatch countDownLatch = new CountDownLatch(5);
        for (int i = 0; i < 5; i++) {
            Thread thread = new TestThread(countDownLatch);
            thread.start();
        }

        try {
            // 阻塞当前线程，直到倒数计数器倒数到0
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();
        System.out.println("子线程执行时长：" + (end - start));
    }
}
