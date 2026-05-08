package com.example.Food.service.Impl;

import com.example.Food.entity.Category;
import com.example.Food.entity.FoodItem;
import com.example.Food.repository.CategoryRepository;
import com.example.Food.repository.FoodItemRepository;
import com.example.Food.service.FoodItemService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
public class FoodItemServiceImpl implements FoodItemService {

    @Autowired
    private FoodItemRepository foodItemRepository;

    @Autowired
    private CategoryRepository categoryRepository;

    @Override
    public FoodItem create(FoodItem foodItem) {

        Long categoryId = foodItem.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        foodItem.setCategory(category);

        return foodItemRepository.save(foodItem);
    }

    @Override
    public List<FoodItem> getAll() {
        return foodItemRepository.findAll();
    }

    @Override
    public FoodItem getById(Long id) {
        return foodItemRepository.findById(id)
                .orElseThrow(() -> new RuntimeException("Food item not found"));
    }

    @Override
    public FoodItem update(Long id, FoodItem foodItem) {

        FoodItem existing = getById(id);

        existing.setName(foodItem.getName());
        existing.setPrice(foodItem.getPrice());
        existing.setStatus(foodItem.getStatus());

        Long categoryId = foodItem.getCategory().getId();

        Category category = categoryRepository.findById(categoryId)
                .orElseThrow(() -> new RuntimeException("Category not found"));

        existing.setCategory(category);

        return foodItemRepository.save(existing);
    }

    @Override
    public void delete(Long id) {
        foodItemRepository.deleteById(id);
    }
}