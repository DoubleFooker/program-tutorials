package com.ognice.demo;

import java.util.concurrent.*;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/16
 */
public class TestCallable implements Callable<String> {
    @Override
    public String call() throws Exception {
        System.out.println("测试Callable线程");
        return "done";


    }

    public static void main(String[] args) {
        ExecutorService executorService = Executors.newFixedThreadPool(1);
        Future<String> stringFuture = executorService.submit(new TestCallable());
        try {
            System.out.println(stringFuture.get());
        } catch (InterruptedException e) {
            e.printStackTrace();
        } catch (ExecutionException e) {
            e.printStackTrace();
        }
        executorService.shutdown();
    }
}
