package com.example.notificationservice.services;

import com.example.notificationservice.dtos.NotificationDto;

import java.util.List;
import java.util.UUID;

public interface NotificationService {
    String sendNotification(NotificationDto notification);

    String deleteNotification(String id);

    String markNotificationAsRead(String id);

    List<NotificationDto> getNotificationsByUser(String id);
}
