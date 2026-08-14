package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class BankTransactionAnalyzer {

    static class Transaction {

        private int id;
        private String type;
        private double amount;

        public Transaction(int id, String type, double amount) {
            this.id = id;
            this.type = type;
            this.amount = amount;
        }

        public int getId() {
            return id;
        }

        public String getType() {
            return type;
        }

        public double getAmount() {
            return amount;
        }

        @Override
        public String toString() {
            return String.format(
                    "Transaction ID: %-5d | Type: %-10s | Amount: %.2f",
                    id, type, amount
            );
        }
    }

    private static final List<Transaction> transactions = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleTransactions();

        while (true) {

            System.out.println("\n=================================");
            System.out.println("      BANK TRANSACTION ANALYZER");
            System.out.println("=================================");
            System.out.println("1. Add Transaction");
            System.out.println("2. Display Transactions");
            System.out.println("3. Calculate Total Deposits");
            System.out.println("4. Calculate Total Withdrawals");
            System.out.println("5. Find Largest Transaction");
            System.out.println("6. Search Transaction");
            System.out.println("7. Exit");
            System.out.println("=================================");

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    addTransaction(scanner);
                    break;

                case 2:
                    displayTransactions();
                    break;

                case 3:
                    calculateTotal("Deposit");
                    break;

                case 4:
                    calculateTotal("Withdrawal");
                    break;

                case 5:
                    findLargestTransaction();
                    break;

                case 6:
                    searchTransaction(scanner);
                    break;

                case 7:
                    System.out.println("\nThank you. Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void loadSampleTransactions() {

        transactions.add(new Transaction(1001, "Deposit", 50000));
        transactions.add(new Transaction(1002, "Withdrawal", 12000));
        transactions.add(new Transaction(1003, "Deposit", 25000));
        transactions.add(new Transaction(1004, "Withdrawal", 5000));
        transactions.add(new Transaction(1005, "Deposit", 15000));
    }

    private static void addTransaction(Scanner scanner) {

        System.out.println("\n--- Add Transaction ---");

        System.out.print("Enter Transaction ID: ");
        int id = scanner.nextInt();

        System.out.print("Enter Type (Deposit/Withdrawal): ");
        String type = scanner.next();

        if (!type.equalsIgnoreCase("Deposit")
                && !type.equalsIgnoreCase("Withdrawal")) {

            System.out.println("Invalid transaction type.");
            return;
        }

        System.out.print("Enter Amount: ");
        double amount = scanner.nextDouble();

        if (amount <= 0) {
            System.out.println("Amount must be greater than zero.");
            return;
        }

        transactions.add(
                new Transaction(id, type, amount)
        );

        System.out.println("Transaction added successfully.");
    }

    private static void displayTransactions() {

        System.out.println("\n--- All Transactions ---");

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        for (Transaction transaction : transactions) {
            System.out.println(transaction);
        }

        System.out.println("---------------------------------");
        System.out.println("Total Transactions: " + transactions.size());
    }

    private static void calculateTotal(String type) {

        double total = 0;

        for (Transaction transaction : transactions) {

            if (transaction.getType().equalsIgnoreCase(type)) {
                total += transaction.getAmount();
            }
        }

        System.out.printf(
                "\nTotal %ss: %.2f%n",
                type,
                total
        );
    }

    private static void findLargestTransaction() {

        if (transactions.isEmpty()) {
            System.out.println("No transactions available.");
            return;
        }

        Transaction largest = transactions.get(0);

        for (Transaction transaction : transactions) {

            if (transaction.getAmount() > largest.getAmount()) {
                largest = transaction;
            }
        }

        System.out.println("\n--- Largest Transaction ---");
        System.out.println(largest);
    }

    private static void searchTransaction(Scanner scanner) {

        System.out.print("\nEnter Transaction ID: ");
        int id = scanner.nextInt();

        boolean found = false;

        for (Transaction transaction : transactions) {

            if (transaction.getId() == id) {

                System.out.println("\nTransaction Found:");
                System.out.println(transaction);

                found = true;
                break;
            }
        }

        if (!found) {
            System.out.println("Transaction not found.");
        }
    }
}