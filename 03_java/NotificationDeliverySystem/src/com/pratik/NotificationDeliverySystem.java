package com.pratik;

import java.util.*;

public class NotificationDeliverySystem {

    static class Notification {

        private String type;
        private String message;
        private String status;

        public Notification(
                String type,
                String message) {

            this.type = type;
            this.message = message;
            this.status = "PENDING";
        }

        public void markDelivered() {
            status = "DELIVERED";
        }

        @Override
        public String toString() {
            return "Type: " + type +
                    ", Message: " + message +
                    ", Status: " + status;
        }
    }

    static class User {

        private String userId;
        private List<Notification> history;

        public User(String userId) {
            this.userId = userId;
            this.history = new ArrayList<>();
        }

        public void receiveNotification(
                Notification notification) {

            notification.markDelivered();
            history.add(notification);
        }

        public void showHistory() {

            System.out.println(
                    "\nNotification History of "
                            + userId);

            for (Notification notification
                    : history) {
                System.out.println(notification);
            }
        }
    }

    static class NotificationService {

        private Map<String, User> users =
                new HashMap<>();

        public void registerUser(
                String userId) {

            users.put(
                    userId,
                    new User(userId));

            System.out.println(
                    "Registered User: "
                            + userId);
        }

        public void sendNotification(
                String userId,
                String type,
                String message) {

            User user = users.get(userId);

            if (user == null) {
                System.out.println(
                        "User not found");
                return;
            }

            Notification notification =
                    new Notification(
                            type,
                            message);

            user.receiveNotification(
                    notification);

            System.out.println(
                    "Notification sent to "
                            + userId);
        }

        public void showUserHistory(
                String userId) {

            User user = users.get(userId);

            if (user != null) {
                user.showHistory();
            }
        }
    }

    public static void main(String[] args) {

        NotificationService service =
                new NotificationService();

        service.registerUser("U101");
        service.registerUser("U102");

        service.sendNotification(
                "U101",
                "EMAIL",
                "Welcome to platform");

        service.sendNotification(
                "U101",
                "PUSH",
                "Payment Successful");

        service.showUserHistory("U101");
    }
}