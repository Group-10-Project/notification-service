package com.example.notificationservice.controllers;

import com.example.notificationservice.dtos.NotificationStorageDto;
import com.example.notificationservice.dtos.RequestDto;
import com.example.notificationservice.models.NotificationStatus;
import com.example.notificationservice.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private final NotificationService notificationService;

    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody RequestDto notification) {
        //Creation: send an acknowledgement 200 OK
        //send to queue
        return new ResponseEntity<>(
            notificationService.sendNotification(notification),
            HttpStatus.OK
        );
    }

    //whatever undelivered deleted those notifications
    //change the status to deleted
    //we'll receive this from microservice
    @DeleteMapping("{id}")
    public String deleteNotification(@PathVariable String id) {
        return notificationService.updateNotificationStatus(id, NotificationStatus.DELETED);
    }


    //this call we'll get from the microservice
    @PatchMapping("{id}")
    public String markNotificationAsRead(@PathVariable String id) {
        //Response: {success: true} / Error
        return notificationService.updateNotificationStatus(id, NotificationStatus.READ);
    }

    //one more parameter for status optional default unread
    @GetMapping("{id}")
    public List<NotificationStorageDto> getNotificationsByUser(@PathVariable String id) {
        return notificationService.getNotificationsByUser(id);
    }
}
