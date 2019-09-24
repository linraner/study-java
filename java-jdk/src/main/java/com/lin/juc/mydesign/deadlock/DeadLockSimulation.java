package com.lin.juc.mydesign.deadlock;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-24 15:31
 * @Description: 模拟一个死锁样例
 **/
public class DeadLockSimulation {
    private static String A = "a";
    private static String B = "b";

    public static void main(String[] args) {
//        new ScanDeadLock().scanDeadLock();
        new DeadLockSimulation().deadLock();
    }

    private void deadLock() {
        Thread t1 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (A) {
                    try {
                        Thread.sleep(1000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    synchronized (B) {
                        System.out.println("t1");
                    }
                }
            }
        });

        Thread t2 = new Thread(new Runnable() {
            @Override
            public void run() {
                synchronized (B) {
                    synchronized (A) {
                        System.out.println("t2");
                    }
                }
            }
        });

        t1.start();
        t2.start();
    }
}
