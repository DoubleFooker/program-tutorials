package com.ognice.lock;

import org.apache.curator.CuratorZookeeperClient;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.RetryForever;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.TimeUnit;

public class DistributedLockUtil {
    // 集群地址
    public static final String ZOOKEEPER_STRING = "127.0.0.1:2181,127.0.0.1:2182,127.0.0.1:2183";
    // 可重入锁实现类
    private InterProcessMutex lock;
    // 锁的路径
    private String lockPath = "/locks";

    public DistributedLockUtil(CuratorFramework client) {
        this.lock = new InterProcessMutex(client, lockPath);
    }

    public InterProcessMutex getLock() {
        return lock;
    }

    public static void main(String[] args) {
        CuratorFramework curatorFramework = CuratorFrameworkFactory.newClient(ZOOKEEPER_STRING,
                10000,// session断开保留时长
                10000,// 链接超时
                new RetryForever(10000)// 重试机制
        );
        curatorFramework.start();
        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 10; i++) {
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                DistributedLockUtil distributedLockUtil = new DistributedLockUtil(curatorFramework);
                InterProcessMutex lock = distributedLockUtil.getLock();
                try {
                    lock.acquire();
                    System.out.println(Thread.currentThread().getName() + "-获得锁!");
                    TimeUnit.SECONDS.sleep(10);
                } catch (Exception e) {
                    e.printStackTrace();
                } finally {
                    try {
                        lock.release();
                    } catch (Exception e) {
                        e.printStackTrace();
                    }
                }


            }, "Thread:" + i).start();
            countDownLatch.countDown();

        }

    }
}
