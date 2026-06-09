package com.pratik;

import java.util.*;

public class DigitalWalletSystem {

    static class Wallet {

        private String userId;
        private double balance;
        private List<String> transactions;

        public Wallet(String userId) {

            this.userId = userId;
            this.balance = 0.0;
            this.transactions =
                    new ArrayList<>();
        }

        public void addMoney(double amount) {

            balance += amount;

            transactions.add(
                    "Added ₹" + amount);
        }

        public boolean transferMoney(
                Wallet receiver,
                double amount) {

            if (amount > balance) {

                System.out.println(
                        "Insufficient Balance");

                return false;
            }

            balance -= amount;
            receiver.balance += amount;

            transactions.add(
                    "Transferred ₹" +
                    amount +
                    " to " +
                    receiver.userId);

            receiver.transactions.add(
                    "Received ₹" +
                    amount +
                    " from " +
                    userId);

            return true;
        }

        public void showBalance() {

            System.out.println(
                    userId +
                    " Balance: ₹" +
                    balance);
        }

        public void showTransactions() {

            System.out.println(
                    "\nTransaction History of "
                    + userId);

            for (String transaction
                    : transactions) {

                System.out.println(
                        transaction);
            }
        }
    }

    public static void main(String[] args) {

        Wallet pratik =
                new Wallet("Pratik");

        Wallet amit =
                new Wallet("Amit");

        pratik.addMoney(5000);

        pratik.transferMoney(
                amit,
                1500);

        pratik.showBalance();
        amit.showBalance();

        pratik.showTransactions();
        amit.showTransactions();
    }
}