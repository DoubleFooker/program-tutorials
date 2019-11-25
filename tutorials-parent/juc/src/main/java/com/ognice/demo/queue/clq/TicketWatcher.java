package com.ognice.demo.queue.clq;

import java.util.concurrent.ConcurrentLinkedQueue;
import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class TicketWatcher implements Runnable {

    private ConcurrentLinkedQueue clqueue;
    private TicketProducer ticketProducer;

    public TicketWatcher(LinkedBlockingQueue queue, TicketProducer ticketProducer) {
        this.ticketProducer = ticketProducer;
    }

    public TicketWatcher(ConcurrentLinkedQueue queue, TicketProducer ticketProducer) {
        this.clqueue = queue;
        this.ticketProducer = ticketProducer;
    }

    @Override
    public void run() {

        // As long as the producer is running,
        // we want to check for tickets.
        while (ticketProducer.isRunning()) {
            System.out.println("clqTickets right now: " + clqueue.size());

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TicketWatcher Completed.");
        System.out.println("Final Tickets in the queue: " + clqueue);
    }
}