package com.example.orderservice.serivce;

import com.example.orderservice.entity.Order;

import java.util.List;
import java.util.Map;

public interface IOrderService {
    List<Order> getAllOrders();
    Order createOrder(Order order);
    Map<String, Object> getOrderWithUser(Long id);
}
