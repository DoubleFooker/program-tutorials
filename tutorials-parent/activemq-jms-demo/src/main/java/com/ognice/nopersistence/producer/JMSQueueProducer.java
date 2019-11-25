package com.ognice.nopersistence.producer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;

/**
 * JMS基本元素
 * 1。ConnectionFactory
 * 2。connection
 * 3。destination
 * 4。session
 * 5。producer
 * 6。consumer
 * 7。message
 */
public class JMSQueueProducer {
    /**
     * connection 创建ActiveMQQueueSession,设置参数
     * 是否持久化，
     * 消息确认方式
     * AUTO_ACKNOWLEDGE
     * CLIENT_ACKNOWLEDGE
     * DUPS_OK_ACKNOWLEDGE
     * SESSION_TRANSACTED
     **/
    //非持久化，非事务，自动应答
    public static void main(String[] args) {
        //connectionfactory
        QueueConnectionFactory queueConnectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        //factory创建ActiveMQConnection
        QueueConnection connection = null;
        Session session = null;
        try {
            connection = queueConnectionFactory.createQueueConnection();
            connection.start();
            session = connection.createQueueSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
            //创建目标
            Destination destination = session.createQueue("testQueue");
            //创建producer
            MessageProducer producer = session.createProducer(destination);
            //设置持久化
            producer.setDeliveryMode(DeliveryMode.NON_PERSISTENT);
            //创建message
            TextMessage textMessage = session.createTextMessage("testMessage");
            //发送消息
            producer.send(textMessage);
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
