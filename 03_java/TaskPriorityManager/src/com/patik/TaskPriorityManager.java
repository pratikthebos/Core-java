package com.patik;

import java.util.PriorityQueue;
import java.util.Scanner;

public class TaskPriorityManager {

    enum Priority {
        HIGH(1),
        MEDIUM(2),
        LOW(3);

        private final int level;

        Priority(int level) {
            this.level = level;
        }

        public int getLevel() {
            return level;
        }
    }

    static class Task implements Comparable<Task> {

        private int id;
        private String title;
        private Priority priority;
        private boolean completed;

        public Task(int id, String title, Priority priority) {
            this.id = id;
            this.title = title;
            this.priority = priority;
            this.completed = false;
        }

        public int getId() {
            return id;
        }

        public String getTitle() {
            return title;
        }

        public Priority getPriority() {
            return priority;
        }

        public boolean isCompleted() {
            return completed;
        }

        public void complete() {
            completed = true;
        }

        @Override
        public int compareTo(Task other) {

            return Integer.compare(
                    this.priority.getLevel(),
                    other.priority.getLevel()
            );
        }

        @Override
        public String toString() {

            return String.format(
                    "ID: %-4d | Priority: %-6s | Status: %-10s | Task: %s",
                    id,
                    priority,
                    completed ? "COMPLETED" : "PENDING",
                    title
            );
        }
    }

    private static final PriorityQueue<Task> taskQueue =
            new PriorityQueue<>();

    private static int nextTaskId = 1001;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleTasks();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addTask(scanner);
                    break;

                case 2:
                    processNextTask();
                    break;

                case 3:
                    displayTasks();
                    break;

                case 4:
                    completeTask(scanner);
                    break;

                case 5:
                    searchTask(scanner);
                    break;

                case 6:
                    displayStatistics();
                    break;

                case 7:
                    System.out.println(
                            "\nTask Priority Manager closed."
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "\nInvalid choice."
                    );
            }
        }
    }

    private static void displayMenu() {

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "             TASK PRIORITY MANAGER"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Add Task");
        System.out.println("2. Process Highest Priority Task");
        System.out.println("3. Display Tasks");
        System.out.println("4. Complete Task");
        System.out.println("5. Search Task");
        System.out.println("6. Display Statistics");
        System.out.println("7. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void loadSampleTasks() {

        taskQueue.offer(
                new Task(
                        nextTaskId++,
                        "Fix production issue",
                        Priority.HIGH
                )
        );

        taskQueue.offer(
                new Task(
                        nextTaskId++,
                        "Review pull request",
                        Priority.MEDIUM
                )
        );

        taskQueue.offer(
                new Task(
                        nextTaskId++,
                        "Update documentation",
                        Priority.LOW
                )
        );
    }

    private static void addTask(Scanner scanner) {

        System.out.println("\n--- Add Task ---");

        System.out.print("Enter task title: ");
        String title = scanner.nextLine().trim();

        if (title.isEmpty()) {

            System.out.println(
                    "Task title cannot be empty."
            );

            return;
        }

        System.out.println(
                "\nSelect Priority:"
        );

        System.out.println("1. HIGH");
        System.out.println("2. MEDIUM");
        System.out.println("3. LOW");

        System.out.print("Enter priority: ");
        int priorityChoice = scanner.nextInt();
        scanner.nextLine();

        Priority priority;

        switch (priorityChoice) {

            case 1:
                priority = Priority.HIGH;
                break;

            case 2:
                priority = Priority.MEDIUM;
                break;

            case 3:
                priority = Priority.LOW;
                break;

            default:
                System.out.println(
                        "Invalid priority."
                );
                return;
        }

        Task task = new Task(
                nextTaskId++,
                title,
                priority
        );

        taskQueue.offer(task);

        System.out.println(
                "\nTask added successfully."
        );

        System.out.println(task);
    }

    private static void processNextTask() {

        if (taskQueue.isEmpty()) {

            System.out.println(
                    "\nNo pending tasks."
            );

            return;
        }

        Task task = taskQueue.poll();

        task.complete();

        System.out.println(
                "\n--- Processing Task ---"
        );

        System.out.println(task);

        System.out.println(
                "Task processed successfully."
        );
    }

    private static void displayTasks() {

        System.out.println(
                "\n--- Pending Tasks ---"
        );

        if (taskQueue.isEmpty()) {

            System.out.println(
                    "No pending tasks."
            );

            return;
        }

        PriorityQueue<Task> copy =
                new PriorityQueue<>(taskQueue);

        while (!copy.isEmpty()) {

            System.out.println(copy.poll());
        }

        System.out.println(
                "\nTotal Pending Tasks: "
                        + taskQueue.size()
        );
    }

    private static void completeTask(
            Scanner scanner) {

        System.out.println(
                "\n--- Complete Task ---"
        );

        System.out.print("Enter Task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task target = findTask(id);

        if (target == null) {

            System.out.println(
                    "Task not found."
            );

            return;
        }

        target.complete();

        taskQueue.remove(target);

        System.out.println(
                "Task completed successfully."
        );

        System.out.println(target);
    }

    private static void searchTask(
            Scanner scanner) {

        System.out.println(
                "\n--- Search Task ---"
        );

        System.out.print("Enter Task ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Task task = findTask(id);

        if (task == null) {

            System.out.println(
                    "Task not found."
            );

            return;
        }

        System.out.println(
                "\nTask Found:"
        );

        System.out.println(task);
    }

    private static Task findTask(int id) {

        for (Task task : taskQueue) {

            if (task.getId() == id) {
                return task;
            }
        }

        return null;
    }

    private static void displayStatistics() {

        int high = 0;
        int medium = 0;
        int low = 0;

        for (Task task : taskQueue) {

            switch (task.getPriority()) {

                case HIGH:
                    high++;
                    break;

                case MEDIUM:
                    medium++;
                    break;

                case LOW:
                    low++;
                    break;
            }
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "              TASK STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Pending : " + taskQueue.size()
        );

        System.out.println(
                "High Priority : " + high
        );

        System.out.println(
                "Medium Priority: " + medium
        );

        System.out.println(
                "Low Priority  : " + low
        );

        System.out.println(
                "=============================================="
        );
    }
}