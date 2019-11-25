package com.ognice.demo.queue.clq;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */

public class TicketConsumer implements Runnable {
    private ConcurrentLinkedQueue queue;
    private TicketProducer ticketProducer;

    public TicketConsumer(ConcurrentLinkedQueue queue,
                          TicketProducer ticketProducer) {
        this.queue = queue;
        this.ticketProducer = ticketProducer;
    }

    @Override
    public void run() {

        // As long as the producer is running,
        // we remove ticket from the queue.
        while (ticketProducer.isRunning()) {

            try {
                System.out.println("Removing Ticket: " + queue.poll());

                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TicketConsumer completed.");
    }
}