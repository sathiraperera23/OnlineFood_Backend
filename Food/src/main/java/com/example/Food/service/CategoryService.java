package com.example.Food.service;

import com.example.Food.entity.Category;

import java.util.List;

public interface CategoryService {

    Category create(Category category);

    List<Category> getAll();

    Category getById(Long id);

    Category update(Long id, Category category);

    void delete(Long id);
}