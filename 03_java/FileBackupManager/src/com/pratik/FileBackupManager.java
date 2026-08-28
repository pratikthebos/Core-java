package com.pratik;

import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class FileBackupManager {

    enum BackupStatus {
        COMPLETED,
        FAILED,
        RESTORED
    }

    static class BackupRecord {

        private int id;
        private String fileName;
        private long fileSize;
        private BackupStatus status;
        private LocalDateTime createdAt;

        public BackupRecord(
                int id,
                String fileName,
                long fileSize,
                BackupStatus status) {

            this.id = id;
            this.fileName = fileName;
            this.fileSize = fileSize;
            this.status = status;
            this.createdAt = LocalDateTime.now();
        }

        public int getId() {
            return id;
        }

        public String getFileName() {
            return fileName;
        }

        public long getFileSize() {
            return fileSize;
        }

        public BackupStatus getStatus() {
            return status;
        }

        public void setStatus(BackupStatus status) {
            this.status = status;
        }

        public LocalDateTime getCreatedAt() {
            return createdAt;
        }

        @Override
        public String toString() {

            DateTimeFormatter formatter =
                    DateTimeFormatter.ofPattern(
                            "yyyy-MM-dd HH:mm:ss"
                    );

            return String.format(
                    "ID: %-5d | File: %-20s | Size: %-8d KB | Status: %-10s | Time: %s",
                    id,
                    fileName,
                    fileSize,
                    status,
                    createdAt.format(formatter)
            );
        }
    }

    private static final List<BackupRecord> backups =
            new ArrayList<>();

    private static int nextBackupId = 1001;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleBackups();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    createBackup(scanner);
                    break;

                case 2:
                    displayBackups();
                    break;

                case 3:
                    restoreBackup(scanner);
                    break;

                case 4:
                    searchBackup(scanner);
                    break;

                case 5:
                    deleteBackup(scanner);
                    break;

                case 6:
                    displayStatistics();
                    break;

                case 7:
                    System.out.println(
                            "\nFile Backup Manager closed."
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
                "              FILE BACKUP MANAGER"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Create Backup");
        System.out.println("2. Display Backups");
        System.out.println("3. Restore Backup");
        System.out.println("4. Search Backup");
        System.out.println("5. Delete Backup");
        System.out.println("6. Display Statistics");
        System.out.println("7. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void loadSampleBackups() {

        backups.add(
                new BackupRecord(
                        nextBackupId++,
                        "database.sql",
                        2048,
                        BackupStatus.COMPLETED
                )
        );

        backups.add(
                new BackupRecord(
                        nextBackupId++,
                        "application.zip",
                        5120,
                        BackupStatus.COMPLETED
                )
        );

        backups.add(
                new BackupRecord(
                        nextBackupId++,
                        "config.json",
                        128,
                        BackupStatus.FAILED
                )
        );
    }

    private static void createBackup(Scanner scanner) {

        System.out.println("\n--- Create Backup ---");

        System.out.print("Enter File Name: ");
        String fileName = scanner.nextLine().trim();

        System.out.print("Enter File Size (KB): ");
        long fileSize = scanner.nextLong();
        scanner.nextLine();

        if (fileName.isEmpty()) {

            System.out.println(
                    "File name cannot be empty."
            );

            return;
        }

        if (fileSize <= 0) {

            System.out.println(
                    "File size must be greater than zero."
            );

            return;
        }

        BackupRecord backup =
                new BackupRecord(
                        nextBackupId++,
                        fileName,
                        fileSize,
                        BackupStatus.COMPLETED
                );

        backups.add(backup);

        System.out.println(
                "\nBackup created successfully."
        );

        System.out.println(backup);
    }

    private static void displayBackups() {

        System.out.println(
                "\n--- Backup Records ---"
        );

        if (backups.isEmpty()) {

            System.out.println(
                    "No backup records found."
            );

            return;
        }

        for (BackupRecord backup : backups) {
            System.out.println(backup);
        }

        System.out.println(
                "\nTotal Backups: " + backups.size()
        );
    }

    private static void restoreBackup(
            Scanner scanner) {

        System.out.println(
                "\n--- Restore Backup ---"
        );

        System.out.print("Enter Backup ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        BackupRecord backup = findBackup(id);

        if (backup == null) {

            System.out.println(
                    "Backup not found."
            );

            return;
        }

        if (backup.getStatus()
                == BackupStatus.FAILED) {

            System.out.println(
                    "Failed backups cannot be restored."
            );

            return;
        }

        backup.setStatus(
                BackupStatus.RESTORED
        );

        System.out.println(
                "\nBackup restored successfully."
        );

        System.out.println(backup);
    }

    private static void searchBackup(
            Scanner scanner) {

        System.out.println(
                "\n--- Search Backup ---"
        );

        System.out.print("Enter file name: ");
        String keyword =
                scanner.nextLine()
                        .trim()
                        .toLowerCase();

        boolean found = false;

        for (BackupRecord backup : backups) {

            if (backup.getFileName()
                    .toLowerCase()
                    .contains(keyword)) {

                System.out.println(backup);
                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No matching backup found."
            );
        }
    }

    private static void deleteBackup(
            Scanner scanner) {

        System.out.println(
                "\n--- Delete Backup ---"
        );

        System.out.print("Enter Backup ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        BackupRecord backup = findBackup(id);

        if (backup == null) {

            System.out.println(
                    "Backup not found."
            );

            return;
        }

        backups.remove(backup);

        System.out.println(
                "Backup " + id
                        + " deleted successfully."
        );
    }

    private static BackupRecord findBackup(int id) {

        for (BackupRecord backup : backups) {

            if (backup.getId() == id) {
                return backup;
            }
        }

        return null;
    }

    private static void displayStatistics() {

        int completed = 0;
        int failed = 0;
        int restored = 0;
        long totalSize = 0;

        for (BackupRecord backup : backups) {

            totalSize += backup.getFileSize();

            switch (backup.getStatus()) {

                case COMPLETED:
                    completed++;
                    break;

                case FAILED:
                    failed++;
                    break;

                case RESTORED:
                    restored++;
                    break;
            }
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "              BACKUP STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Backups : " + backups.size()
        );

        System.out.println(
                "Completed     : " + completed
        );

        System.out.println(
                "Failed        : " + failed
        );

        System.out.println(
                "Restored      : " + restored
        );

        System.out.println(
                "Total Size    : " + totalSize + " KB"
        );

        System.out.println(
                "=============================================="
        );
    }
}