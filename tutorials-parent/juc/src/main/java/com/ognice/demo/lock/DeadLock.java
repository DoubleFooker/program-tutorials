package com.ognice.demo.lock;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/17
 */
public class DeadLock {
    //死锁demo
    private static Object A = new Object();
    private static Object B = new Object();

    public static void main(String[] args) {

        new Thread(() -> {
            synchronized (A) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (B) {
                    System.out.println(B);
                }
            }
        }, "DeadLockThread-1").start();
        new Thread(() -> {
            synchronized (B) {
                try {
                    Thread.sleep(2000);
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                synchronized (A) {
                    System.out.println(B);
                }
            }
        }, "DeadLockThread-2").start();
    }
}
