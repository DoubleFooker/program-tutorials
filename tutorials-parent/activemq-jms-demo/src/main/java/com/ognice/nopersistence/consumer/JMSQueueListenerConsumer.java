package com.ognice.nopersistence.consumer;

import org.apache.activemq.ActiveMQConnectionFactory;

import javax.jms.*;
import java.io.IOException;

/**
 * listener方式非阻塞接收消息
 */
public class JMSQueueListenerConsumer {
    public static void main(String[] args) {


        ConnectionFactory connectionFactory = new ActiveMQConnectionFactory("tcp://127.0.0.1:61616");
        Connection connection = null;
        Session session = null;

        {
            try {
                connection = connectionFactory.createConnection();
                connection.start();
                session = connection.createSession(Boolean.FALSE, Session.AUTO_ACKNOWLEDGE);
                Destination destination = session.createQueue("testQueue");
                MessageListener messageListener = new MessageListener() {
                    @Override
                    public void onMessage(Message message) {
                        if (message instanceof TextMessage) {
                            try {
                                System.out.println(((TextMessage) message).getText());
                            } catch (JMSException e) {
                                e.printStackTrace();
                            }
                        }
                    }
                };

                MessageConsumer consumer = session.createConsumer(destination);

                //非阻塞接收消息
                consumer.setMessageListener(messageListener);
                //等待测试效果
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


}
