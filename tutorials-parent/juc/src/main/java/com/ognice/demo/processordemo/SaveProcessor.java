package com.ognice.demo.processordemo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class SaveProcessor extends Thread implements RequestProcessor {
    private LinkedBlockingQueue<Request> requestLinkedBlockingQueue = new LinkedBlockingQueue<>();

    @Override
    public void doRequestProcessor(Request request) {
        requestLinkedBlockingQueue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request take = requestLinkedBlockingQueue.take();
                System.out.println("保存线程执行--" + take.getName());
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
