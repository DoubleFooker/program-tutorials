package com.ognice.demo.queue.clq;

import java.util.concurrent.ConcurrentLinkedQueue;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/19
 */
public class TicketProducer implements Runnable {

    private ConcurrentLinkedQueue queue;
    private boolean running;

    public TicketProducer(ConcurrentLinkedQueue queue) {
        this.queue = queue;
        running = true;
    }

    // We need to check if the producer thread is
    // Still running, and this method will return
    // the state (running/stopped).
    public boolean isRunning() {
        return running;
    }

    @Override
    public void run() {

        // We are adding tickets using put() which waits
        // until it can actually insert elements if there is
        // not space in the queue.
        for (int i = 0; i < 1000000; i++) {
            String element = "Ticket" + i;


            queue.add(element);
            System.out.println("Ticket added: " + element);
//            try {
//                Thread.sleep(1000);
//
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
        }
        System.out.println("TicketProducer Completed.");
        running = false;
    }

}