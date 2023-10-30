package com.example.notificationservice.controllers;

import com.example.notificationservice.dtos.NotificationDto;
import com.example.notificationservice.services.NotificationService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.UUID;

@RestController
@RequestMapping("/notifications")
public class NotificationController {
    private NotificationService notificationService;

    //what is the below constructor doing?
    public NotificationController(NotificationService notificationService) {
        this.notificationService = notificationService;
    }

    @PostMapping
    public ResponseEntity<String> sendNotification(@RequestBody NotificationDto notification) {
        return new ResponseEntity<>(
                notificationService.sendNotification(notification),
                HttpStatus.OK
        );
    }

    @DeleteMapping("{id}")
    public String deleteNotification(@PathVariable String id) {
        return notificationService.deleteNotification(id);
    }

    @PatchMapping("{id}")
    public String markNotificationAsRead(@PathVariable String id) {
        //Doubt: If i am keeping this as patch/notificationId, won't I be having any update notification method?
        //PATCH
        //path: /notificationId
        //Response: {success: true} / Error
        return notificationService.markNotificationAsRead(id);
    }

    @GetMapping("{id}")
    public List<NotificationDto> getNotificationsByUser(@PathVariable String id) {
        return notificationService.getNotificationsByUser(id);
    }
}
