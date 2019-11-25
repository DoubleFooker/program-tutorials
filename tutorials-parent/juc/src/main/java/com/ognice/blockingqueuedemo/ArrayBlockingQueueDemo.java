package com.ognice.blockingqueuedemo;

import java.util.concurrent.ArrayBlockingQueue;
import java.util.concurrent.TimeUnit;

/**
 * 实现生产者、消费者模型
 */
public class ArrayBlockingQueueDemo {
    // 队列容量10
    static ArrayBlockingQueue<String> arrayBlockingQueue = new ArrayBlockingQueue<>(10);

    // 生产者
    private static void enq(String entry) {
        arrayBlockingQueue.add(entry);
//        try {
//            TimeUnit.SECONDS.sleep(2);
//        } catch (InterruptedException e) {
//            e.printStackTrace();
//        }
    }

    static {
        init();
    }

    // 启动线程监听队列数据，消费者
    private static void init() {
        new Thread(() -> {
            System.out.println("消费者启动！");
            while (true) {
//                try {
//                    // 如果队列为空，这个操作会阻塞
//                    String data = arrayBlockingQueue.take();
//                    System.out.println("take 消费数据：" + data);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                // 如果队列为空 返回null 不阻塞
//                String polldata = arrayBlockingQueue.poll();
//                try {
//                    TimeUnit.SECONDS.sleep(1);
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//                System.out.println("poll 消费数据：" + polldata);
                // 队列为空 会报错
                if (arrayBlockingQueue.size() > 0) {
                    String removeData = arrayBlockingQueue.remove();
                    try {
                        TimeUnit.SECONDS.sleep(5);
                    } catch (InterruptedException e) {
                        e.printStackTrace();
                    }
                    System.out.println("remove 消费数据：" + removeData);
                }
            }
        }).start();
    }

    public static void main(String[] args) {
        for (int i = 0; i < 1000; i++) {
//            // 如果队列满了,会一直阻塞
            try {
                arrayBlockingQueue.put("data" + i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            // 可设置超时时间
            try {
                boolean add3 = arrayBlockingQueue.offer("data" + i, 1, TimeUnit.SECONDS);
                System.out.println("senddata"+i);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
//            // 如果队列满了，继续添加数据会报异常
//            boolean add = arrayBlockingQueue.add("data" + i);
        }
    }
}
