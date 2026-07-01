package com.pratik;

import java.util.*;

public class NotificationServiceSimulator {

    enum Channel {
        EMAIL, SMS, PUSH
    }

    static class Notification {
        private String userId;
        private String message;
        private Channel channel;
        private String status;

        public Notification(String userId, String message, Channel channel) {
            this.userId = userId;
            this.message = message;
            this.channel = channel;
            this.status = "PENDING";
        }

        public void markSent() {
            status = "SENT";
        }

        public void markFailed() {
            status = "FAILED";
        }

        @Override
        public String toString() {
            return "User=" + userId +
                    ", Channel=" + channel +
                    ", Status=" + status +
                    ", Message=" + message;
        }
    }

    static class NotificationService {
        private List<Notification> history = new ArrayList<>();
        private Random random = new Random();

        public void send(String userId, String message, Channel channel) {

            Notification notification =
                    new Notification(userId, message, channel);

            boolean success = random.nextInt(100) >= 20;

            if (success) {
                notification.markSent();
                System.out.println("Notification Sent");
            } else {
                notification.markFailed();
                System.out.println("Notification Failed");
            }

            history.add(notification);
        }

        public void showHistory() {
            System.out.println("\nNotification History:");
            for (Notification notification : history) {
                System.out.println(notification);
            }
        }
    }

    public static void main(String[] args) {

        NotificationService service =
                new NotificationService();

        service.send(
                "USER_101",
                "Your OTP is 5678",
                Channel.SMS);

        service.send(
                "USER_102",
                "Welcome to our platform",
                Channel.EMAIL);

        service.send(
                "USER_103",
                "You have a new message",
                Channel.PUSH);

        service.showHistory();
    }
}