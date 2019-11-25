package com.ognice.notransaction.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * 持久化，消费者启动后也能消费掉线是产生的消息
 * 非事务
 * 测试消息确认方式
 * DUPS_OK_ACKNOWLEDGE，延迟确认
 */
public class JMSPersistenceDupAckTopicConsumer {
    public static void main(String[] args) {
        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;
        try {
            connection = connectionFactory.createConnection();
            //设置消费者id，做持久化标识
            connection.setClientID("dbfk");

            connection.start();
            session = connection.createSession(Boolean.FALSE, Session.DUPS_OK_ACKNOWLEDGE);
            Topic topic = session.createTopic("testTopic");

            //通过注册的clientid，创建持久化消费端
            MessageConsumer consumer = session.createDurableSubscriber(topic, "dbfk");
            //阻塞消费,设置事务型消费，未发送确认消息 session.commit()，消息依然存在队列中
            int max = 10;
            for (int i = 0; i < max; i++) {
                Message message = consumer.receive();
                if (message instanceof TextMessage) {
                    System.out.println(((TextMessage) message).getText());

                }
                    //DUPS_OK_ACKNOWLEDGE，需要客户端发送确认消息，将之前消费的消息通知MQ，之后消费的也还没确认
                    message.acknowledge();
            }


            //发送确认应答，消息队列收到应答才删除消息
            //session.commit();

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
