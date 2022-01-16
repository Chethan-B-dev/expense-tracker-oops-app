package com.company;

import com.company.model.Category;
import com.company.model.Expense;

import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.*;

/**
 * created by chethan on 15-01-2022
 **/
public class ExpenseManagement extends CategoryManagement{

    public Expense addExpense(UUID categoryId,String name, double amount){
        Category category = findCategoryById(categoryId);
        if (category != null){
            Expense expense = new Expense(UUID.randomUUID(),name,amount, LocalDateTime.now(),category);
            category.getExpenses().add(expense);
            return expense;
        }
        return null;
    }

    public Expense addExpense(String categoryName,String name, double amount){
        Optional<Category> category = findCategoryByName(categoryName);
        if (category.isPresent()){
            Expense expense = new Expense(UUID.randomUUID(),name,amount, LocalDateTime.now(),category.get());
            category.get().getExpenses().add(expense);
            return expense;
        }
        return null;
    }

    private Expense findExpenseById(List<Expense> expenseList,UUID expenseId){
        return expenseList.stream().filter(expense -> Objects.equals(expense.getId(),expenseId)).findFirst().orElse(null);
    }

    public boolean deleteExpense(String categoryName,UUID expenseId){
        Optional<Category> category = findCategoryByName(categoryName);
        if (category.isPresent()){
            Expense expense = findExpenseById(category.get().getExpenses(),expenseId);
            if (expense != null)
                category.get().getExpenses().remove(expense);
            return true;
        }
        return false;
    }

    public boolean deleteExpense(UUID categoryId,UUID expenseId){
        Category category = findCategoryById(categoryId);
        if (category != null){
            Expense expense = findExpenseById(category.getExpenses(),expenseId);
            if (expense != null)
                category.getExpenses().remove(expense);
            return true;
        }
        return false;
    }

    public Expense updateExpenseByCategory(UUID categoryId,UUID expenseId,String name,double amount){
        Category category = findCategoryById(categoryId);
        if (category != null){
            Expense expense = findExpenseById(category.getExpenses(),expenseId);
            if (expense != null){
                expense.setAmount(amount);
                expense.setName(name);
                return expense;
            }
        }
        return null;
    }

    public Expense updateExpenseByCategory(String categoryName,UUID expenseId,String name,double amount){
        Optional<Category> category = findCategoryByName(categoryName);
        if (category.isPresent()){
            Expense expense = findExpenseById(category.get().getExpenses(),expenseId);
            if (expense != null){
                expense.setAmount(amount);
                expense.setName(name);
                return expense;
            }
        }
        return null;
    }

    private void printExpenses(List<Expense> expenses){
        System.out.println("-----------------");
        expenses.forEach(System.out::println);
        System.out.println("-----------------");
    }

    public void listExpensesOfTheDay(){
        List<Expense> expenses = new ArrayList<>();
        double totalAmount = 0.0;
        for (Category category : categories) {
            for (Expense expense : category.getExpenses()) {
                LocalDate today = LocalDate.now();
                LocalDate expenseCreatedDay = expense.getCreatedAt().toLocalDate();
                if (expenseCreatedDay.toString().equals(today.toString())){
                    expenses.add(expense);
                    totalAmount += expense.getAmount();
                }
            }
        }
        printExpenses(expenses);
        System.out.println("Total Amount Spent Today - " + LocalDate.now() + " is : " + totalAmount);
    }

}
