package com.gtel.user_service.subscribers;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;
import org.springframework.amqp.core.Message;
import org.springframework.amqp.rabbit.annotation.RabbitListener;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Service;

import java.nio.charset.StandardCharsets;

@Service
@RequiredArgsConstructor
@Slf4j
public class NotificationSubscriber {
    @RabbitListener(queues = "notification_email_queue")
    public void sendEmailHandler(Message message) {
        log.info("sending email notification with message = {}",  new String(message.getBody(), StandardCharsets.UTF_8));
    }


    @RabbitListener(queues = "notification_sms_queue")
    public void sendSmsHandler(Message message) {
        log.info("sending sms notification with message = {}",  new String(message.getBody(), StandardCharsets.UTF_8));
    }

    @RabbitListener(queues = "notification_discord_queue")
    public void sendDiscordHandler(Message message) {
        log.info("sending discord notification with message = {}",  new String(message.getBody(), StandardCharsets.UTF_8));
    }
}
