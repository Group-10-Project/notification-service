package com.example.notificationservice.models;

import jakarta.persistence.Entity;
import lombok.Getter;
import lombok.Setter;

import java.sql.Timestamp;
import java.util.UUID;

@Getter
@Setter
@Entity
public class Notification extends BaseModel{
    private Timestamp createdAt;
    private Timestamp recievedAt;
    private UUID userId;
    private UUID serviceId;
    private String message;
    private NotificationStatus notificationStatus;
    //notificationSource: which notification it is coming from
    //notificationType: email, push, message
    //mapping with every service
}
