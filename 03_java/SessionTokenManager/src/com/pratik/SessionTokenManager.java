package com.pratik;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;
import java.util.UUID;

public class SessionTokenManager {

    static class Session {

        private String username;
        private String token;
        private long createdAt;
        private long expiresAt;

        public Session(String username, int expirySeconds) {

            this.username = username;
            this.token = UUID.randomUUID().toString();

            this.createdAt = System.currentTimeMillis();

            this.expiresAt =
                    createdAt + (expirySeconds * 1000L);
        }

        public String getUsername() {
            return username;
        }

        public String getToken() {
            return token;
        }

        public long getCreatedAt() {
            return createdAt;
        }

        public long getExpiresAt() {
            return expiresAt;
        }

        public boolean isExpired() {

            return System.currentTimeMillis() >= expiresAt;
        }

        public long getRemainingSeconds() {

            long remaining =
                    expiresAt - System.currentTimeMillis();

            if (remaining <= 0) {
                return 0;
            }

            return remaining / 1000;
        }
    }

    private static final Map<String, Session> sessions =
            new HashMap<>();

    private static final int SESSION_EXPIRY_SECONDS = 300;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    createSession(scanner);
                    break;

                case 2:
                    validateToken(scanner);
                    break;

                case 3:
                    logout(scanner);
                    break;

                case 4:
                    displayActiveSessions();
                    break;

                case 5:
                    cleanupExpiredSessions();
                    break;

                case 6:
                    searchUserSession(scanner);
                    break;

                case 7:
                    displayStatistics();
                    break;

                case 8:
                    System.out.println(
                            "\nSession Token Manager stopped."
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
                "           SESSION TOKEN MANAGER"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Create Session");
        System.out.println("2. Validate Token");
        System.out.println("3. Logout");
        System.out.println("4. Display Active Sessions");
        System.out.println("5. Remove Expired Sessions");
        System.out.println("6. Search User Session");
        System.out.println("7. Display Statistics");
        System.out.println("8. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void createSession(Scanner scanner) {

        System.out.println("\n--- Create Session ---");

        System.out.print("Enter username: ");
        String username = scanner.nextLine().trim();

        if (username.isEmpty()) {

            System.out.println(
                    "Username cannot be empty."
            );

            return;
        }

        removeExpiredSession(username);

        Session existingSession =
                findSessionByUsername(username);

        if (existingSession != null) {

            System.out.println(
                    "\nUser already has an active session."
            );

            System.out.println(
                    "Existing Token: "
                            + existingSession.getToken()
            );

            return;
        }

        Session session =
                new Session(
                        username,
                        SESSION_EXPIRY_SECONDS
                );

        sessions.put(session.getToken(), session);

        System.out.println(
                "\nSESSION CREATED SUCCESSFULLY"
        );

        System.out.println(
                "Username : " + username
        );

        System.out.println(
                "Token    : " + session.getToken()
        );

        System.out.println(
                "Expires  : "
                        + SESSION_EXPIRY_SECONDS
                        + " seconds"
        );
    }

    private static void validateToken(Scanner scanner) {

        System.out.println("\n--- Validate Token ---");

        System.out.print("Enter token: ");
        String token = scanner.nextLine().trim();

        Session session = sessions.get(token);

        if (session == null) {

            System.out.println(
                    "\nINVALID TOKEN"
            );

            return;
        }

        if (session.isExpired()) {

            sessions.remove(token);

            System.out.println(
                    "\nTOKEN EXPIRED"
            );

            return;
        }

        System.out.println(
                "\nTOKEN VALID"
        );

        System.out.println(
                "Username : "
                        + session.getUsername()
        );

        System.out.println(
                "Remaining: "
                        + session.getRemainingSeconds()
                        + " seconds"
        );
    }

    private static void logout(Scanner scanner) {

        System.out.println("\n--- Logout ---");

        System.out.print("Enter token: ");
        String token = scanner.nextLine().trim();

        Session session = sessions.get(token);

        if (session == null) {

            System.out.println(
                    "Session not found."
            );

            return;
        }

        sessions.remove(token);

        System.out.println(
                "\nLOGOUT SUCCESSFUL"
        );

        System.out.println(
                "User: " + session.getUsername()
        );
    }

    private static void displayActiveSessions() {

        cleanupExpiredSessions();

        System.out.println(
                "\n--- Active Sessions ---"
        );

        if (sessions.isEmpty()) {

            System.out.println(
                    "No active sessions."
            );

            return;
        }

        System.out.println(
                String.format(
                        "%-18s %-38s %-15s",
                        "USERNAME",
                        "TOKEN",
                        "REMAINING"
                )
        );

        System.out.println(
                "----------------------------------------------------------------"
        );

        for (Session session : sessions.values()) {

            System.out.println(
                    String.format(
                            "%-18s %-38s %-15s",
                            session.getUsername(),
                            session.getToken(),
                            session.getRemainingSeconds()
                                    + " sec"
                    )
            );
        }
    }

    private static void cleanupExpiredSessions() {

        int removed = 0;

        for (String token : sessions.keySet()
                .toArray(new String[0])) {

            Session session = sessions.get(token);

            if (session.isExpired()) {

                sessions.remove(token);
                removed++;
            }
        }

        if (removed > 0) {

            System.out.println(
                    removed
                            + " expired session(s) removed."
            );
        }
    }

    private static void searchUserSession(
            Scanner scanner) {

        System.out.println(
                "\n--- Search User Session ---"
        );

        System.out.print("Enter username: ");
        String username =
                scanner.nextLine().trim();

        removeExpiredSession(username);

        Session session =
                findSessionByUsername(username);

        if (session == null) {

            System.out.println(
                    "No active session found."
            );

            return;
        }

        System.out.println(
                "\nSession Found"
        );

        System.out.println(
                "Username : " + session.getUsername()
        );

        System.out.println(
                "Token    : " + session.getToken()
        );

        System.out.println(
                "Remaining: "
                        + session.getRemainingSeconds()
                        + " seconds"
        );
    }

    private static void displayStatistics() {

        cleanupExpiredSessions();

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "            SESSION STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Active Sessions : "
                        + sessions.size()
        );

        System.out.println(
                "Session Timeout : "
                        + SESSION_EXPIRY_SECONDS
                        + " seconds"
        );

        System.out.println(
                "System Status   : "
                        + (sessions.isEmpty()
                        ? "IDLE"
                        : "ACTIVE")
        );

        System.out.println(
                "=============================================="
        );
    }

    private static Session findSessionByUsername(
            String username) {

        for (Session session : sessions.values()) {

            if (session.getUsername()
                    .equalsIgnoreCase(username)) {

                return session;
            }
        }

        return null;
    }

    private static void removeExpiredSession(
            String username) {

        Session session =
                findSessionByUsername(username);

        if (session != null && session.isExpired()) {

            sessions.remove(session.getToken());
        }
    }
}