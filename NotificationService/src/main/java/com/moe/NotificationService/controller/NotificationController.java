package com.moe.NotificationService.controller;


import com.moe.NotificationService.entity.Notification;
import com.moe.NotificationService.service.NotificationService;
import com.moe.clients.notification.NotificationRequest;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("api/v1/notifications")
@Slf4j
public class NotificationController {

    @Autowired
    NotificationService notificationService;

    @PostMapping
    public void createNotification(@RequestBody NotificationRequest notification){

        notificationService.createNotification(notification);
    }
}
