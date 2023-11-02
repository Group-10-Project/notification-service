package com.example.notificationservice.models;

public enum NotificationStatus {
    //This state will be created when the notification is created
    PENDING,
    //This state will be created when the notification is sent to the rabbitMQ queue/when rabbitMQ actually sends it
    SENT,
    //This state will be created when the rabbitMQ sends the notification to the user and returns an ack
    DELIVERED,
    //This state will be created once markasread notification is called by the other microservices/User?
    READ,
    //This state will be created when the notification is failed to be sent to be sent to the user
    FAILED,
    //This state will be created when the delete notification is called by the other microservices and the notification is still in PENDING/FAILED state
    DELETED
}
