package com.ognice.reentrantlockdemo;

import java.util.concurrent.TimeUnit;
import java.util.concurrent.locks.ReentrantLock;
import java.util.concurrent.locks.ReentrantReadWriteLock;

public class ReentranLockDemo {
    // 测试两次锁，只执行一次unlock
    private static void testDoublockLock(){
        ReentrantLock reentrantLock=new ReentrantLock();
        new Thread(()->{
            reentrantLock.lock();
            System.out.println("getLock1");

            try {
                reentrantLock.lock();
                System.out.println("getLock2");
                TimeUnit.SECONDS.sleep(5);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }finally {
                reentrantLock.unlock();
                System.out.println("unlock");
            }
        }).start();
        new Thread(()->{
            reentrantLock.lock();
            System.out.println("getLock");
            reentrantLock.unlock();
        }).start();
    }
    private static void readWriteLock(){
        ReentrantReadWriteLock reentrantReadWriteLock=new ReentrantReadWriteLock();
        ReentrantReadWriteLock.ReadLock readLock = reentrantReadWriteLock.readLock();
        ReentrantReadWriteLock.WriteLock writeLock = reentrantReadWriteLock.writeLock();
        new Thread(()->{
readLock.lock();
        }).start();
    }
    public static void main(String[] args) {

    }
}
