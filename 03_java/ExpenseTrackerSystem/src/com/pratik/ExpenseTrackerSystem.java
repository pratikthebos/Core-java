package com.pratik;

import java.util.*;

public class ExpenseTrackerSystem {

    static class Expense {

        private int expenseId;
        private String category;
        private double amount;

        public Expense(int expenseId,
                       String category,
                       double amount) {

            this.expenseId = expenseId;
            this.category = category;
            this.amount = amount;
        }

        public String getCategory() {
            return category;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {

            return "Expense ID: " + expenseId +
                    ", Category: " + category +
                    ", Amount: ₹" + amount;
        }
    }

    static class ExpenseManager {

        private final List<Expense> expenses;

        public ExpenseManager() {
            expenses = new ArrayList<>();
        }

        public void addExpense(
                Expense expense) {

            expenses.add(expense);

            System.out.println(
                    "Expense Added Successfully");
        }

        public double getTotalExpense() {

            double total = 0;

            for (Expense expense : expenses) {
                total += expense.getAmount();
            }

            return total;
        }

        public void categoryWiseSummary() {

            Map<String, Double> summary =
                    new HashMap<>();

            for (Expense expense : expenses) {

                summary.put(
                        expense.getCategory(),
                        summary.getOrDefault(
                                expense.getCategory(),
                                0.0)
                                + expense.getAmount());
            }

            System.out.println(
                    "\nCategory Wise Expenses:");

            for (Map.Entry<String, Double> entry
                    : summary.entrySet()) {

                System.out.println(
                        entry.getKey()
                                + " : ₹"
                                + entry.getValue());
            }
        }

        public void displayExpenses() {

            System.out.println(
                    "\nExpense Records:");

            for (Expense expense : expenses) {
                System.out.println(expense);
            }
        }
    }

    public static void main(String[] args) {

        ExpenseManager manager =
                new ExpenseManager();

        manager.addExpense(
                new Expense(
                        1,
                        "Food",
                        450));

        manager.addExpense(
                new Expense(
                        2,
                        "Travel",
                        1200));

        manager.addExpense(
                new Expense(
                        3,
                        "Food",
                        300));

        manager.displayExpenses();

        manager.categoryWiseSummary();

        System.out.println(
                "\nTotal Expense: ₹"
                        + manager.getTotalExpense());
    }
}