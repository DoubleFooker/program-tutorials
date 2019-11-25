package com.ognice.demo.queue.clq;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class ConcurrentLinkedQueueTest {

    public static void main(String[] args) {
        ConcurrentLinkedQueue queue = new ConcurrentLinkedQueue();

        TicketProducer producer = new TicketProducer(queue);
        TicketWatcher watcher = new TicketWatcher(queue, producer);
        TicketConsumer consumer = new TicketConsumer(queue, producer);
        TicketConsumer consumer2 = new TicketConsumer(queue, producer);
        TicketConsumer consumer3 = new TicketConsumer(queue, producer);

        Thread producerThread = new Thread(producer);
        Thread watcherThread = new Thread(watcher);
        //Thread consumerThread = new Thread(consumer);
        // Thread consumerThread2 = new Thread(consumer2);
        // Thread consumerThread3 = new Thread(consumer3);

        producerThread.start();
        // consumerThread.start();
        // consumerThread2.start();
        //  consumerThread3.start();
        watcherThread.start();
    }
}