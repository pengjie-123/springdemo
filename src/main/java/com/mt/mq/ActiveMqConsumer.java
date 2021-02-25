package com.mt.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.JMSException;
import javax.jms.Message;
import javax.jms.MessageConsumer;
import javax.jms.MessageListener;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqConsumer {
    public static void main(String[] args) throws JMSException {
        ConnectionFactory factory    = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616/");
        Connection         connection = factory.createConnection();
        connection.start();
        Session         session     = connection.createSession(false, Session.AUTO_ACKNOWLEDGE);
        Destination     destination = session.createQueue("test_queue");
        MessageConsumer consumer    = session.createConsumer(destination);
//        TextMessage         message     = (TextMessage) consumer.receive(5000);
        consumer.setMessageListener(new MessageListener() {
            @Override
            public void onMessage(Message message) {
                TextMessage messages = (TextMessage) message;
                try {
                    System.out.println(messages.getText());
                } catch (JMSException e) {
                    e.printStackTrace();
                }

            }
        });
//        consumer.close();
//        session.close();
//        connection.close();

    }
}
