package com.example.Food.service;

import com.example.Food.entity.Order;

import java.util.List;

public interface OrderService {

    Order placeOrder(Long userId);

    List<Order> getOrdersByUser(Long userId);

    Order getOrderById(Long orderId);

    Order updateStatus(Long orderId, String status);
}