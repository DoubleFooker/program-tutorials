package com.ognice.singleton;

import java.util.concurrent.CountDownLatch;

/**
 * 懒汉式
 * 使用时在创建
 * 存在线程安全问题
 */
public class LazyThreadTest {
    public static void main(String[] args) {
        /**
         * 启动多线程 查看获取的对象是否一致
         * 在相同时间点 获取的对象是否一致
         */
        int count = 400;
        CountDownLatch countDownLatch = new CountDownLatch(count);
        for (int i = 1; i <= count; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                    //Object obj = Lazy.getInstance();
                    Object obj = Lazy.getInstanceByLazyHolder();
                    //Object obj = Lazy.getInstanceSyc();
                    System.out.println(System.currentTimeMillis() + "-->" + obj);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                } finally {

                }
            }).start();
            countDownLatch.countDown();

        }


    }

}
