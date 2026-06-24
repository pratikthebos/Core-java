package com.pratik;

import java.util.*;

public class ChatServerSimulator {

    static class Message {

        private String sender;
        private String receiver;
        private String text;
        private long timestamp;

        public Message(
                String sender,
                String receiver,
                String text) {

            this.sender = sender;
            this.receiver = receiver;
            this.text = text;
            this.timestamp =
                    System.currentTimeMillis();
        }

        @Override
        public String toString() {
            return "[" + timestamp + "] "
                    + sender + " -> "
                    + receiver + ": "
                    + text;
        }
    }

    static class User {

        private String username;
        private List<Message> inbox;

        public User(String username) {
            this.username = username;
            this.inbox = new ArrayList<>();
        }

        public void receiveMessage(
                Message message) {
            inbox.add(message);
        }

        public void showInbox() {
            System.out.println(
                    "\nInbox of " + username);

            for (Message message : inbox) {
                System.out.println(message);
            }
        }
    }

    static class ChatServer {

        private Map<String, User> users =
                new HashMap<>();

        public void registerUser(
                String username) {

            users.put(
                    username,
                    new User(username));

            System.out.println(
                    username + " joined chat");
        }

        public void sendMessage(
                String sender,
                String receiver,
                String text) {

            User user = users.get(receiver);

            if (user == null) {
                System.out.println(
                        "Receiver not online");
                return;
            }

            Message message =
                    new Message(
                            sender,
                            receiver,
                            text);

            user.receiveMessage(message);
        }

        public void broadcast(
                String sender,
                String text) {

            for (String username :
                    users.keySet()) {

                if (!username.equals(sender)) {
                    sendMessage(
                            sender,
                            username,
                            text);
                }
            }
        }

        public void showUserInbox(
                String username) {

            User user = users.get(username);

            if (user != null) {
                user.showInbox();
            }
        }
    }

    public static void main(String[] args) {

        ChatServer server =
                new ChatServer();

        server.registerUser("Pratik");
        server.registerUser("Amit");
        server.registerUser("Ravi");

        server.sendMessage(
                "Pratik",
                "Amit",
                "Hello Amit!");

        server.broadcast(
                "Pratik",
                "Good Morning Everyone!");

        server.showUserInbox("Amit");
        server.showUserInbox("Ravi");
    }
}