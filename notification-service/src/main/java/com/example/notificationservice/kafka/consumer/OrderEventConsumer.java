package com.example.notificationservice.kafka.consumer;

import com.example.notificationservice.dto.OrderCreatedEvent;
import com.example.notificationservice.serivce.NotificationService;
import com.google.gson.Gson;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.annotation.KafkaListener;
import org.springframework.stereotype.Component;

@Component
@RequiredArgsConstructor
public class OrderEventConsumer {

    private final NotificationService notificationService;

    @KafkaListener(topics = "order-created", groupId = "notification-service")
    public void consumeOrderEvent(String message) {
        // parse JSON -> Object
        OrderCreatedEvent event = new Gson().fromJson(message, OrderCreatedEvent.class);

        // gọi service để xử lý
        notificationService.sendOrderConfirmation(event);
    }
}

