package com.ognice.demo;

import java.util.concurrent.atomic.AtomicInteger;

/**
 * 描述:写点注释吧
 * StackOverflowError测试，栈溢出
 * 线程请求的【栈深度】大于虚拟机所允许的最大深度，将抛出StackOverflowError
 * 递归导致栈深度太大导致的
 *
 * @author dbfk
 * @sine 2018/8/24
 */
public class SOFTest {
    public static void main(String[] args) {
        new Thread(new SOFClass(), "Sub thread").start();

    }

    private static class SOFClass implements Runnable {

        private AtomicInteger count = new AtomicInteger(0);

        @Override
        public void run() {
            try {
                recursiveMethod();
            } catch (Throwable throwable) {
                throwable.printStackTrace();
            } finally {
                System.out.println("递归调用次数：" + "--" + this.count);

            }

        }

        public void recursiveMethod() {
            this.count.incrementAndGet();
            recursiveMethod();
        }
    }
}
