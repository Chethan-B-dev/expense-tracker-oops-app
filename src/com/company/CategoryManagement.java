package com.company;

import com.company.model.Category;
import com.company.model.Expense;
import java.time.LocalDateTime;
import java.util.*;

/**
 * created by chethan on 15-01-2022
 **/
public class CategoryManagement {

    protected List<Category> categories;

    public CategoryManagement() {
        this.categories = new ArrayList<>();
    }

    public Category addCategory(String name,String description){
        Category category = new Category(UUID.randomUUID(), name, description, LocalDateTime.now());
        categories.add(category);
        return category;
    }

    public boolean deleteCategory(String name){
        Optional<Category> category = findCategoryByName(name);
        if (category.isPresent()){
            categories.remove(category.get());
            return true;
        }
        return false;
    }

    public boolean deleteCategory(UUID id){
        Category categoryToDelete = findCategoryById(id);
        if (categoryToDelete != null){
            categories.remove(categoryToDelete);
            return true;
        }
        return false;
    }

    protected Category findCategoryById(UUID id){
        return categories.stream().filter(category -> Objects.equals(category.getId(),id)).findFirst().orElse(null);
    }

    protected Optional<Category> findCategoryByName(String name){
        return categories.stream().filter(category -> category.getName().equals(name)).findFirst();
    }

    public void listCategories(){
        categories.forEach(System.out::println);
    }

    public void listExpensesOfCategory(String categoryName){
        Optional<Category> category = findCategoryByName(categoryName);
        if (category.isPresent()){
            category.get().getExpenses().forEach(System.out::println);
            if (category.get().getExpenses().isEmpty()){
                System.out.println("There Are no Expenses in the category - " + category.get().getName());
            }
        } else {
            System.out.println("Category " + categoryName + " not found.");
        }
    }

    public void listExpensesOfCategory(UUID categoryId){
        Category category = findCategoryById(categoryId);
        if (category != null){
            for (Expense expense : category.getExpenses()) {
                System.out.println(expense);
            }
        } else {
            System.out.println("Category with id " + categoryId + " not found.");
        }
    }

    public boolean updateCategoryById(UUID categoryId,String name,String description){
        Category category = findCategoryById(categoryId);
        if (category != null){
            category.setName(name);
            category.setDescription(description);
            return true;
        }
        System.out.println("category with id " + categoryId + " not found");
        return false;
    }

    public boolean updateCategoryByName(String oldName,String name,String description){
        Optional<Category> category = findCategoryByName(oldName);
        if (category.isPresent()){
            category.get().setName(name);
            category.get().setDescription(description);
            return true;
        }
        System.out.println("category with name " + oldName + " not found");
        return false;
    }

}
