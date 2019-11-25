package com.ognice;

import com.ognice.kafka.sender.KafkaProducerService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.Random;

/**
 * Hello world!
 */
@RestController
@EnableAutoConfiguration
@ComponentScan("com.ognice")
public class KafkaDemoApp {
    @Autowired
    KafkaProducerService kafkaProducerService;

    @RequestMapping("/")
    String home() {
        kafkaProducerService.sendMessage("我点击了" + new Random().nextInt());
        return "Hello World!";
    }

    public static void main(String[] args) {
        SpringApplication.run(KafkaDemoApp.class, args);
    }
}
