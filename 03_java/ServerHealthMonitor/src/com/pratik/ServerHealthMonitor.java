package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ServerHealthMonitor {

    static class Server {

        private int serverId;
        private String serverName;
        private double cpuUsage;
        private double memoryUsage;
        private long responseTime;
        private boolean online;

        public Server(int serverId, String serverName,
                      double cpuUsage, double memoryUsage,
                      long responseTime, boolean online) {

            this.serverId = serverId;
            this.serverName = serverName;
            this.cpuUsage = cpuUsage;
            this.memoryUsage = memoryUsage;
            this.responseTime = responseTime;
            this.online = online;
        }

        public int getServerId() {
            return serverId;
        }

        public String getServerName() {
            return serverName;
        }

        public double getCpuUsage() {
            return cpuUsage;
        }

        public double getMemoryUsage() {
            return memoryUsage;
        }

        public long getResponseTime() {
            return responseTime;
        }

        public boolean isOnline() {
            return online;
        }

        public String getHealthStatus() {

            if (!online) {
                return "OFFLINE";
            }

            if (cpuUsage > 90 || memoryUsage > 90
                    || responseTime > 1000) {
                return "CRITICAL";
            }

            if (cpuUsage > 70 || memoryUsage > 70
                    || responseTime > 500) {
                return "WARNING";
            }

            return "HEALTHY";
        }

        @Override
        public String toString() {

            return String.format(
                    "ID: %-4d | Server: %-15s | CPU: %5.1f%% | Memory: %5.1f%% | Response: %4d ms | Status: %s",
                    serverId,
                    serverName,
                    cpuUsage,
                    memoryUsage,
                    responseTime,
                    getHealthStatus()
            );
        }
    }

    private static final List<Server> servers = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleServers();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addServer(scanner);
                    break;

                case 2:
                    displayServers();
                    break;

                case 3:
                    showCriticalServers();
                    break;

                case 4:
                    findFastestServer();
                    break;

                case 5:
                    calculateAverageCpu();
                    break;

                case 6:
                    searchServer(scanner);
                    break;

                case 7:
                    generateHealthReport();
                    break;

                case 8:
                    System.out.println("\nServer Health Monitor closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void displayMenu() {

        System.out.println("\n=================================================");
        System.out.println("             SERVER HEALTH MONITOR");
        System.out.println("=================================================");
        System.out.println("1. Add Server");
        System.out.println("2. Display All Servers");
        System.out.println("3. Show Critical Servers");
        System.out.println("4. Find Fastest Server");
        System.out.println("5. Calculate Average CPU Usage");
        System.out.println("6. Search Server");
        System.out.println("7. Generate Health Report");
        System.out.println("8. Exit");
        System.out.println("=================================================");
    }

    private static void loadSampleServers() {

        servers.add(
                new Server(
                        101,
                        "API-Server-01",
                        45.5,
                        52.3,
                        120,
                        true
                )
        );

        servers.add(
                new Server(
                        102,
                        "DB-Server-01",
                        78.2,
                        81.5,
                        620,
                        true
                )
        );

        servers.add(
                new Server(
                        103,
                        "Auth-Server-01",
                        35.8,
                        44.2,
                        95,
                        true
                )
        );

        servers.add(
                new Server(
                        104,
                        "Cache-Server-01",
                        94.5,
                        92.1,
                        1250,
                        true
                )
        );

        servers.add(
                new Server(
                        105,
                        "Backup-Server-01",
                        10.2,
                        20.5,
                        0,
                        false
                )
        );
    }

    private static void addServer(Scanner scanner) {

        System.out.println("\n--- Add Server ---");

        System.out.print("Enter Server ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (findServerById(id) != null) {
            System.out.println("Server ID already exists.");
            return;
        }

        System.out.print("Enter Server Name: ");
        String name = scanner.nextLine();

        System.out.print("Enter CPU Usage (%): ");
        double cpu = scanner.nextDouble();

        System.out.print("Enter Memory Usage (%): ");
        double memory = scanner.nextDouble();

        System.out.print("Enter Response Time (ms): ");
        long responseTime = scanner.nextLong();

        System.out.print("Is Server Online? (true/false): ");
        boolean online = scanner.nextBoolean();

        if (cpu < 0 || cpu > 100 ||
                memory < 0 || memory > 100 ||
                responseTime < 0) {

            System.out.println("Invalid server metrics.");
            return;
        }

        servers.add(
                new Server(
                        id,
                        name,
                        cpu,
                        memory,
                        responseTime,
                        online
                )
        );

        System.out.println("Server added successfully.");
    }

    private static void displayServers() {

        System.out.println("\n--- Server List ---");

        if (servers.isEmpty()) {
            System.out.println("No servers available.");
            return;
        }

        for (Server server : servers) {
            System.out.println(server);
        }

        System.out.println("-----------------------------------------------");
        System.out.println("Total Servers: " + servers.size());
    }

    private static void showCriticalServers() {

        System.out.println("\n--- Critical Servers ---");

        boolean found = false;

        for (Server server : servers) {

            if (server.getHealthStatus().equals("CRITICAL")) {

                System.out.println(server);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No critical servers found.");
        }
    }

    private static void findFastestServer() {

        if (servers.isEmpty()) {
            System.out.println("No servers available.");
            return;
        }

        Server fastest = null;

        for (Server server : servers) {

            if (!server.isOnline()) {
                continue;
            }

            if (fastest == null ||
                    server.getResponseTime()
                            < fastest.getResponseTime()) {

                fastest = server;
            }
        }

        if (fastest == null) {
            System.out.println("No online servers available.");
            return;
        }

        System.out.println("\n--- Fastest Server ---");
        System.out.println(fastest);
    }

    private static void calculateAverageCpu() {

        if (servers.isEmpty()) {
            System.out.println("No servers available.");
            return;
        }

        double totalCpu = 0;

        for (Server server : servers) {
            totalCpu += server.getCpuUsage();
        }

        double average = totalCpu / servers.size();

        System.out.printf(
                "\nAverage CPU Usage: %.2f%%%n",
                average
        );
    }

    private static void searchServer(Scanner scanner) {

        System.out.print("\nEnter Server ID: ");
        int id = scanner.nextInt();

        Server server = findServerById(id);

        if (server == null) {
            System.out.println("Server not found.");
            return;
        }

        System.out.println("\n--- Server Found ---");
        System.out.println(server);
    }

    private static Server findServerById(int id) {

        for (Server server : servers) {

            if (server.getServerId() == id) {
                return server;
            }
        }

        return null;
    }

    private static void generateHealthReport() {

        int healthy = 0;
        int warning = 0;
        int critical = 0;
        int offline = 0;

        for (Server server : servers) {

            if (!server.isOnline()) {
                offline++;
            } else if (server.getHealthStatus().equals("CRITICAL")) {
                critical++;
            } else if (server.getHealthStatus().equals("WARNING")) {
                warning++;
            } else {
                healthy++;
            }
        }

        System.out.println("\n=================================================");
        System.out.println("             SERVER HEALTH REPORT");
        System.out.println("=================================================");
        System.out.println("Total Servers : " + servers.size());
        System.out.println("Healthy       : " + healthy);
        System.out.println("Warning       : " + warning);
        System.out.println("Critical      : " + critical);
        System.out.println("Offline       : " + offline);
        System.out.println("=================================================");
    }
}