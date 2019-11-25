package com.ognice.juc;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

/**
 * 当有线程在写的时候，读锁和写锁都阻塞
 */
public class ReentranReadWriteLockDemo {
    static ReentrantReadWriteLock lock = new ReentrantReadWriteLock();
    static Lock readLock = lock.readLock();
    static Lock writeLock = lock.writeLock();

    static void get() {
        readLock.lock();
        try {
            System.out.println("get");

        } finally {
            readLock.unlock();
        }
    }

    static void save() {
        writeLock.lock();
        try {
            System.out.println("save");
            try {
                TimeUnit.SECONDS.sleep(2);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        } finally {
            writeLock.unlock();
        }
    }

    public static void main(String[] args) {
        new Thread(() -> {
            ReentranReadWriteLockDemo.save();
        }).start();
        new Thread(() -> {
            ReentranReadWriteLockDemo.get();
        }).start();
    }
}
