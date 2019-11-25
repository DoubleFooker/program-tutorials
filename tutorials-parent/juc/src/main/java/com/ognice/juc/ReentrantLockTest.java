package com.ognice.juc;

import java.util.concurrent.locks.ReentrantLock;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class ReentrantLockTest {
    private static int count = 0;
    static ReentrantLock reentrantLock = new ReentrantLock(true);

    public static void main(String[] args) {
        reentrantLock.lock();
        System.out.println("f-" + reentrantLock.getHoldCount());
        System.out.println("f-" + reentrantLock.isLocked());
        try {
            count++;
            testDoubleLock();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
        System.out.println(count);

    }

    private static void testDoubleLock() {
        reentrantLock.lock();
        System.out.println("s-" + reentrantLock.getHoldCount());
        System.out.println("s-" + reentrantLock.isLocked());
        try {
            count++;
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            reentrantLock.unlock();
        }
    }
}
