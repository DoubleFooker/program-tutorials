package com.ognice;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

/**
 * Hello world!
 */
@SpringBootApplication
public class CqrsApp {
    public static void main(String[] args) {
        SpringApplication.run(CqrsApp.class, args);
//        Configuration config = DefaultConfigurer.defaultConfiguration()
//                .configureEmbeddedEventStore(c -> new InMemoryEventStorageEngine())
//                .buildConfiguration();
//        config.start();
        //config.commandGateway().send(new UserAccountCommand("dbfk", "MyAccount", "mail"));
    }
}
