package com.ognice.zklock.services.curator;

import org.apache.curator.RetryPolicy;
import org.apache.curator.framework.CuratorFramework;
import org.apache.curator.framework.CuratorFrameworkFactory;
import org.apache.curator.framework.imps.CuratorFrameworkState;
import org.apache.curator.framework.recipes.leader.LeaderSelector;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListener;
import org.apache.curator.framework.recipes.leader.LeaderSelectorListenerAdapter;
import org.apache.curator.retry.ExponentialBackoffRetry;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 描述:zk选举策略代码实现
 *
 * @author dbfk
 * @sine 2018/9/4
 */
public class CuratorLeaderElection {
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

    public void getLeader() {
        createConnect();
        LeaderSelectorListener listener = new LeaderSelectorListenerAdapter() {
            public void takeLeadership(CuratorFramework client) throws Exception {
                // this callback will get called when you are the leader
                // do whatever leader work you need to and only exit
                // this method when you want to relinquish leadership
                //System.out.println("leaderTest");
            }
        };

        LeaderSelector selector = new LeaderSelector(client, "/dbfk-leader", listener);
        selector.autoRequeue();  // not required, but this is behavior that you will probably expect
        selector.start();
    }

    public static void main(String[] args) {
        CuratorLeaderElection curatorLeaderElection = new CuratorLeaderElection();
        curatorLeaderElection.getLeader();
    }

}
