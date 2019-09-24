# Java线程中的死锁

> 死锁是指两个或两个以上的进程在执行过程中，由于竞争资源或者由于彼此通信而造成的一种阻塞的现象，若无外力作用，它们都将无法推进下去。此时称系统处于死锁状态或系统产生了死锁，这些永远在互相等待的进程称为死锁进程。

Java线程中我们构造两个相互等待对方释放资源的线程就构成了死锁。

代码：

```Java
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
```

运行上边的代码产生死锁。JDK自带的工具有jconsole、jvisualvm、jstack等。

## jvisualvm

运行jvisualvm如下：

![](https://i.loli.net/2019/09/24/vKgWpnUO8Je7uwE.png)

发现提示检测到死锁。

## jstack

查看进程号：

```shell
jps // 查看Java任务进程号
2784 Jps
8672 Main
18068 Launcher
12520 KotlinCompileDaemon
16776 DeadLockSimulation
9576
16668 RemoteMavenServer
```

运行jstack。

```shell
jstack [进程号] // 查看当前进程堆栈

Found one Java-level deadlock:                                                                           
=============================                                                                            
"Thread-0":                                                                           
  waiting to lock monitor 0x0000012f11b53e80 (object 0x0000000741d763e8, a java.lang.String),            
  which is held by "Thread-1"                                                        
"Thread-1":                                                          
  waiting to lock monitor 0x0000012f11b51e80 (object 0x0000000741d76400, a java.lang.String),            
  which is held by "Thread-0"                                                        
Java stack information for the threads listed above:                                                     
===================================================                                   
"Thread-0":                                                                                              
        at com.lin.juc.mydesign.DeadLockSimulation$1.run(DeadLockSimulation.java:28)                     
        - waiting to lock <0x0000000741d763e8> (a java.lang.String)                                      
        - locked <0x0000000741d76400> (a java.lang.String)                                               
        at java.lang.Thread.run(java.base@11.0.1/Thread.java:834)                                        
"Thread-1":                                                                          
        at com.lin.juc.mydesign.DeadLockSimulation$2.run(DeadLockSimulation.java:39)                     
        - waiting to lock <0x0000000741d76400> (a java.lang.String)                  
        - locked <0x0000000741d763e8> (a java.lang.String)                           
        at java.lang.Thread.run(java.base@11.0.1/Thread.java:834)                   
Found 1 deadlock.          
```

其他工具类似。

