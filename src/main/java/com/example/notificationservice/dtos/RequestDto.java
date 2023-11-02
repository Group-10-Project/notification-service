package com.example.notificationservice.dtos;

import lombok.Getter;
import lombok.Setter;

@Getter
@Setter
public class RequestDto {
    private String message;
    private String userId;
    private String microserviceId;
    private String createdAt;
}
