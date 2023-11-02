package com.example.notificationservice.repository;

import com.example.notificationservice.models.Notification;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;
import java.util.UUID;

public interface NotificationRepository extends CrudRepository<Notification, UUID> {
    Notification save(Notification notification);
    List<Notification> findByUserId(UUID uuid);
}
