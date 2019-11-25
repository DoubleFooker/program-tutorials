package com.ognice;

import com.ognice.strategy.spider2.ProductSpider;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.context.ConfigurableApplicationContext;

import java.util.concurrent.CountDownLatch;

/**
 * Hello world!
 */
@SpringBootApplication
public class App {
    public static void main(String[] args)


    {

        ConfigurableApplicationContext run = SpringApplication.run(App.class);


        CountDownLatch countDownLatch = new CountDownLatch(1);
        for (int i = 0; i < 2000; i++) {

            String url = "tburl" + i;
            new Thread(() -> {
                try {
                    countDownLatch.await();
                } catch (InterruptedException e) {
                    e.printStackTrace();
                }
                System.out.println(Thread.currentThread().getName() + ":" + ProductSpider.spiderMap.get("TB").doSpider(url));
                ;

            }).start();
        }
        countDownLatch.countDown();
    }
}
