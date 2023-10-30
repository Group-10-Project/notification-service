package com.example.notificationservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class NotificationDto {
    private String id;
    private String createdAt;
    private String userId;
    private String serviceId;
    private String message;
    private String notificationStatus;
    private String recievedAt;
}
