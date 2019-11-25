package com.ognice.demo.processordemo;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class PrintProcessor extends Thread implements RequestProcessor {
    private final RequestProcessor nextRequestProcessor;

    private LinkedBlockingQueue<Request> requestLinkedBlockingQueue = new LinkedBlockingQueue<>();

    PrintProcessor(RequestProcessor requestProcessor) {
        this.nextRequestProcessor = requestProcessor;

    }

    @Override
    public void doRequestProcessor(Request request) {
        requestLinkedBlockingQueue.add(request);
    }

    @Override
    public void run() {
        while (true) {
            try {
                Request take = requestLinkedBlockingQueue.take();
                System.out.println("打印新城执行--" + take.getName());
                nextRequestProcessor.doRequestProcessor(take);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
