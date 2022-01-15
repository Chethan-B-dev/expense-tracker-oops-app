package com.company;

import com.company.model.Category;
import com.company.model.Expense;

import java.text.SimpleDateFormat;
import java.time.Instant;
import java.time.LocalDateTime;
import java.time.ZoneId;
import java.time.ZonedDateTime;
import java.time.temporal.ChronoUnit;
import java.util.*;

/**
 * created by chethan on 15-01-2022
 **/
public class ExpenseManagement extends CategoryManagement{


    public Expense addExpense(UUID categoryId,String name, double amount){
        Category category = findCategoryById(categoryId);
        if (category != null){
            Expense expense = new Expense(UUID.randomUUID(),name,amount, LocalDateTime.now());
            category.getExpenses().add(expense);
            return expense;
        }
        return null;
    }

    public Expense addExpense(String categoryName,String name, double amount){
        Category category = findCategoryByName(categoryName);
        if (category != null){
            Expense expense = new Expense(UUID.randomUUID(),name,amount, LocalDateTime.now());
            category.getExpenses().add(expense);
            return expense;
        }
        return null;
    }

    private Expense findExpenseById(List<Expense> expenseList,UUID expenseId){
        return expenseList.stream().filter(expense -> Objects.equals(expense.getId(),expenseId)).findFirst().orElse(null);
    }

    public boolean deleteExpense(String categoryName,UUID expenseId){
        Category category = findCategoryByName(categoryName);
        if (category != null){
            Expense expense = findExpenseById(category.getExpenses(),expenseId);
            if (expense != null){
                category.getExpenses().remove(expense);
            }
            return true;
        }
        return false;
    }

    public boolean deleteExpense(UUID categoryId,UUID expenseId){
        Category category = findCategoryById(categoryId);
        if (category != null){
            Expense expense = findExpenseById(category.getExpenses(),expenseId);
            if (expense != null){
                category.getExpenses().remove(expense);
            }
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
        Category category = findCategoryByName(categoryName);
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

    private void printExpenses(List<Expense> expenses){
        expenses.forEach(System.out::println);
    }

    public List<Expense> listExpensesOfTheDay(){
        List<Expense> expenses = new ArrayList<>();
        for (Category category : categories) {
            for (Expense expense : category.getExpenses()) {
                Date today = Date.from(LocalDateTime.now().atZone(ZoneId.systemDefault()).toInstant());
                Date expenseDate = Date.from(expense.getCreatedAt().atZone(ZoneId.systemDefault()).toInstant());
                if (new SimpleDateFormat("yyyyMMdd").format(expenseDate).equals(new SimpleDateFormat("yyyyMMdd").format(today))){
                    expenses.add(expense);
                }
            }
        }
        printExpenses(expenses);
        return expenses;
    }

}
