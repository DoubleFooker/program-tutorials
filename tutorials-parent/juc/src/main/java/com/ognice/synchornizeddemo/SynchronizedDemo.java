package com.ognice.synchornizeddemo;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class SynchronizedDemo {
    private static Integer count = 0;
    public Integer count2=0;

    // 类锁
    public static synchronized void incLockClazz() {
        System.out.println("lock");
        try {
            TimeUnit.SECONDS.sleep(100);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        count++;
    }
    // 类锁
    public static  void incNolopck() {
        System.out.println("nolock");
        count++;
    }

    //类锁
    public void incLockClazz2() {
        synchronized (SynchronizedDemo.class) {
            count++;
        }
    }

    //对象锁
    public synchronized void incLockObj2() {
        count2++;
    }

    //对象锁
    public void incLockObj() {
        synchronized (this) {
            count2++;
        }
    }

    public static void main(String[] args) throws InterruptedException {
        CountDownLatch countDownLatch=new CountDownLatch(1);
        new Thread(()->{
            SynchronizedDemo.incLockClazz();
        }).start();
        TimeUnit.SECONDS.sleep(1);
        new Thread(()->{
            SynchronizedDemo.incNolopck();
        }).start();



//
//        SynchronizedDemo synchronizedDemo=new SynchronizedDemo();
//        for (int i = 0; i < 1000; i++) {
//            new Thread(() -> {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                incLockClazz();}).start();
//            new Thread(() -> {
//                try {
//                    countDownLatch.await();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                synchronizedDemo.incLockObj();
//            }).start();
//
//        }
//        countDownLatch.countDown();
//        TimeUnit.SECONDS.sleep(5);
//        System.out.println(SynchronizedDemo.count);
//        System.out.println(synchronizedDemo.count2);
    }
}
