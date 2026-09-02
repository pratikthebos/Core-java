package com.pratik;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class PortHealthChecker {

    private static final Map<Integer, String> services = new HashMap<>();

    public static void main(String[] args) {

        loadServices();

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== PORT HEALTH CHECKER ===");
            System.out.println("1. Check Port");
            System.out.println("2. Show Services");
            System.out.println("3. Exit");

            System.out.print("Choose: ");
            int choice = scanner.nextInt();

            switch (choice) {

                case 1:
                    checkPort(scanner);
                    break;

                case 2:
                    services.forEach(
                            (port, service) ->
                                    System.out.println(
                                            port + " -> " + service
                                    )
                    );
                    break;

                case 3:
                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void loadServices() {

        services.put(22, "SSH");
        services.put(80, "HTTP");
        services.put(443, "HTTPS");
        services.put(3306, "MySQL");
        services.put(8080, "Application Server");
    }

    private static void checkPort(Scanner scanner) {

        System.out.print("Enter port number: ");
        int port = scanner.nextInt();

        if (port < 1 || port > 65535) {
            System.out.println("Invalid port number.");
            return;
        }

        if (services.containsKey(port)) {

            System.out.println(
                    "Port " + port
                            + " -> "
                            + services.get(port)
                            + " [REGISTERED]"
            );

        } else {

            System.out.println(
                    "Port " + port + " [UNKNOWN]"
            );
        }
    }
}