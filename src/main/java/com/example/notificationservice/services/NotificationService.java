package com.example.notificationservice.services;

import com.example.notificationservice.dtos.NotificationStorageDto;
import com.example.notificationservice.dtos.RequestDto;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationStatus;

import java.util.List;

public interface NotificationService {
    String sendNotification(RequestDto notification);
    Notification createNotification(RequestDto notification);

    String deleteNotification(String id);

    String updateNotificationStatus(String id, NotificationStatus notificationStatus);

    List<NotificationStorageDto> getNotificationsByUser(String id);
}
