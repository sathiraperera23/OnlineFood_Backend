package com.example.Food.repository;

import com.example.Food.entity.CartItem;
import com.example.Food.entity.Cart;
import com.example.Food.entity.FoodItem;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.Optional;

public interface CartItemRepository extends JpaRepository<CartItem, Long> {

    Optional<CartItem> findByCartAndFoodItem(Cart cart, FoodItem foodItem);
}