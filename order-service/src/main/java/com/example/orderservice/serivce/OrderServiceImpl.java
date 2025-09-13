package com.example.orderservice.serivce;

import com.example.orderservice.feign.UserClient;
import com.example.orderservice.entity.Order;
import com.example.orderservice.dto.UserDto;
import com.example.orderservice.kafka.event.OrderEvent;
import com.example.orderservice.kafka.producer.OrderProducer;
import com.example.orderservice.repository.OrderRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.cache.annotation.CacheEvict;
import org.springframework.cache.annotation.Cacheable;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Map;

@Service
@RequiredArgsConstructor
public class OrderServiceImpl implements IOrderService {
    private final OrderRepository orderRepository;
    private final UserClient userClient;
    private final OrderProducer orderProducer;


    @Override
    @Cacheable(value = "orders")
    public List<Order> getAllOrders() {
        System.out.println("Fetching from DB...");
        return orderRepository.findAll();
    }

    @Override
    @CacheEvict(value = "orders", allEntries = true)
    public Order createOrder(Order order) {
        Order saved = orderRepository.save(order);

        // tạo message
        OrderEvent event = new OrderEvent();
        event.setOrderId(saved.getId().toString());
        event.setCustomerEmail("test@gmail.com");
        event.setContent("Your order " + saved.getId() + " has been created!");

        // gửi message qua Kafka
        orderProducer.sendOrderCreatedEvent(event);

        return saved;
    }

    @Override
    public Map<String, Object> getOrderWithUser(Long id) {
        Order order = orderRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Order not found: " + id));
        UserDto user = userClient.getUser(order.getUserId());
        return Map.of("order", order, "user", user);
    }
}

