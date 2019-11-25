package com.ognice.demo.threadstatus;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/17
 */
public class TestThreadStatus {
    /**
     * 线程状态
     * NEW 新建线程未执行start
     * RUNNABLE java中把运行和就绪状态归纳未运行中（run和ready）
     * BLOCKED 阻塞
     * 等待阻塞：wait方法执行 运行的线程执行wait方法，jvm会把当前线程放入到等待队列
     * 同步阻塞：同步锁 运行的线程在获取对象的同步锁时，若该同步锁被其他线程锁占 用了，那么 jvm 会把当前的线程放入到锁池中
     * 其他阻塞：Thread.sleep/join 运行的线程执行Thread.sleep或者t.join方法，或者发出了I/O 请求时，JVM 会把当前线程设置为阻塞状态，当 sleep 结束、join 线程终止、 io 处理完毕则线程恢复
     * WAITING
     * TIMED_WAITING 超时等待，超时到了恢复
     * TERMINATED 终止
     *
     * @param args
     */
    public static void main(String[] args) {
        //time_waiting
//        Thread thread = new Thread(() -> {
//            try {
//                Thread.sleep(100000);
//            } catch (InterruptedException e) {
//                e.printStackTrace();
//            }
//        }, "dbfk-new Thread");
//        thread.start();


        //Runable
        Thread runableThread = new Thread(() -> {
            while (true) {
            }
        }, "dbfk-run Thread");
        runableThread.start();

        //blocked -waiting
//        Thread blockedThread = new Thread(() -> {
//            synchronized (TestThreadStatus.class) {
//                try {
//                    TestThreadStatus.class.wait();
//                } catch (InterruptedException e) {
//                    e.printStackTrace();
//                }
//            }
//        }, "block-thread");
//        blockedThread.start();

        while (true) {

        }

    }
}
