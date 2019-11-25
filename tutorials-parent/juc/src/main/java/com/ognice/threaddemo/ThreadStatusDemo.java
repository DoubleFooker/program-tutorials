package com.ognice.threaddemo;

import java.util.concurrent.TimeUnit;

public class ThreadStatusDemo {


    public static void main(String[] args) {
        Thread thread = new Thread(() -> {
            Thread.currentThread().isInterrupted();
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(100);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }, "Time Waiting Thread");
        thread.start();
        thread.interrupt();


        new Thread(() -> {
            while (true) {
                synchronized (ThreadStatusDemo.class) {
                    try {
                        ThreadStatusDemo.class.wait();
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                }
            }
        }, "Waiting Thread").start();
        new Thread(() -> {
            synchronized (ThreadStatusDemo.class) {
                while (true) {
                }

            }
        }, "Blocked Thread Holder").start();
        new Thread(() -> {
            synchronized (ThreadStatusDemo.class) {
                while (true) {

                }
            }
        }, "Blocked Thread").start();
    }
}
