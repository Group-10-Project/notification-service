package com.example.notificationservice.dtos;


import lombok.Getter;
import lombok.Setter;

@Getter
@Setter

public class NotificationStorageDto {
    private String id;
    private String message;
    private String userId;
    private String microserviceId;
    private String createdAt;
    private String deliveredAt;
    private String notificationStatus;
}

//make  a request dto and response dto and storage dto

