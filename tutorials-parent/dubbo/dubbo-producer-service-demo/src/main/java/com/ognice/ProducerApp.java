package com.ognice;

import com.alibaba.dubbo.config.spring.context.annotation.DubboComponentScan;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
@DubboComponentScan(basePackages = {"com.ognice"})
public class ProducerApp {
    public static void main(String[] args) {
        SpringApplication.run(ProducerApp.class);
    }
}
