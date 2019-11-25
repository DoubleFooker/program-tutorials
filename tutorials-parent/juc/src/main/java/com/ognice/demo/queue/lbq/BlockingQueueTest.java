package com.ognice.demo.queue.lbq;

import java.util.List;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class BlockingQueueTest {

    public static void main(String[] args) throws InterruptedException {
        LinkedBlockingQueue<List<String>> queue = new LinkedBlockingQueue<>(10);

        TicketProducer producer = new TicketProducer(queue);
        TicketWatcher watcher = new TicketWatcher(queue, producer);
        TicketConsumer consumer = new TicketConsumer(queue, producer);

        Thread producerThread = new Thread(producer);
        Thread watcherThread = new Thread(watcher);
        Thread consumerThread = new Thread(consumer);

        //producerThread.start();
        Thread.sleep(2000);
        consumerThread.start();
        Thread.sleep(2000);
        //watcherThread.start();
    }
}