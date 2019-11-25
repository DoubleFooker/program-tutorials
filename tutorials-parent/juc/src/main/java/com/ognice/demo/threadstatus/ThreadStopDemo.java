package com.ognice.demo.threadstatus;

import java.util.concurrent.TimeUnit;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/17
 */
public class ThreadStopDemo {
    public static void main(String[] args) throws InterruptedException {
        Thread thread = new Thread(() -> {
            while (!Thread.currentThread().isInterrupted()) {
            }
            System.out.println("线程已终止");
        }, "interrupted");
        thread.start();
        TimeUnit.SECONDS.sleep(1);
        thread.interrupt();
    }
}
