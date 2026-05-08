package com.example.Food.service;

import com.example.Food.entity.Cart;

public interface CartService {

    Cart getCartByUserId(Long userId);

    Cart addItem(Long userId, Long foodItemId, int quantity);

    Cart removeItem(Long userId, Long foodItemId);

    Cart clearCart(Long userId);
}