package com.example.Food.controller;

import com.example.Food.entity.FoodItem;
import com.example.Food.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/food")
public class FoodItemController {

    @Autowired
    private FoodItemService foodItemService;

    @PostMapping
    public FoodItem create(@RequestBody FoodItem foodItem) {
        return foodItemService.create(foodItem);
    }

    @GetMapping
    public List<FoodItem> getAll() {
        return foodItemService.getAll();
    }

    @GetMapping("/{id}")
    public FoodItem getById(@PathVariable Long id) {
        return foodItemService.getById(id);
    }

    @PutMapping("/{id}")
    public FoodItem update(@PathVariable Long id, @RequestBody FoodItem foodItem) {
        return foodItemService.update(id, foodItem);
    }

    @DeleteMapping("/{id}")
    public void delete(@PathVariable Long id) {
        foodItemService.delete(id);
    }
}