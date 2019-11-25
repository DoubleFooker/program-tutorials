package com.ognice.zklock.services.curator.example;

import org.apache.curator.framework.recipes.locks.InterProcessMutex;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.concurrent.TimeUnit;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/9/4
 */
public class LockService {
    Logger logger = LoggerFactory.getLogger(this.getClass());
    private InterProcessMutex interProcessMutex;
    private LockProcess lockProcess;
    private long time = 3000;
    private TimeUnit timeUnit = TimeUnit.MILLISECONDS;

    public LockService(InterProcessMutex interProcessMutex, LockProcess lockProcess) {
        this.interProcessMutex = interProcessMutex;
        this.lockProcess = lockProcess;
    }

    public void workProcess() throws Exception {
        try {
            if (!interProcessMutex.acquire(time, timeUnit)) {
                throw new IllegalAccessException("get lock fail!");
            }
            lockProcess.doWork();
        } finally {
            //interProcessMutex.release();
        }

    }
}
