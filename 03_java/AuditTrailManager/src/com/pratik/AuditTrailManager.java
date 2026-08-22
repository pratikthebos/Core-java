package com.pratik;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class AuditTrailManager {

    static class AuditLog {

        private int logId;
        private String username;
        private String action;
        private String resource;
        private String status;
        private LocalDateTime timestamp;

        public AuditLog(
                int logId,
                String username,
                String action,
                String resource,
                String status) {

            this.logId = logId;
            this.username = username;
            this.action = action;
            this.resource = resource;
            this.status = status;
            this.timestamp = LocalDateTime.now();
        }

        public int getLogId() {
            return logId;
        }

        public String getUsername() {
            return username;
        }

        public String getAction() {
            return action;
        }

        public String getResource() {
            return resource;
        }

        public String getStatus() {
            return status;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm:ss"
                    );

            return String.format(
                    "ID: %-4d | User: %-12s | Action: %-12s | Resource: %-15s | Status: %-8s | Time: %s",
                    logId,
                    username,
                    action,
                    resource,
                    status,
                    timestamp.format(formatter)
            );
        }
    }

    private static final List<AuditLog> auditLogs =
            new ArrayList<>();

    private static int nextLogId = 1001;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleLogs();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addAuditLog(scanner);
                    break;

                case 2:
                    displayAllLogs();
                    break;

                case 3:
                    searchByUser(scanner);
                    break;

                case 4:
                    searchByAction(scanner);
                    break;

                case 5:
                    showFailedActions();
                    break;

                case 6:
                    showSuccessfulActions();
                    break;

                case 7:
                    displayStatistics();
                    break;

                case 8:
                    deleteLog(scanner);
                    break;

                case 9:
                    System.out.println(
                            "\nAudit Trail Manager closed."
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
                "\n=========================================================="
        );

        System.out.println(
                "                 AUDIT TRAIL MANAGER"
        );

        System.out.println(
                "=========================================================="
        );

        System.out.println("1. Add Audit Log");
        System.out.println("2. Display All Logs");
        System.out.println("3. Search Logs By User");
        System.out.println("4. Search Logs By Action");
        System.out.println("5. Show Failed Actions");
        System.out.println("6. Show Successful Actions");
        System.out.println("7. Display Statistics");
        System.out.println("8. Delete Log");
        System.out.println("9. Exit");

        System.out.println(
                "=========================================================="
        );
    }

    private static void loadSampleLogs() {

        addSampleLog(
                "admin",
                "LOGIN",
                "Authentication",
                "SUCCESS"
        );

        addSampleLog(
                "pratik",
                "CREATE",
                "Employee",
                "SUCCESS"
        );

        addSampleLog(
                "developer",
                "UPDATE",
                "Configuration",
                "SUCCESS"
        );

        addSampleLog(
                "user01",
                "DELETE",
                "Transaction",
                "FAILED"
        );

        addSampleLog(
                "admin",
                "EXPORT",
                "Reports",
                "SUCCESS"
        );
    }

    private static void addSampleLog(
            String username,
            String action,
            String resource,
            String status) {

        auditLogs.add(
                new AuditLog(
                        nextLogId++,
                        username,
                        action,
                        resource,
                        status
                )
        );
    }

    private static void addAuditLog(Scanner scanner) {

        System.out.println("\n--- Add Audit Log ---");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        System.out.print("Enter Action: ");
        String action = scanner.nextLine().trim().toUpperCase();

        System.out.print("Enter Resource: ");
        String resource = scanner.nextLine().trim();

        System.out.print("Enter Status (SUCCESS/FAILED): ");
        String status = scanner.nextLine()
                .trim()
                .toUpperCase();

        if (username.isEmpty()
                || action.isEmpty()
                || resource.isEmpty()) {

            System.out.println(
                    "All fields are required."
            );

            return;
        }

        if (!status.equals("SUCCESS")
                && !status.equals("FAILED")) {

            System.out.println(
                    "Status must be SUCCESS or FAILED."
            );

            return;
        }

        AuditLog log = new AuditLog(
                nextLogId++,
                username,
                action,
                resource,
                status
        );

        auditLogs.add(log);

        System.out.println(
                "\nAudit log created successfully."
        );

        System.out.println(log);
    }

    private static void displayAllLogs() {

        System.out.println("\n--- All Audit Logs ---");

        if (auditLogs.isEmpty()) {

            System.out.println(
                    "No audit logs available."
            );

            return;
        }

        for (AuditLog log : auditLogs) {
            System.out.println(log);
        }

        System.out.println(
                "\nTotal Logs: " + auditLogs.size()
        );
    }

    private static void searchByUser(Scanner scanner) {

        System.out.println("\n--- Search By User ---");

        System.out.print("Enter Username: ");
        String username = scanner.nextLine().trim();

        boolean found = false;

        for (AuditLog log : auditLogs) {

            if (log.getUsername()
                    .equalsIgnoreCase(username)) {

                System.out.println(log);
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No logs found for user: "
                            + username
            );
        }
    }

    private static void searchByAction(Scanner scanner) {

        System.out.println("\n--- Search By Action ---");

        System.out.print("Enter Action: ");
        String action = scanner.nextLine()
                .trim()
                .toUpperCase();

        boolean found = false;

        for (AuditLog log : auditLogs) {

            if (log.getAction()
                    .equalsIgnoreCase(action)) {

                System.out.println(log);
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No logs found for action: "
                            + action
            );
        }
    }

    private static void showFailedActions() {

        System.out.println(
                "\n--- Failed Actions ---"
        );

        boolean found = false;

        for (AuditLog log : auditLogs) {

            if (log.getStatus()
                    .equals("FAILED")) {

                System.out.println(log);
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No failed actions found."
            );
        }
    }

    private static void showSuccessfulActions() {

        System.out.println(
                "\n--- Successful Actions ---"
        );

        boolean found = false;

        for (AuditLog log : auditLogs) {

            if (log.getStatus()
                    .equals("SUCCESS")) {

                System.out.println(log);
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No successful actions found."
            );
        }
    }

    private static void displayStatistics() {

        int successful = 0;
        int failed = 0;

        for (AuditLog log : auditLogs) {

            if (log.getStatus()
                    .equals("SUCCESS")) {

                successful++;

            } else {

                failed++;
            }
        }

        System.out.println(
                "\n================================================"
        );

        System.out.println(
                "             AUDIT LOG STATISTICS"
        );

        System.out.println(
                "================================================"
        );

        System.out.println(
                "Total Logs       : " + auditLogs.size()
        );

        System.out.println(
                "Successful       : " + successful
        );

        System.out.println(
                "Failed           : " + failed
        );

        System.out.println(
                "Success Rate     : "
                        + calculateSuccessRate(successful)
                        + "%"
        );

        System.out.println(
                "================================================"
        );
    }

    private static double calculateSuccessRate(
            int successful) {

        if (auditLogs.isEmpty()) {
            return 0;
        }

        return (successful * 100.0)
                / auditLogs.size();
    }

    private static void deleteLog(Scanner scanner) {

        System.out.println("\n--- Delete Audit Log ---");

        System.out.print("Enter Log ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        AuditLog target = null;

        for (AuditLog log : auditLogs) {

            if (log.getLogId() == id) {

                target = log;
                break;
            }
        }

        if (target == null) {

            System.out.println(
                    "Audit log not found."
            );

            return;
        }

        auditLogs.remove(target);

        System.out.println(
                "Audit log " + id
                        + " deleted successfully."
        );
    }
}