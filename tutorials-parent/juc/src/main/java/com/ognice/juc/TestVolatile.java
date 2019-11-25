package com.ognice.juc;

import sun.misc.Unsafe;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/18
 */
public class TestVolatile {
    private static volatile int count = 0;
    private static CountDownLatch countDownLatch = new CountDownLatch(1000);
    private static Object object = new Object();
    private static final Unsafe unsafe = Unsafe.getUnsafe();

    public static void main(String[] args) throws InterruptedException {


        for (int i = 1; i <= 1000; i++) {
            new Thread(() -> {
                try {
                    Thread.sleep(1000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                int oldcount = count;
                countDownLatch.countDown();
                for (; ; ) {
                    if (count == oldcount) {
                        count++;
                        System.out.println(oldcount + "--->" + count);
                        return;
                    }
                }
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        System.out.println(count);

    }
}
