package com.ognice.notransaction.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 发布订阅模式
 * 设置 事务型会话，消息确认方式为auto_acknowledge，需要session进行commit操作才算完成操作
 */
public class JMSTopicProducer {
    public static void main(String[] args) {
        //
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;
        try {
            connection = ((ActiveMQConnectionFactory) connectionFactory).createTopicConnection();
            connection.start();

            session = connection.createSession(Boolean.TRUE, Session.AUTO_ACKNOWLEDGE);

            Destination destination = session.createTopic("testTopic");

            MessageProducer producer = session.createProducer(destination);
            int max = 20;
            for (int i = 0; i < max; i++) {

                TextMessage textMessage = session.createTextMessage("testTopic" + i);

                producer.send(textMessage);
            }
            session.commit();

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
