package com.lin.basic.stopwatch;


import com.google.common.base.Stopwatch;

import java.util.concurrent.TimeUnit;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-23 20:57
 * @Description:性能检测工具 这个是
 **/
public class StopWatchDemo {
    public static void main(String[] args) throws InterruptedException {
        Stopwatch stopwatch = Stopwatch.createUnstarted();
        stopwatch.start();
        Thread.sleep(1000);
        stopwatch.stop();
        Long time = stopwatch.elapsed(TimeUnit.MICROSECONDS);
        System.out.println(stopwatch.elapsed(TimeUnit.SECONDS));
        System.out.println("time = " + time);

        Long start = System.currentTimeMillis();
        Thread.sleep(1000);
        Long end = System.currentTimeMillis();
        System.out.println("this sleep: " + (Long)(end - start));
    }
}
