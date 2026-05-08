package com.example.Food.service.Impl;

import com.example.Food.entity.*;
import com.example.Food.repository.*;
import com.example.Food.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class CartServiceImpl implements CartService {

    @Autowired
    private com.example.Food.repository.CartRepository cartRepository;

    @Autowired
    private CartItemRepository cartItemRepository;

    @Autowired
    private UserRepository userRepository;

    @Autowired
    private FoodItemRepository foodItemRepository;

    private Cart getOrCreateCart(User user) {
        return cartRepository.findByUser(user)
                .orElseGet(() -> {
                    Cart cart = new Cart();
                    cart.setUser(user);
                    return cartRepository.save(cart);
                });
    }

    @Override
    public Cart getCartByUserId(Long userId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        return getOrCreateCart(user);
    }

    @Override
    public Cart addItem(Long userId, Long foodItemId, int quantity) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        Cart cart = getOrCreateCart(user);

        Optional<CartItem> existingItem =
                cartItemRepository.findByCartAndFoodItem(cart, foodItem);

        if (existingItem.isPresent()) {
            CartItem item = existingItem.get();
            item.setQuantity(item.getQuantity() + quantity);
            cartItemRepository.save(item);
        } else {
            CartItem newItem = new CartItem();
            newItem.setCart(cart);
            newItem.setFoodItem(foodItem);
            newItem.setQuantity(quantity);
            cartItemRepository.save(newItem);
        }

        return cart;
    }

    @Override
    public Cart removeItem(Long userId, Long foodItemId) {

        User user = userRepository.findById(userId)
                .orElseThrow(() -> new RuntimeException("User not found"));

        FoodItem foodItem = foodItemRepository.findById(foodItemId)
                .orElseThrow(() -> new RuntimeException("Food item not found"));

        Cart cart = getOrCreateCart(user);

        CartItem item = cartItemRepository.findByCartAndFoodItem(cart, foodItem)
                .orElseThrow(() -> new RuntimeException("Item not in cart"));

        cartItemRepository.delete(item);

        return cart;
    }

    @Override
    public Cart clearCart(Long userId) {

        Cart cart = getCartByUserId(userId);

        cartItemRepository.deleteAll(
                cartItemRepository.findAll().stream()
                        .filter(i -> i.getCart().getId().equals(cart.getId()))
                        .toList()
        );

        return cart;
    }
}