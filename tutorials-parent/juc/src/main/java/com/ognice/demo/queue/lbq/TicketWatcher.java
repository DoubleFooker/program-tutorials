package com.ognice.demo.queue.lbq;

import java.util.concurrent.LinkedBlockingQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class TicketWatcher implements Runnable {

    private LinkedBlockingQueue queue;
    private TicketProducer ticketProducer;

    public TicketWatcher(LinkedBlockingQueue queue, TicketProducer ticketProducer) {
        this.queue = queue;
        this.ticketProducer = ticketProducer;
    }

    @Override
    public void run() {

        // As long as the producer is running,
        // we want to check for tickets.
        while (ticketProducer.isRunning()) {
            System.out.println("Tickets right now: " + queue);

            try {
                Thread.sleep(2000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }

        System.out.println("TicketWatcher Completed.");
        System.out.println("Final Tickets in the queue: " + queue);
    }
}