package com.ognice.producer;

import org.apache.kafka.clients.producer.KafkaProducer;
import org.apache.kafka.clients.producer.ProducerConfig;
import org.apache.kafka.clients.producer.ProducerRecord;

import java.util.Properties;

/**
 * @author
 */
public class KafkaProducerDemo {

    private String aa;

    public String getAa() {
        return aa;
    }

    public KafkaProducerDemo setAa(String aa) {
        this.aa = aa;
        return this;
    }

    public static void main(String[] args) {

        KafkaProducer<Integer, String> kafkaProducer = null;
        String topic = "dbfk-kafka-topic";
        Properties properties = new Properties();
        properties.setProperty(ProducerConfig.BOOTSTRAP_SERVERS_CONFIG, "127.0.0.1:9092,127.0.0.1:9093,127.0.0.1:9094");
        properties.setProperty(ProducerConfig.CLIENT_ID_CONFIG, "dbfk-kafka-producer");
        properties.setProperty(ProducerConfig.ACKS_CONFIG, "-1");
        //key value序列化设置
        properties.setProperty(ProducerConfig.KEY_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.IntegerSerializer");
        properties.setProperty(ProducerConfig.VALUE_SERIALIZER_CLASS_CONFIG, "org.apache.kafka.common.serialization.StringSerializer");
        kafkaProducer = new KafkaProducer<Integer, String>(properties);
        for (int i = 0; i < 50; i++) {
            String message = "msg_" + i;
            kafkaProducer.send(new ProducerRecord<Integer, String>(topic, message));
            System.out.println("发送消息" + message + "->kafka");

            try {
                Thread.sleep(1000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }


        }

    }
}
