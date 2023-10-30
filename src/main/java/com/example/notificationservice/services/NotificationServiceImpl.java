package com.example.notificationservice.services;

import com.example.notificationservice.dtos.NotificationDto;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationStatus;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.context.annotation.Primary;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;
import java.util.Optional;
import java.util.UUID;

@Service
public class NotificationServiceImpl implements NotificationService{
    final NotificationRepository notificationRepository;

    public NotificationServiceImpl(NotificationRepository notificationRepository) {
        this.notificationRepository = notificationRepository;
    }

    @Override
    public String sendNotification(NotificationDto notification) {
        Notification notificationEntity = new Notification();
        notificationEntity.setMessage(notification.getMessage());
        notificationEntity.setServiceId(UUID.fromString(notification.getServiceId()));
        notificationEntity.setNotificationStatus(NotificationStatus.UNREAD);
        notificationEntity.setUserId(UUID.fromString(notification.getUserId()));
        notificationEntity.setCreatedAt(Timestamp.valueOf(notification.getCreatedAt()));

        notificationRepository.save(notificationEntity);

        return notificationEntity.getMessage();
    }

    @Override
    public String deleteNotification(String id) {
        //Either directly delete by id or first get the optional notification class and then delete it
        notificationRepository.deleteById(UUID.fromString(id));
        return "Deleted Successfully";
    }

    public String markNotificationAsRead(String id) {
        Optional<Notification> notification = notificationRepository.findById(UUID.fromString(id));
        if(notification.isPresent()) {
            notification.get().setNotificationStatus(NotificationStatus.READ);
            notificationRepository.save(notification.get());
        } else {
            return "Not found";
        }
        return "Marked as read";
    }


    private String formatTimestamp(Timestamp timestamp) {
        return timestamp.toString();
    }

    public NotificationDto mapNotificationToDto(Notification notification) {
        NotificationDto dto = new NotificationDto();
        dto.setId(notification.getId().toString());
        dto.setCreatedAt(formatTimestamp(notification.getCreatedAt()));
        dto.setUserId(notification.getUserId().toString());
        dto.setServiceId(notification.getServiceId().toString());;
        dto.setMessage(notification.getMessage());
        dto.setNotificationStatus(notification.getNotificationStatus().toString());

        return dto;
    }

    public List<NotificationDto> mapNotificationListToDtoList(List<Notification> notificationList) {
        List<NotificationDto> dtoList = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationDto dto = mapNotificationToDto(notification);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<NotificationDto> getNotificationsByUser(String id) {
        List<Notification> notifications = notificationRepository.findByUserId(UUID.fromString(id));
        return mapNotificationListToDtoList(notifications);
    }
}
