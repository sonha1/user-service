package com.gtel.user_service.publisher;


import lombok.RequiredArgsConstructor;
import org.hibernate.annotations.Comment;
import org.springframework.amqp.rabbit.core.RabbitTemplate;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class NotificationPublisher {
//    private final Rabbit
    private final RabbitTemplate rabbitTemplate;

    @Value("${spring.rabbitmq.exchanges.notification}")
    String notificationExchange;

    public void sendMessage(String message, String routingKey) {
        rabbitTemplate.convertAndSend(notificationExchange, routingKey, message);
    }
}
