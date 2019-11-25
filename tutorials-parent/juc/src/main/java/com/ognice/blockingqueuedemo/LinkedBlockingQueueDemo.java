package com.ognice.blockingqueuedemo;

import java.util.concurrent.LinkedBlockingQueue;

public class LinkedBlockingQueueDemo {
    static LinkedBlockingQueue<String> stringLinkedBlockingQueue=new LinkedBlockingQueue<>(10);

    public static void main(String[] args) {
        for (int i=0;i<1000;i++){
            try {
                System.out.println("sendData"+i);
                stringLinkedBlockingQueue.put("data"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
        }
    }
}
