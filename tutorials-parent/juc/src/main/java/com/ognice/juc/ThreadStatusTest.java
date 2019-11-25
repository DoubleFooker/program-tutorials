package com.ognice.juc;

import java.util.Collections;
import java.util.HashSet;
import java.util.Set;
import java.util.concurrent.TimeUnit;
import java.util.stream.Collectors;
import java.util.stream.Stream;

/**
 * 线程状态的转化
 * new->REDAY->RUNING->TERMINATE
 * ->WAITING
 * ->TIMEWAITING
 * ->BLOCKED
 * Sleep会转化为timewaiting
 * 阻塞（加锁的时候）导致blocked
 * 调用class.wait()方法，调用前要先获得的锁，不然会抛异常（因为可能当前的线程不是持有锁的线程）转化为waiting状态
 */
public class ThreadStatusTest {
private Object lock;

    public ThreadStatusTest(Object lock) {
        this.lock = lock;
    }

    public   void  test(){
        synchronized (lock) {
            System.out.println("");
            Set a=new HashSet();
            a.toArray();
        }
    }
    public static void main(String[] args) {
        Object aa=new Object();
        ThreadStatusTest a=new ThreadStatusTest(aa);
        a.test();
        ThreadStatusTest b=new ThreadStatusTest(aa);
        b.test();
        new Thread(()->a.test()).start();
        new Thread(()->b.test()).start();
//        Thread t = new Thread(() -> {
//            synchronized (ThreadStatusTest.class) {
//                System.out.println("wait");
//                try {
//                    // wait会释放锁 sleep不会
//                    ThreadStatusTest.class.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//
//        });
//        t.setName("waiting");
//        t.start();
//
//        Thread t2 = new Thread(() -> {
//            System.out.println("time wait");
//
//            while (true) {
//                try {
//                    TimeUnit.SECONDS.sleep(2);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        t2.setName("time waiting");
//        t2.start();
//        Thread blocked1=new Thread(()->{
//            synchronized (ThreadStatusTest.class){
//                System.out.println("hold blocled");
//                try {
//                    // sleep不释放锁
//                    TimeUnit.SECONDS.sleep(200);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        });
//        blocked1.setName("hold blocked");
//        blocked1.start();
//
//        Thread blocked=new Thread(()->{
//            synchronized (ThreadStatusTest.class){
//                System.out.println("blocled");
//            }
//        });
//        blocked.setName("blocked");
//        blocked.start();

    }
}
