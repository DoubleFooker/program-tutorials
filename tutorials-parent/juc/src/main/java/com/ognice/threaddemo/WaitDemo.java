package com.ognice.threaddemo;

import java.util.concurrent.TimeUnit;

public class WaitDemo {
    public static void main(String[] args) {
        Object obj = new Object();
        new Thread(() -> {
            synchronized (obj) {
                try {
                    obj.wait();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
            }
        }).start();
        new Thread(() -> {
            obj.notify();
        }).start();
        Thread xxxx = new Thread(() -> {
            while (true) {
                try {
                    TimeUnit.SECONDS.sleep(1);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println("xxxx");
            }
        });
        xxxx.start();
        Thread.yield();
    }
}
