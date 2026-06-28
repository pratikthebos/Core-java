package com.pratik;

import java.util.*;
import java.util.concurrent.*;

public class DistributedTaskScheduler {

    enum TaskStatus {
        PENDING,
        RUNNING,
        COMPLETED
    }

    static class Task implements Comparable<Task> {
        private String taskId;
        private String taskName;
        private int priority;
        private long scheduledTime;
        private TaskStatus status;

        public Task(
                String taskId,
                String taskName,
                int priority,
                long delayMillis) {

            this.taskId = taskId;
            this.taskName = taskName;
            this.priority = priority;
            this.scheduledTime =
                    System.currentTimeMillis() + delayMillis;
            this.status = TaskStatus.PENDING;
        }

        public boolean isReady() {
            return System.currentTimeMillis() >= scheduledTime;
        }

        public void execute() {
            status = TaskStatus.RUNNING;

            System.out.println(
                    "Executing Task: " + taskName);

            status = TaskStatus.COMPLETED;
        }

        @Override
        public int compareTo(Task other) {
            return other.priority - this.priority;
        }

        @Override
        public String toString() {
            return taskId + " | " + taskName +
                    " | " + status;
        }
    }

    static class Scheduler {
        private PriorityQueue<Task> queue =
                new PriorityQueue<>();

        public void addTask(Task task) {
            queue.offer(task);
        }

        public void runScheduler() {
            while (!queue.isEmpty()) {
                Task task = queue.peek();

                if (task.isReady()) {
                    queue.poll();
                    task.execute();
                } else {
                    break;
                }
            }
        }

        public void showTasks() {
            System.out.println("\nPending Tasks:");
            for (Task task : queue) {
                System.out.println(task);
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        Scheduler scheduler = new Scheduler();

        scheduler.addTask(
                new Task("T1", "Email Job", 3, 2000));

        scheduler.addTask(
                new Task("T2", "Report Job", 5, 1000));

        scheduler.addTask(
                new Task("T3", "Backup Job", 2, 3000));

        scheduler.showTasks();

        Thread.sleep(1500);
        scheduler.runScheduler();

        Thread.sleep(2000);
        scheduler.runScheduler();
    }
}