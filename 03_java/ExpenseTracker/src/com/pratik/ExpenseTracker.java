package com.pratik;

import java.util.ArrayList;
import java.util.Scanner;

public class ExpenseTracker {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        ArrayList<Double> expenses = new ArrayList<>();

        System.out.println("=== Expense Tracker ===");

        while (true) {
            System.out.println("\n1. Add Expense");
            System.out.println("2. Show Total");
            System.out.println("3. Exit");
            System.out.print("Choose: ");

            int choice = sc.nextInt();

            if (choice == 1) {
                System.out.print("Enter expense: ");
                expenses.add(sc.nextDouble());
                System.out.println("Expense added.");
            } 
            else if (choice == 2) {
                double total = 0;

                for (double expense : expenses) {
                    total += expense;
                }

                System.out.println("Total Expense: ₹" + total);
            } 
            else if (choice == 3) {
                System.out.println("Thank you!");
                break;
            } 
            else {
                System.out.println("Invalid choice.");
            }
        }

        sc.close();
    }
}