package com.ognice.juc;

import java.util.concurrent.TimeUnit;

public class VolatileTest {
    static boolean finish=false;
    static int finish2=1;
    public static void main(String[] args) throws InterruptedException {
        new Thread(()->{
            System.out.println("thread1 running");
            while (!finish){
               // if(finish){
                finish2++;
                //}
            }
            System.out.println("thread1 end");
        }).start();
        TimeUnit.SECONDS.sleep(5);
        new Thread(()->{
            System.out.println("thread2 running");
            finish=true;
            System.out.println("thread2 end");
        }).start();
    }
}
