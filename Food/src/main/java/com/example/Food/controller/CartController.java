package com.example.Food.controller;

import com.example.Food.entity.Cart;
import com.example.Food.service.CartService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/cart")
public class CartController {

    @Autowired
    private CartService cartService;

    @GetMapping("/{userId}")
    public Cart getCart(@PathVariable Long userId) {
        return cartService.getCartByUserId(userId);
    }

    @PostMapping("/add")
    public Cart addItem(@RequestParam Long userId,
                        @RequestParam Long foodItemId,
                        @RequestParam int quantity) {
        return cartService.addItem(userId, foodItemId, quantity);
    }

    @DeleteMapping("/remove")
    public Cart removeItem(@RequestParam Long userId,
                           @RequestParam Long foodItemId) {
        return cartService.removeItem(userId, foodItemId);
    }

    @DeleteMapping("/clear/{userId}")
    public Cart clearCart(@PathVariable Long userId) {
        return cartService.clearCart(userId);
    }
}