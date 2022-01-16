package com.company.model;

import java.time.LocalDateTime;
import java.util.UUID;

/**
 * created by chethan on 15-01-2022
 **/

public class Expense {
    private UUID id;
    private String name;
    private double amount;
    private LocalDateTime createdAt;
    private Category category;

    public Expense(UUID id, String name, double amount, LocalDateTime createdAt, Category category) {
        this.id = id;
        this.name = name;
        this.amount = amount;
        this.createdAt = createdAt;
        this.category = category;
    }

    public Category getCategory() {
        return category;
    }

    public void setCategory(Category category) {
        this.category = category;
    }

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public UUID getId() {
        return id;
    }

    public void setId(UUID id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public double getAmount() {
        return amount;
    }

    public void setAmount(double amount) {
        this.amount = amount;
    }

    @Override
    public String toString() {
        return "Expense{" + "id=" + id + ", name='" + name + '\'' + ", amount=" + amount + ", createdAt=" + createdAt + ", category=" + category.getName() + '}';
    }
}
