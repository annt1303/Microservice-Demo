package com.example.orderservice.kafka.producer;

import com.example.orderservice.kafka.event.OrderEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.kafka.core.KafkaTemplate;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class OrderProducer {
    private final KafkaTemplate<String, OrderEvent> kafkaTemplate;

    public void sendOrderCreatedEvent(OrderEvent event) {
        kafkaTemplate.send("order-created", event.getOrderId(), event);
        System.out.println("📤 Sent Kafka message: " + event);
    }
}

