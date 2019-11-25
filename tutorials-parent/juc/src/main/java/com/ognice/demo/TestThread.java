package com.ognice.demo;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class TestThread extends Thread {

    @Override
    public void run() {
        System.out.println("继承Thread线程启动！");

    }

    public static synchronized void main(String[] args) {
        TestThread testThread = new TestThread();
        testThread.start();
    }
}
