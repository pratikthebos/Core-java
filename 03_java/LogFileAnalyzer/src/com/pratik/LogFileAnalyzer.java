package com.pratik;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class LogFileAnalyzer {

    static class LogEntry {

        private final String timestamp;
        private final String level;
        private final String message;

        public LogEntry(
                String timestamp,
                String level,
                String message) {

            this.timestamp = timestamp;
            this.level = level;
            this.message = message;
        }

        public String getTimestamp() {
            return timestamp;
        }

        public String getLevel() {
            return level;
        }

        public String getMessage() {
            return message;
        }

        @Override
        public String toString() {
            return String.format(
                    "%-20s | %-7s | %s",
                    timestamp,
                    level,
                    message
            );
        }
    }

    private static final List<LogEntry> logs =
            new ArrayList<>();

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
                    displayLogs();
                    break;

                case 2:
                    addLog(scanner);
                    break;

                case 3:
                    displayStatistics();
                    break;

                case 4:
                    searchLogs(scanner);
                    break;

                case 5:
                    findMostFrequentError();
                    break;

                case 6:
                    removeErrors();
                    break;

                case 7:
                    System.out.println(
                            "\nLog File Analyzer closed."
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
                "              LOG FILE ANALYZER"
        );
        System.out.println(
                "=============================================="
        );
        System.out.println("1. Display Logs");
        System.out.println("2. Add Log");
        System.out.println("3. Display Statistics");
        System.out.println("4. Search Logs");
        System.out.println("5. Most Frequent Error");
        System.out.println("6. Remove Error Logs");
        System.out.println("7. Exit");
        System.out.println(
                "=============================================="
        );
    }

    private static void loadSampleLogs() {

        logs.add(new LogEntry(
                "2026-08-27 09:10:15",
                "INFO",
                "Application started"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:12:20",
                "INFO",
                "Database connection established"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:15:41",
                "WARNING",
                "High memory usage detected"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:17:10",
                "ERROR",
                "Database connection failed"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:18:35",
                "ERROR",
                "Database connection failed"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:20:02",
                "INFO",
                "User authentication successful"
        ));

        logs.add(new LogEntry(
                "2026-08-27 09:22:18",
                "ERROR",
                "Invalid API response"
        ));
    }

    private static void displayLogs() {

        System.out.println("\n--- Application Logs ---");

        if (logs.isEmpty()) {
            System.out.println("No logs available.");
            return;
        }

        for (LogEntry log : logs) {
            System.out.println(log);
        }

        System.out.println(
                "\nTotal Logs: " + logs.size()
        );
    }

    private static void addLog(Scanner scanner) {

        System.out.println("\n--- Add Log ---");

        System.out.print("Enter timestamp: ");
        String timestamp = scanner.nextLine().trim();

        System.out.print(
                "Enter level (INFO/WARNING/ERROR): "
        );

        String level = scanner.nextLine()
                .trim()
                .toUpperCase();

        System.out.print("Enter message: ");
        String message = scanner.nextLine().trim();

        if (timestamp.isEmpty()
                || message.isEmpty()) {

            System.out.println(
                    "Timestamp and message are required."
            );

            return;
        }

        if (!level.equals("INFO")
                && !level.equals("WARNING")
                && !level.equals("ERROR")) {

            System.out.println(
                    "Invalid log level."
            );

            return;
        }

        logs.add(
                new LogEntry(
                        timestamp,
                        level,
                        message
                )
        );

        System.out.println(
                "Log added successfully."
        );
    }

    private static void displayStatistics() {

        int infoCount = 0;
        int warningCount = 0;
        int errorCount = 0;

        for (LogEntry log : logs) {

            switch (log.getLevel()) {

                case "INFO":
                    infoCount++;
                    break;

                case "WARNING":
                    warningCount++;
                    break;

                case "ERROR":
                    errorCount++;
                    break;

                default:
                    break;
            }
        }

        System.out.println(
                "\n=============================================="
        );
        System.out.println(
                "               LOG STATISTICS"
        );
        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Logs : " + logs.size()
        );

        System.out.println(
                "INFO       : " + infoCount
        );

        System.out.println(
                "WARNING    : " + warningCount
        );

        System.out.println(
                "ERROR      : " + errorCount
        );

        System.out.println(
                "=============================================="
        );
    }

    private static void searchLogs(Scanner scanner) {

        System.out.println("\n--- Search Logs ---");

        System.out.print("Enter keyword: ");
        String keyword = scanner.nextLine()
                .trim()
                .toLowerCase();

        if (keyword.isEmpty()) {
            System.out.println(
                    "Keyword cannot be empty."
            );
            return;
        }

        boolean found = false;

        for (LogEntry log : logs) {

            if (log.getMessage()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println(log);
                found = true;
            }
        }

        if (!found) {
            System.out.println(
                    "No matching logs found."
            );
        }
    }

    private static void findMostFrequentError() {

        Map<String, Integer> errorFrequency =
                new HashMap<>();

        for (LogEntry log : logs) {

            if (!log.getLevel().equals("ERROR")) {
                continue;
            }

            errorFrequency.put(
                    log.getMessage(),
                    errorFrequency.getOrDefault(
                            log.getMessage(),
                            0
                    ) + 1
            );
        }

        if (errorFrequency.isEmpty()) {

            System.out.println(
                    "\nNo error logs found."
            );

            return;
        }

        String mostFrequentError = null;
        int highestCount = 0;

        for (Map.Entry<String, Integer> entry
                : errorFrequency.entrySet()) {

            if (entry.getValue() > highestCount) {

                highestCount = entry.getValue();
                mostFrequentError = entry.getKey();
            }
        }

        System.out.println(
                "\n--- Most Frequent Error ---"
        );

        System.out.println(
                "Error   : " + mostFrequentError
        );

        System.out.println(
                "Count   : " + highestCount
        );
    }

    private static void removeErrors() {

        int removedCount = 0;

        for (int i = logs.size() - 1; i >= 0; i--) {

            if (logs.get(i)
                    .getLevel()
                    .equals("ERROR")) {

                logs.remove(i);
                removedCount++;
            }
        }

        System.out.println(
                "\nRemoved " + removedCount
                        + " error log(s)."
        );
    }
}