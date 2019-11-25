package com.ognice.threadpooldemo;

import java.util.concurrent.*;

public class ThreadPoolDemo {
    // 动态创建，每个空闲线程会在60秒后被回收
    static ExecutorService newCachedThreadPool = Executors.newCachedThreadPool();
    // 固定线程数的线程池
    static ExecutorService newFixedThreadPool = Executors.newFixedThreadPool(10);
    // 定时器线程
    static ScheduledExecutorService newScheduledThreadPool = Executors.newScheduledThreadPool(10);
    // 单线程，只有一个核心线程的线程池
    static ExecutorService newSingleThreadExecutor = Executors.newSingleThreadExecutor();
    // fork/join线程池
    static ExecutorService newWorkStealingPool = Executors.newWorkStealingPool();

    public static void main(String[] args) {
        noCoreThread();
    }

    // 测试线程大于核心线程数，非核心线程是否运行,不运行的？？ 添加的时候又加了worker？
    // 超过等待队列长度 则进行拒绝，如果没设置拒绝策略 则报错
    private static void noCoreThread() {
        ThreadPoolExecutor threadPoolExecutor = new ThreadPoolExecutor(3, 5,
                0L, TimeUnit.MILLISECONDS,
                new LinkedBlockingQueue<Runnable>(2));
        for (long i = 0; i < 10; i++) {
            try {


                threadPoolExecutor.execute(() -> {
                    System.out.println(Thread.currentThread().getName());
                    try {
                        TimeUnit.SECONDS.sleep(100000);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                });
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        System.out.println("add end");
        threadPoolExecutor.shutdown();
    }
}
