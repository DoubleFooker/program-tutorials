package com.ognice.zklock.services.curator.example;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.apache.curator.utils.CloseableUtils;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/4
 */
public class ZkServer {
    private static String zkUrl = "127.0.0.1:2184";
    private RetryPolicy retryPolicy = new ExponentialBackoffRetry(3000, 3);
    private CuratorFramework client;

    public CuratorFramework getClient() {
        if (client == null || client.getState().equals(CuratorFrameworkState.STOPPED)) {
            createClient();
        }
        return client;
    }

    private void createClient() {
        client = CuratorFrameworkFactory.newClient(zkUrl, retryPolicy);
        client.start();
    }

    public void closeResource() {
        if (client != null) {
            CloseableUtils.closeQuietly(client);
        }
    }

}
