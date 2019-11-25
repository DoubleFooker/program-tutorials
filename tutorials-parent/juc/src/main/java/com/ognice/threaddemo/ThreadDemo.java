package com.ognice.threaddemo;

import java.util.concurrent.*;

public class ThreadDemo {
    public static void main(String[] args) {
        // 方式一
        RunnableDemo runnableDemo = new RunnableDemo();
        new Thread(runnableDemo).start();
        // 方式二
        Thread threadRunning = new Thread(() -> System.out.println("new thread running"));
        threadRunning.start();
        new ThreadExtendDemo().start();
        // 方式三
        ExecutorService threadPool = Executors.newSingleThreadExecutor();
        Future<String> future = threadPool.submit(new CallableDemo());
        try {
            System.out.println(future.get());
        } catch (Exception e) {
            // ignore
        } finally {
            threadPool.shutdown();
        }
        //方式四
        // 构建一个线程池 参数说明
        // 核心线程数，最大线程数，空闲线程的存活时间，时间单位，用于缓存任务的阻塞队列，线程池工厂达到最大线程数的拒绝策略
        ThreadPoolExecutor
                threadPoolExecutor = new ThreadPoolExecutor(2, 4, 3,
                TimeUnit.SECONDS, new ArrayBlockingQueue<Runnable>(3),
                Executors.defaultThreadFactory(),
                new ThreadPoolExecutor.DiscardOldestPolicy());
        threadPoolExecutor.execute(() -> {
            System.out.println("Thread pool create thread running");
        });
        threadPoolExecutor.shutdown();
    }
}
