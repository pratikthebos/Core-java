package com.pratik;

import java.util.*;

public class PaymentGatewaySimulator {

    static class Transaction {

        private String transactionId;
        private double amount;
        private String status;

        public Transaction(double amount) {
            this.transactionId =
                    UUID.randomUUID().toString();
            this.amount = amount;
            this.status = "PENDING";
        }

        public void markSuccess() {
            status = "SUCCESS";
        }

        public void markFailed() {
            status = "FAILED";
        }

        @Override
        public String toString() {
            return "Transaction ID: " + transactionId +
                    ", Amount: ₹" + amount +
                    ", Status: " + status;
        }
    }

    static class Account {

        private String userId;
        private double balance;
        private List<Transaction> transactions;

        public Account(String userId) {
            this.userId = userId;
            this.balance = 0;
            this.transactions = new ArrayList<>();
        }

        public void addBalance(double amount) {
            balance += amount;
        }

        public boolean processPayment(double amount) {

            Transaction transaction =
                    new Transaction(amount);

            if (balance >= amount) {
                balance -= amount;
                transaction.markSuccess();
                transactions.add(transaction);
                return true;
            }

            transaction.markFailed();
            transactions.add(transaction);
            return false;
        }

        public void showHistory() {
            System.out.println(
                    "\nTransaction History for " + userId);

            for (Transaction transaction : transactions) {
                System.out.println(transaction);
            }
        }

        public void showBalance() {
            System.out.println(
                    userId + " Balance: ₹" + balance);
        }
    }

    public static void main(String[] args) {

        Account account =
                new Account("USER_101");

        account.addBalance(5000);

        account.processPayment(1200);
        account.processPayment(4500);

        account.showBalance();
        account.showHistory();
    }
}