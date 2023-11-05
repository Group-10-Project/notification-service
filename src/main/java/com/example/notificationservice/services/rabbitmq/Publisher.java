package com.example.notificationservice.services.rabbitmq;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

public class Publisher {
    //Move this to application.properties
    private final static String QUEUE_NAME = "notification_queue";
    private final static String rabbitmqHost = "localhost";

    public static void publish(String message) {
        ConnectionFactory factory = new ConnectionFactory();
        factory.setHost(rabbitmqHost);
        try (
            Connection connection = factory.newConnection();
            Channel channel = connection.createChannel()
        ) {
            channel.queueDeclare(QUEUE_NAME, false, false, false, null);
            channel.basicPublish("", QUEUE_NAME, null, message.getBytes());
            System.out.println(" [x] Sent '" + message + "'");
        } catch (IOException | TimeoutException e) {
            throw new RuntimeException(e);
        }
    }
}
