package com.example.orderservice.entity;

import jakarta.persistence.*;
import lombok.Data;

import java.io.Serializable;


@Entity
@Data
@Table(name = "orders")
public class Order implements Serializable {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private Long userId;
    private String product;
}

