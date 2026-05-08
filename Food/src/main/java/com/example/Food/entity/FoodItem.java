package com.example.Food.entity;

import com.example.Food.enums.FoodStatus;
import jakarta.persistence.*;
import lombok.Data;

@Entity
@Data
public class FoodItem {

    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    private String name;
    private Double price;

    @Enumerated(EnumType.STRING)
    private FoodStatus status;

    @ManyToOne
    @JoinColumn(name = "category_id")
    private Category category;
}
