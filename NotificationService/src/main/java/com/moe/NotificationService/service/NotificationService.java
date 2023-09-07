package com.moe.NotificationService.service;


import com.moe.NotificationService.Repository.NotificationRepository;
import com.moe.NotificationService.entity.Notification;
import com.moe.clients.notification.NotificationRequest;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;

@Service
public class NotificationService {

    @Autowired
    NotificationRepository notificationRepository;

    public void createNotification(NotificationRequest request){

        Notification notification = Notification.builder()
                .toCustomerId(request.toCustomerId())
                .toCustomerEmail(request.toCustomerEmail())
                .message(request.message())
                .sender("Mohamed Ali")
                .sentAt(LocalDateTime.now())
                .build();

        notificationRepository.save(notification);

    }
}
