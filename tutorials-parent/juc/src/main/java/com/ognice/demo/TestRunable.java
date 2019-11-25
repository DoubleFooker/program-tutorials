package com.ognice.demo;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class TestRunable implements Runnable {
    @Override
    public void run() {
        System.out.println("测试runable线程！");
    }

    public static void main(String[] args) {
        new Thread(new TestRunable()).start();
    }
}
