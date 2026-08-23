package com.pratik;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.PriorityQueue;
import java.util.Scanner;

public class NotificationScheduler {

    static class Notification implements Comparable<Notification> {

        private int id;
        private String recipient;
        private String message;
        private LocalDateTime scheduledTime;
        private String status;

        public Notification(
                int id,
                String recipient,
                String message,
                LocalDateTime scheduledTime) {

            this.id = id;
            this.recipient = recipient;
            this.message = message;
            this.scheduledTime = scheduledTime;
            this.status = "SCHEDULED";
        }

        public int getId() {
            return id;
        }

        public String getRecipient() {
            return recipient;
        }

        public String getMessage() {
            return message;
        }

        public LocalDateTime getScheduledTime() {
            return scheduledTime;
        }

        public String getStatus() {
            return status;
        }

        public void setStatus(String status) {
            this.status = status;
        }

        @Override
        public int compareTo(Notification other) {
            return this.scheduledTime.compareTo(
                    other.scheduledTime
            );
        }

        @Override
        public String toString() {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm"
                    );

            return String.format(
                    "ID: %-4d | To: %-15s | Time: %-17s | Status: %-10s | Message: %s",
                    id,
                    recipient,
                    scheduledTime.format(formatter),
                    status,
                    message
            );
        }
    }

    private static final PriorityQueue<Notification> notifications =
            new PriorityQueue<>();

    private static int nextId = 1001;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleNotifications();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    scheduleNotification(scanner);
                    break;

                case 2:
                    displayNotifications();
                    break;

                case 3:
                    processNextNotification();
                    break;

                case 4:
                    cancelNotification(scanner);
                    break;

                case 5:
                    showNextNotification();
                    break;

                case 6:
                    displayStatistics();
                    break;

                case 7:
                    System.out.println(
                            "\nNotification Scheduler closed."
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
                "\n================================================"
        );

        System.out.println(
                "             NOTIFICATION SCHEDULER"
        );

        System.out.println(
                "================================================"
        );

        System.out.println("1. Schedule Notification");
        System.out.println("2. Display Notifications");
        System.out.println("3. Process Next Notification");
        System.out.println("4. Cancel Notification");
        System.out.println("5. Show Next Notification");
        System.out.println("6. Display Statistics");
        System.out.println("7. Exit");

        System.out.println(
                "================================================"
        );
    }

    private static void loadSampleNotifications() {

        notifications.add(
                new Notification(
                        nextId++,
                        "Pratik",
                        "Daily stand-up meeting",
                        LocalDateTime.now().plusMinutes(5)
                )
        );

        notifications.add(
                new Notification(
                        nextId++,
                        "Developer",
                        "Code review reminder",
                        LocalDateTime.now().plusMinutes(10)
                )
        );

        notifications.add(
                new Notification(
                        nextId++,
                        "Manager",
                        "Project status update",
                        LocalDateTime.now().plusMinutes(15)
                )
        );
    }

    private static void scheduleNotification(
            Scanner scanner) {

        System.out.println(
                "\n--- Schedule Notification ---"
        );

        System.out.print("Enter Recipient: ");
        String recipient = scanner.nextLine().trim();

        System.out.print("Enter Message: ");
        String message = scanner.nextLine().trim();

        System.out.print(
                "Enter delay in minutes from now: "
        );

        long minutes = scanner.nextLong();
        scanner.nextLine();

        if (recipient.isEmpty()
                || message.isEmpty()) {

            System.out.println(
                    "Recipient and message are required."
            );

            return;
        }

        if (minutes < 0) {

            System.out.println(
                    "Delay cannot be negative."
            );

            return;
        }

        LocalDateTime scheduledTime =
                LocalDateTime.now()
                        .plusMinutes(minutes);

        Notification notification =
                new Notification(
                        nextId++,
                        recipient,
                        message,
                        scheduledTime
                );

        notifications.offer(notification);

        System.out.println(
                "\nNotification scheduled successfully."
        );

        System.out.println(notification);
    }

    private static void displayNotifications() {

        System.out.println(
                "\n--- Scheduled Notifications ---"
        );

        if (notifications.isEmpty()) {

            System.out.println(
                    "No notifications available."
            );

            return;
        }

        PriorityQueue<Notification> copy =
                new PriorityQueue<>(notifications);

        while (!copy.isEmpty()) {

            System.out.println(copy.poll());
        }

        System.out.println(
                "\nTotal Notifications: "
                        + notifications.size()
        );
    }

    private static void processNextNotification() {

        if (notifications.isEmpty()) {

            System.out.println(
                    "\nNo notifications to process."
            );

            return;
        }

        Notification notification =
                notifications.poll();

        notification.setStatus("SENT");

        System.out.println(
                "\n--- Notification Processed ---"
        );

        System.out.println(
                "Sending notification..."
        );

        System.out.println(
                "Recipient : "
                        + notification.getRecipient()
        );

        System.out.println(
                "Message   : "
                        + notification.getMessage()
        );

        System.out.println(
                "Status    : "
                        + notification.getStatus()
        );
    }

    private static void showNextNotification() {

        if (notifications.isEmpty()) {

            System.out.println(
                    "\nNo pending notifications."
            );

            return;
        }

        Notification next =
                notifications.peek();

        System.out.println(
                "\n--- Next Notification ---"
        );

        System.out.println(next);
    }

    private static void cancelNotification(
            Scanner scanner) {

        System.out.println(
                "\n--- Cancel Notification ---"
        );

        System.out.print("Enter Notification ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        Notification target = null;

        for (Notification notification : notifications) {

            if (notification.getId() == id) {

                target = notification;
                break;
            }
        }

        if (target == null) {

            System.out.println(
                    "Notification not found."
            );

            return;
        }

        notifications.remove(target);

        target.setStatus("CANCELLED");

        System.out.println(
                "Notification "
                        + id
                        + " cancelled successfully."
        );
    }

    private static void displayStatistics() {

        int scheduled = 0;
        int cancelled = 0;
        int sent = 0;

        for (Notification notification : notifications) {

            if (notification.getStatus()
                    .equals("SCHEDULED")) {

                scheduled++;

            } else if (notification.getStatus()
                    .equals("CANCELLED")) {

                cancelled++;

            } else if (notification.getStatus()
                    .equals("SENT")) {

                sent++;
            }
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "          NOTIFICATION STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Pending Notifications : " + scheduled
        );

        System.out.println(
                "Cancelled             : " + cancelled
        );

        System.out.println(
                "Sent                  : " + sent
        );

        System.out.println(
                "Total Pending         : "
                        + notifications.size()
        );

        System.out.println(
                "=============================================="
        );
    }
}