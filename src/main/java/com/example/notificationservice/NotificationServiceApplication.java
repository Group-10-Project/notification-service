package com.example.notificationservice;

import com.rabbitmq.client.Channel;
import com.rabbitmq.client.Connection;
import com.rabbitmq.client.ConnectionFactory;
import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;

import java.io.IOException;
import java.util.concurrent.TimeoutException;

@SpringBootApplication
public class NotificationServiceApplication {
    private final static String QUEUE_NAME = "notification_queue";
    public static void main(String[] args) {

        SpringApplication.run(NotificationServiceApplication.class, args);
        System.out.println("Notification Service is running...");

    }
}
