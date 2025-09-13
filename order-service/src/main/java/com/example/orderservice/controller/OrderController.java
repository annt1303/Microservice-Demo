package com.example.orderservice.controller;

import com.example.orderservice.entity.Order;
import com.example.orderservice.serivce.IOrderService;
import lombok.RequiredArgsConstructor;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Map;

@RestController
@RequiredArgsConstructor
@RequestMapping("/orders")
public class OrderController {
    private final IOrderService orderService;


    @GetMapping
    public List<Order> all() {
        return orderService.getAllOrders();
    }

    @PostMapping
    public Order create(@RequestBody Order order) {
        return orderService.createOrder(order);
    }

    @GetMapping("/{id}")
    public Map<String, Object> get(@PathVariable Long id) {
        return orderService.getOrderWithUser(id);
    }
}

