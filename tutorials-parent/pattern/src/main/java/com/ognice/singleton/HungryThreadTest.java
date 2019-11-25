package com.ognice.singleton;

import java.util.concurrent.CountDownLatch;

public class HungryThreadTest {
    public static void main(String[] args) {
        /**
         * 启动多线程 查看获取的对象是否一致
         * 在相同时间点 获取的对象是否一致
         */
        Hungry.getInstance();
        int count = 100;
        CountDownLatch countDownLatch = new CountDownLatch(count);

        for (int i = 1; i <= count; i++) {

            new Thread(() -> {
                Hungry.getInstance();

                countDownLatch.countDown();
            }).start();
        }
        try {
            countDownLatch.await();
        } catch (InterruptedException e) {
            e.printStackTrace();
        }

    }
}
