package com.lin.juc.mydesign;

import java.lang.management.ManagementFactory;
import java.lang.management.ThreadInfo;
import java.lang.management.ThreadMXBean;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

/**
 * @author: linran
 * @email: linran@live.com
 * @create: 2019-09-24 15:56
 * @Description: MXBean用于扫描程序是否存在死锁 扫描存在性能损耗
 **/
public class ScanDeadLock {

    public void scanDeadLock() {
        ThreadMXBean mxBean = ManagementFactory.getThreadMXBean();
        Runnable runnable = () -> {
            long[] ids = mxBean.findDeadlockedThreads();
            System.out.println("扫描死锁...");
            if (ids != null) {
                ThreadInfo[] threadInfos = mxBean.getThreadInfo(ids);
                for (ThreadInfo info : threadInfos) {
                    System.out.println("info = " + info);
                }
            }
        };
        ExecutorService executorService = Executors.newScheduledThreadPool(4);
        executorService.execute(runnable);
    }
}
