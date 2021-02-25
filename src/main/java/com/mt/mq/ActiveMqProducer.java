package com.mt.mq;

import javax.jms.Connection;
import javax.jms.ConnectionFactory;
import javax.jms.Destination;
import javax.jms.MessageProducer;
import javax.jms.Queue;
import javax.jms.Session;
import javax.jms.TextMessage;
import org.apache.activemq.ActiveMQConnectionFactory;

public class ActiveMqProducer {
    public static void main(String[] args) throws Exception {
        ConnectionFactory factory = new ActiveMQConnectionFactory("admin", "admin", "tcp://localhost:61616/");
        Connection connection = factory.createConnection();
        connection.start();
        Session session = connection.createSession(false,Session.AUTO_ACKNOWLEDGE);
        Destination destination = session.createQueue("test_queue");
        MessageProducer producer = session.createProducer(destination);
        TextMessage message = session.createTextMessage("hello, some message!!!");
        for (int i = 0; i < 10; i++) {
            Thread.sleep(2000);
            producer.send(message);
        }
        System.out.println("message send success!!");
        producer.close();
        session.close();
        connection.close();


    }
}
