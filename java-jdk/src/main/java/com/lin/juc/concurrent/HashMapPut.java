package com.lin.juc.concurrent;

import java.util.HashMap;
import java.util.Map;
import java.util.concurrent.BrokenBarrierException;
import java.util.concurrent.CyclicBarrier;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-10-16 10:56
 * @Description: 检验HashMap线程不安全 这个地方是有问题的
 **/
public class HashMapPut {
    final static CyclicBarrier cyclicBarrier = new CyclicBarrier(2);

    public static void main(String[] args) {
        final Map<String, String> map = new HashMap<>();
        Runnable runnable1 = () -> {
            try {
                cyclicBarrier.await();
                for (int i = 0; i < 32; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
                System.out.println("run t1 " + System.currentTimeMillis());
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }

        };
        Runnable runnable2 = () -> {
            try {
                cyclicBarrier.await();
                for (int i = 32; i < 64; i++) {
                    map.put(String.valueOf(i), String.valueOf(i));
                }
                System.out.println("run t2 " + System.currentTimeMillis());
            } catch (BrokenBarrierException | InterruptedException e) {
                e.printStackTrace();
            }

        };

        Thread t1 = new Thread(runnable1);
        Thread t2 = new Thread(runnable2);

        t1.start();
        t2.start();

//        map.keySet().forEach(key -> System.out.println(key + " : " + map.get(key)));
        for (String key : map.keySet()) {
            if (!key.equals(map.get(key))) {
                System.out.println(key + " : " + map.get(key));
            }
        }

    }
}
