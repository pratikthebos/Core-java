package com.pratik;

import java.util.*;

public class NotificationQueueManager {

    static class Notification {

        private String message;
        private String type;

        public Notification(
                String message,
                String type) {

            this.message = message;
            this.type = type;
        }

        public String getMessage() {
            return message;
        }

        public String getType() {
            return type;
        }

        @Override
        public String toString() {

            return "[" + type +
                    "] " + message;
        }
    }

    static class NotificationService {

        private Queue<Notification>
                notificationQueue;

        public NotificationService() {

            notificationQueue =
                    new LinkedList<>();
        }

        // Add notification
        public void addNotification(
                Notification notification) {

            notificationQueue.offer(
                    notification);

            System.out.println(
                    "Notification Added: "
                            + notification);
        }

        // Send notification
        public void sendNotification() {

            if (notificationQueue
                    .isEmpty()) {

                System.out.println(
                        "No pending notifications!");
                return;
            }

            Notification notification =
                    notificationQueue.poll();

            System.out.println(
                    "Sent Notification: "
                            + notification);
        }

        // Show pending notifications
        public void showPendingNotifications() {

            System.out.println(
                    "\nPending Notifications:");

            if (notificationQueue
                    .isEmpty()) {

                System.out.println(
                        "No notifications pending");
                return;
            }

            for (Notification notification
                    : notificationQueue) {

                System.out.println(
                        notification);
            }
        }
    }

    public static void main(String[] args) {

        NotificationService service =
                new NotificationService();

        service.addNotification(
                new Notification(
                        "Welcome User!",
                        "INFO"));

        service.addNotification(
                new Notification(
                        "Payment Successful",
                        "SUCCESS"));

        service.showPendingNotifications();

        service.sendNotification();

        service.showPendingNotifications();
    }
}