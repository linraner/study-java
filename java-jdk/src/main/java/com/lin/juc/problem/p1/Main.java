package com.lin.juc.problem.p1;

import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Paths;
import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-03 10:41
 * @Description: 一个方法里面 同时起三个线程 读取同一个文件 假定返回文件的行数 三个线程返回结果之后
 * 方法再返回结果 你来怎么保证线程正确执行
 **/
public class Main {
    static CountDownLatch latch = new CountDownLatch(4);

    public static void main(String[] args) throws IOException, InterruptedException {
        String path = "D:/program/java/my/java-study/java-jdk/src/main/java/com/lin/juc/problem/p1/test.txt";

//        int ans = getFileLineNum(path);
//        latch.await();


        int ans = getFileLineNum1(path);
//        latch.await(3,TimeUnit.SECONDS);
        System.out.println(ans);
    }

    static int getFileLineNum(String path) throws IOException {
        String file = Files.readString(Paths.get(path));
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t1 get line is : " + file.lines().count());
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t2 get line is : " + file.lines().count());
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    System.out.println("t3 get line is : " + file.lines().count());
                    latch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        });
        t1.start();
//        latch.countDown();
        t2.start();
//        latch.countDown();
        t3.start();
//        latch.countDown();
//        latch.countDown();
//        latch.countDown();
        return (int) file.lines().count();
    }

    // join
    static int getFileLineNum1(String path) throws IOException, InterruptedException {
        String file = Files.readString(Paths.get(path));
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t1 get line is : " + file.lines().count());
            }
        });
        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t2 get line is : " + file.lines().count());
            }
        });
        Thread t3 = new Thread(new Runnable() {
            @Override
            public void run() {
                System.out.println("t3 get line is : " + file.lines().count());
            }
        });
        t1.start();
        t2.start();
        t3.start();

//        t1.join();
//        t2.join();
//        t3.join();
        return (int) file.lines().count();
    }
}