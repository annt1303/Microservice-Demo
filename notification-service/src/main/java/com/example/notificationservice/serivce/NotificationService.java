package com.example.notificationservice.serivce;

import com.example.notificationservice.dto.OrderCreatedEvent;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

@Service
@RequiredArgsConstructor
public class NotificationService {

    public void sendOrderConfirmation(OrderCreatedEvent event) {
        String msg = "Your order " + event.getOrderId() + " has been placed successfully. Total: " + event.getTotalAmount();

        // giả lập gửi mail
        System.out.println("📧 Sent email to " + event.getCustomerEmail() + ": " + msg);
    }
}

