package com.ognice.juc;

import java.util.concurrent.CountDownLatch;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class CountDownLatchTest {
    public static void main(String[] args) {
        final CountDownLatch latch = new CountDownLatch(2);
        LatchDemo ld = new LatchDemo(latch);
        long start = System.currentTimeMillis();
        // 创建10个线程
        for (int i = 0; i < 2; i++) {
            new Thread(ld).start();
        }
        try {
            latch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

        long end = System.currentTimeMillis();

        System.out.println("耗费时间为:" + (end - start));
    }
}

class LatchDemo implements Runnable {
    private CountDownLatch latch;

    // 有参构造器
    public LatchDemo(CountDownLatch latch) {
        this.latch = latch;
    }

    public void run() {

        synchronized (this) {
            try {
                // 打印50000以内的偶数
                for (int i = 0; i < 2; i++) {
                    if (i % 2 == 0) {
                        System.out.println(i);
                    }
                }
            } finally {
                // 线程数量递减
                latch.countDown();
            }
            while (true) {
            }
        }
    }
}