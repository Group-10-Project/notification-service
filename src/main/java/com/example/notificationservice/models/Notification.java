package com.example.notificationservice.models;

import java.security.Timestamp;
import java.util.UUID;

public class Notification extends BaseModel{
    private Timestamp timestamp;
    private UUID userId;
    private String service;
    private String message;
    private Boolean isRead;

}
