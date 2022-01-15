package com.company;

import com.company.model.Category;
import com.company.model.Expense;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.UUID;

/**
 * created by chethan on 15-01-2022
 **/
public class CategoryManagement {
    protected List<Category> categories;

    public CategoryManagement() {
        this.categories = new ArrayList<>();
    }

    /**
     * adds a category to category list
     * @param name
     * @param description
     * @return
     */

    public Category addCategory(String name,String description){
        Category category = new Category(UUID.randomUUID(),name,description, LocalDateTime.now());
        categories.add(category);
        return category;
    }

    public boolean deleteCategory(String name){

        Category categoryToDelete = findCategoryByName(name);

        if (categoryToDelete != null){
            categories.remove(categoryToDelete);
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

    protected Category findCategoryByName(String name){
        return categories.stream().filter(category -> category.getName().equals(name)).findFirst().orElse(null);
    }


    public void listCategories(){
        categories.forEach(System.out::println);
    }

    public void listExpensesOfCategory(String categoryName){
        Category category = findCategoryByName(categoryName);
        if (category != null){
            for (Expense expense : category.getExpenses()) {
                System.out.println(expense);
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
        Category category = findCategoryByName(oldName);
        if (category != null){
            category.setName(name);
            category.setDescription(description);
            return true;
        }
        System.out.println("category with name " + oldName + " not found");
        return false;
    }

}
