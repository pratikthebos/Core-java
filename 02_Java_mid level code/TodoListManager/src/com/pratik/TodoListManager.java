package com.pratik;

import java.util.ArrayList;
import java.util.Scanner;

public class TodoListManager {

    static ArrayList<String> tasks = new ArrayList<>();
    static Scanner sc = new Scanner(System.in);

    public static void main(String[] args) {

        while (true) {

            System.out.println("\n--- TO-DO LIST MENU ---");
            System.out.println("1. Add Task");
            System.out.println("2. View Tasks");
            System.out.println("3. Remove Task");
            System.out.println("4. Exit");
            System.out.print("Enter choice: ");

            int choice = sc.nextInt();
            sc.nextLine(); // consume newline

            switch (choice) {
                case 1 -> addTask();
                case 2 -> viewTasks();
                case 3 -> removeTask();
                case 4 -> {
                    System.out.println("Goodbye!");
                    return;
                }
                default -> System.out.println("Invalid choice!");
            }
        }
    }

    static void addTask() {
        System.out.print("Enter new task: ");
        String task = sc.nextLine();
        tasks.add(task);
        System.out.println("Task added.");
    }

    static void viewTasks() {
        if (tasks.isEmpty()) {
            System.out.println("No tasks available.");
            return;
        }

        System.out.println("\nYour Tasks:");
        for (int i = 0; i < tasks.size(); i++) {
            System.out.println((i + 1) + ". " + tasks.get(i));
        }
    }

    static void removeTask() {
        viewTasks();
        if (tasks.isEmpty()) return;

        System.out.print("Enter task number to remove: ");
        int index = sc.nextInt();

        if (index >= 1 && index <= tasks.size()) {
            tasks.remove(index - 1);
            System.out.println("Task removed.");
        } else {
            System.out.println("Invalid task number.");
        }
    }
}
