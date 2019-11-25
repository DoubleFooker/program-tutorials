package com.ognice.notransaction.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * 持久化，消费者启动后也能消费掉线是产生的消息
 * 事务
 * 测试消息确认方式
 * AUTO_ACKNOWLEDGE
 */
public class JMSPersistenceClientAckLinstenerTopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            //设置消费者id，做持久化标识
            connection.setClientID("dbfk2");

            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.CLIENT_ACKNOWLEDGE);
            Topic topic = session.createTopic("testTopic");

            //通过注册的clientid，创建持久化消费端
            MessageConsumer consumer = session.createDurableSubscriber(topic, "dbfk2");
            consumer.setMessageListener(new MessageListener() {
                @Override
                public void onMessage(Message message) {
                    if (message instanceof TextMessage) {
                        try {
                            System.out.println(((TextMessage) message).getText());
                            //message.acknowledge();
                        } catch (JMSException e) {
                            e.printStackTrace();
                        }

                    }
                }
            });
            System.in.read();

        } catch (JMSException e) {
            e.printStackTrace();
        } catch (IOException e) {
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
