package com.example.Food.service.impl;

import com.example.Food.entity.*;
import com.example.Food.enums.OrderStatus;
import com.example.Food.repository.*;
import com.example.Food.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class OrderServiceImpl implements OrderService {

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private OrderRepository orderRepository;

    @Autowired
    private OrderItemRepository orderItemRepository;

    @Override
    public Order placeOrder(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        Cart cart = cartRepository.findByUser(user)
                .orElseThrow(() -> new RuntimeException("Cart is empty"));

        List<CartItem> cartItems = cartItemRepository.findAll()
                .stream()
                .filter(i -> i.getCart().getId().equals(cart.getId()))
                .toList();

        if (cartItems.isEmpty()) {
            throw new RuntimeException("Cart is empty");
        }

        Order order = new Order();
        order.setUser(user);
        order.setStatus(OrderStatus.PLACED);
        order.setCreatedAt(LocalDateTime.now());

        Order savedOrder = orderRepository.save(order);

        double total = 0;

        for (CartItem cartItem : cartItems) {

            OrderItem orderItem = new OrderItem();
            orderItem.setOrder(savedOrder);
            orderItem.setFoodItem(cartItem.getFoodItem());
            orderItem.setQuantity(cartItem.getQuantity());

            double price = cartItem.getFoodItem().getPrice() * cartItem.getQuantity();
            orderItem.setPrice(price);

            total += price;

            orderItemRepository.save(orderItem);
        }

        savedOrder.setTotal(total);
        orderRepository.save(savedOrder);

        // clear cart
        cartItemRepository.deleteAll(cartItems);

        return savedOrder;
    }

    @Override
    public List<Order> getOrdersByUser(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return orderRepository.findByUser(user);
    }

    @Override
    public Order getOrderById(Long orderId) {

        return orderRepository.findById(orderId)
                .orElseThrow(() -> new RuntimeException("Order not found"));
    }

    @Override
    public Order updateStatus(Long orderId, String status) {

        Order order = getOrderById(orderId);

        order.setStatus(OrderStatus.valueOf(status));

        return orderRepository.save(order);
    }
}