package com.ognice.notransaction.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 持久化，消费者启动后也能消费掉线是产生的消息
 * 事务
 * 测试消息确认方式
 * AUTO_ACKNOWLEDGE
 */
public class JMSPersistenceAutoAckTopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            //设置消费者id，做持久化标识
            connection.setClientID("dbfk");

            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            Topic topic = session.createTopic("testTopic");

            //通过注册的clientid，创建持久化消费端
            MessageConsumer consumer = session.createDurableSubscriber(topic, "dbfk");
            int max = 10;
            for (int i = 0; i < max; i++) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    System.out.println(((TextMessage) message).getText());
                }
            }

        } catch (JMSException e) {
            e.printStackTrace();
        } finally {
            if (session != null) {
                try {
                    session.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
            if (connection != null) {
                try {
                    connection.close();
                } catch (JMSException e) {
                    e.printStackTrace();
                }
            }
        }
    }
}
