package com.ognice.zklock.services.curator.example;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/4
 */
public class TestExample {
    public static void main(String[] args) {
        ZkServer zkService = new ZkServer();
        LockService lockService = new LockService(new InterProcessMutex(zkService.getClient(), "/dbfk-lock"), () -> {
            System.out.println("I'm being lock!");
        });
        try {
            lockService.workProcess();
        } catch (Exception e) {
            e.printStackTrace();
        } finally {
            zkService.closeResource();
        }

    }
}
