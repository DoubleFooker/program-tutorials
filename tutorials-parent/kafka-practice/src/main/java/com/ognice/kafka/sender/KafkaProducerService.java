package com.ognice.kafka.sender;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Component;

/**
 * 描述:写点注释吧
 *
 * @author dbfk
 * @sine 2018/8/25
 */
@Component
public class KafkaProducerService {


    private final KafkaTemplate kafkaTemplate;

    @Autowired
    public KafkaProducerService(KafkaTemplate kafkaTemplate) {
        this.kafkaTemplate = kafkaTemplate;
    }

    public void sendMessage(String msg) {
        kafkaTemplate.send("dbfk-test", msg);
    }
}