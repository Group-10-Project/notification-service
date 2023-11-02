package com.example.notificationservice.services;

import com.example.notificationservice.dtos.NotificationStorageDto;
import com.example.notificationservice.dtos.RequestDto;
import com.example.notificationservice.models.Notification;
import com.example.notificationservice.models.NotificationStatus;
import com.example.notificationservice.repository.NotificationRepository;
import org.springframework.stereotype.Service;

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
    public Notification createNotification(RequestDto notification) {
        Notification notificationEntity = new Notification();

        notificationEntity.setMessage(notification.getMessage());
        notificationEntity.setUserId(UUID.fromString(notification.getUserId()));
        notificationEntity.setMicroserviceId(UUID.fromString(notification.getMicroserviceId()));
        notificationEntity.setCreatedAt(Timestamp.valueOf(notification.getCreatedAt()));
        notificationEntity.setNotificationStatus(NotificationStatus.PENDING);

        return notificationRepository.save(notificationEntity);
    }

    @Override
    public String sendNotification(RequestDto notification) {
        Notification notificationEntity = createNotification(notification);

        //send to rabbitMQ queue and update the status to sent in the db
        updateNotificationStatus(notificationEntity.getId().toString(), NotificationStatus.SENT);

        return "Notification sent successfully";
    }

    //This method needs to be removed
    @Override
    public String deleteNotification(String id) {
        //Either directly delete by id or first get the optional notification class and then delete it
        notificationRepository.deleteById(UUID.fromString(id));
        return "Deleted Successfully";
    }

    public String updateNotificationStatus(String id, NotificationStatus notificationStatus) {
        Optional<Notification> notification = notificationRepository.findById(UUID.fromString(id));
        if(notification.isPresent()) {
            notification.get().setNotificationStatus(notificationStatus);
            notificationRepository.save(notification.get());
        } else {
            return "Not found";
        }
        return "Updated notification status to: " + notificationStatus.toString();
    }

    //this can be static
    private String formatTimestamp(Timestamp timestamp) {
        return timestamp.toString();
    }

    //static
    public NotificationStorageDto mapNotificationToDto(Notification notification) {
        NotificationStorageDto dto = new NotificationStorageDto();
        dto.setId(notification.getId().toString());
        dto.setCreatedAt(formatTimestamp(notification.getCreatedAt()));
        dto.setUserId(notification.getUserId().toString());
        dto.setMicroserviceId(notification.getMicroserviceId().toString());;
        dto.setMessage(notification.getMessage());
        dto.setNotificationStatus(notification.getNotificationStatus().toString());

        return dto;
    }

    public List<NotificationStorageDto> mapNotificationListToDtoList(List<Notification> notificationList) {
        List<NotificationStorageDto> dtoList = new ArrayList<>();
        for (Notification notification : notificationList) {
            NotificationStorageDto dto = mapNotificationToDto(notification);
            dtoList.add(dto);
        }
        return dtoList;
    }

    public List<NotificationStorageDto> getNotificationsByUser(String id) {
        List<Notification> notifications = notificationRepository.findByUserId(UUID.fromString(id));
        return mapNotificationListToDtoList(notifications);
    }
}
