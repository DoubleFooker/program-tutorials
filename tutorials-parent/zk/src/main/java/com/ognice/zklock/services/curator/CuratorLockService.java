package com.ognice.zklock.services.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Random;
import java.util.concurrent.TimeUnit;


/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/4
 */
public class CuratorLockService {
    Logger logger = LoggerFactory.getLogger(this.getClass());

    private String zkUrl = "127.0.0.1:2184";
    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(10000, 3);
    private CuratorFramework client;
    private long maxWait = 3000;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;


    private void createConnect() {
        if (client == null || client.getState().equals(CuratorFrameworkState.STOPPED)) {
            client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
            client.start();
        }
    }

    public void lock() throws Exception {
        createConnect();
        InterProcessMutex lock = new InterProcessMutex(client, "/dbfk-lock");
        //加锁成功即执行
        if (lock.acquire(maxWait, timeUnit)) {
            try {
                // do some work inside of the critical section here
                Thread.sleep(30000);
            } finally {
                lock.release();
                CloseableUtils.closeQuietly(client);
            }
        }
    }

    private void createPath() {
        try {
            client.create().forPath("/dbfk" + new Random().nextInt());
        } catch (Exception e) {
            logger.error("create path error", e);
        } finally {
            client.close();
        }
    }

    public static void main(String[] args) {
        CuratorLockService c = new CuratorLockService();
        c.createConnect();
        c.createPath();
    }
}
