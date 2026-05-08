package com.example.Food.service;

import com.example.Food.entity.FoodItem;

import java.util.List;

public interface FoodItemService {

    FoodItem create(FoodItem foodItem);

    List<FoodItem> getAll();

    FoodItem getById(Long id);

    FoodItem update(Long id, FoodItem foodItem);

    void delete(Long id);
}