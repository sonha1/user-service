package com.gtel.user_service.controller;

import com.gtel.user_service.model.request.SendNotificationRequest;
import com.gtel.user_service.publisher.NotificationPublisher;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
@RequestMapping("notification")
public class NotificationController {
    private final NotificationPublisher notificationPublisher;

    @Value("${spring.rabbitmq.routing-key.notification.email}")
    String emailRouting;


    @Value("${spring.rabbitmq.routing-key.notification.sms}")
    String smsRouting;

    @Value("${spring.rabbitmq.routing-key.notification.discord}")
    String discordRouting;

    @PostMapping("send")
    public void sendNotification(@RequestBody SendNotificationRequest request) {
        notificationPublisher.sendMessage(request.getMessage(), getRoutingKey(request.getType()));
    }

    public String getRoutingKey(Integer type) {
        return switch (type) {
            case 1 -> emailRouting;
            case 2 -> smsRouting;
            case 3 -> discordRouting;
            default -> "";
        };
    }
}
