package com.company;

import com.company.model.Expense;

import java.util.Scanner;
import java.util.UUID;

public class Main {

    private static final ExpenseManagement expenseManagement = new ExpenseManagement();
    private static final Scanner scanner = new Scanner(System.in);

    public static void main(String[] args) {
        try {
            boolean stop = false;
            while (!stop) {
                printMenu();
                System.out.println("Enter Your Choice: ");
                int choice = scanner.nextInt();
                switch (choice) {
                    case 1 -> {
                        System.out.println("Enter the category Name: ");
                        String name = scanner.next();
                        System.out.println("Enter the category description: ");
                        String description = scanner.next();
                        scanner.nextLine();
                        expenseManagement.addCategory(name, description);
                        System.out.println("Category " + name + " has been added..");
                    }
                    case 2 -> expenseManagement.listCategories();
                    case 3 -> {
                        System.out.println("Enter category name to delete");
                        String categoryName = scanner.next();
                        if (expenseManagement.deleteCategory(categoryName)) {
                            System.out.println("Category " + categoryName + " has been deleted");
                        } else {
                            System.out.println("Category " + categoryName + " has not been deleted");
                        }
                    }
                    case 4 -> {
                        System.out.println("Enter category name to list: ");
                        String catName = scanner.next();
                        scanner.nextLine();
                        expenseManagement.listExpensesOfCategory(catName);
                    }
                    case 5 -> {
                        System.out.println("Enter category name to update: ");
                        String caName = scanner.next();
                        System.out.println("Enter new category name: ");
                        String newCatName = scanner.next();
                        System.out.println("Enter new category description: ");
                        String newCatDescription = scanner.nextLine();
                        scanner.nextLine();
                        if (expenseManagement.updateCategoryByName(caName, newCatName, newCatDescription)) {
                            System.out.println("category " + caName + " has been updated");
                        } else {
                            System.out.println("category not updated");
                        }
                    }
                    case 6 -> {
                        System.out.println("Enter category name to update: ");
                        String cateName = scanner.next();
                        System.out.println("Enter expense name: ");
                        String expenseName = scanner.next();
                        System.out.println("Enter expense amount: ");
                        scanner.nextLine();
                        double amount = scanner.nextDouble();
                        scanner.nextLine();
                        Expense expense = expenseManagement.addExpense(cateName, expenseName, amount);
                        System.out.println("Expense " + expense + " has been added");
                    }
                    case 7 -> {
                        System.out.println("Enter category name to delete: ");
                        String deleteCategoryName = scanner.next();
                        System.out.println("Enter expense id to delete: ");
                        String expenseId = scanner.next();
                        UUID uuid;
                        try {
                            uuid = UUID.fromString(expenseId);
                        } catch (IllegalArgumentException err) {
                            System.out.println("Please enter correct id");
                            break;
                        }
                        scanner.nextLine();
                        if (expenseManagement.deleteExpense(deleteCategoryName, uuid)) {
                            System.out.println("expense with id " + uuid + " has been deleted");
                        } else {
                            System.out.println("could not delete");
                        }
                    }
                    case 8 -> {
                        System.out.println("Enter category name to update: ");
                        String updateCategoryName = scanner.next();
                        System.out.println("Enter expense id to update: ");
                        String expenseIdToUpdate = scanner.next();
                        UUID expenseIdUUid;
                        try {
                            expenseIdUUid = UUID.fromString(expenseIdToUpdate);
                        } catch (IllegalArgumentException err) {
                            System.out.println("Please enter correct id");
                            break;
                        }
                        System.out.println("Enter new name: ");
                        String newName = scanner.next();
                        scanner.nextLine();
                        System.out.println("Enter new Amount");
                        double newAmount = scanner.nextDouble();
                        scanner.nextLine();
                        Expense expense1 = expenseManagement.updateExpenseByCategory(updateCategoryName, expenseIdUUid, newName, newAmount);
                        System.out.println("Expense " + expense1 + " has been updated");
                    }
                    case 9 -> expenseManagement.listExpensesOfTheDay();
                    default -> {
                        System.out.println("Thank you for using expense tracker OOPS Application");
                        stop = true;
                    }
                }
            }
        } catch (Exception err) {
            err.printStackTrace();
        }

    }

    private static void printMenu() {
        System.out.println("1) add category [+]\n" +
                "2) list category [+]\n" +
                "3) delete category [+]\n" +
                "4) list all expenses of category [+]\n" +
                "5) update category by name [+]\n" +
                "6) add expense to category [+]\n" +
                "7) delete expenses of category [+]\n" +
                "8) update expense by category id and expense id [+]\n" +
                "9) list expenses of the day [-]");
        System.out.println("10) exit");
    }

}
