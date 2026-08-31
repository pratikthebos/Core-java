package com.pratik;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Scanner;

public class ApiRequestTracker {

    static class ApiRequest {

        private final String clientId;
        private final String endpoint;
        private final LocalDateTime timestamp;
        private final int responseCode;

        public ApiRequest(
                String clientId,
                String endpoint,
                int responseCode) {

            this.clientId = clientId;
            this.endpoint = endpoint;
            this.responseCode = responseCode;
            this.timestamp = LocalDateTime.now();
        }

        public String getClientId() {
            return clientId;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public int getResponseCode() {
            return responseCode;
        }

        public LocalDateTime getTimestamp() {
            return timestamp;
        }

        @Override
        public String toString() {

            return String.format(
                    "Client: %-12s | Endpoint: %-20s | Response: %-3d | Time: %s",
                    clientId,
                    endpoint,
                    responseCode,
                    timestamp
            );
        }
    }

    private static final List<ApiRequest> requests =
            new ArrayList<>();

    private static final Map<String, Integer> clientRequestCount =
            new HashMap<>();

    private static final Map<String, Integer> endpointRequestCount =
            new HashMap<>();

    private static final int RATE_LIMIT = 5;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleRequests();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    recordRequest(scanner);
                    break;

                case 2:
                    displayRequests();
                    break;

                case 3:
                    displayClientStatistics();
                    break;

                case 4:
                    displayEndpointStatistics();
                    break;

                case 5:
                    checkRateLimit(scanner);
                    break;

                case 6:
                    displayErrorRequests();
                    break;

                case 7:
                    displayOverallStatistics();
                    break;

                case 8:
                    System.out.println(
                            "\nAPI Request Tracker closed."
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
                "             API REQUEST TRACKER"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Record API Request");
        System.out.println("2. Display All Requests");
        System.out.println("3. Client Statistics");
        System.out.println("4. Endpoint Statistics");
        System.out.println("5. Check Rate Limit");
        System.out.println("6. Display Error Requests");
        System.out.println("7. Overall Statistics");
        System.out.println("8. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void loadSampleRequests() {

        recordSample(
                "client-101",
                "/api/users",
                200
        );

        recordSample(
                "client-101",
                "/api/users",
                200
        );

        recordSample(
                "client-102",
                "/api/orders",
                201
        );

        recordSample(
                "client-101",
                "/api/orders",
                500
        );

        recordSample(
                "client-103",
                "/api/products",
                200
        );

        recordSample(
                "client-102",
                "/api/orders",
                404
        );
    }

    private static void recordSample(
            String clientId,
            String endpoint,
            int responseCode) {

        ApiRequest request =
                new ApiRequest(
                        clientId,
                        endpoint,
                        responseCode
                );

        requests.add(request);

        clientRequestCount.put(
                clientId,
                clientRequestCount.getOrDefault(
                        clientId,
                        0
                ) + 1
        );

        endpointRequestCount.put(
                endpoint,
                endpointRequestCount.getOrDefault(
                        endpoint,
                        0
                ) + 1
        );
    }

    private static void recordRequest(
            Scanner scanner) {

        System.out.println(
                "\n--- Record API Request ---"
        );

        System.out.print("Enter Client ID: ");
        String clientId =
                scanner.nextLine().trim();

        System.out.print("Enter Endpoint: ");
        String endpoint =
                scanner.nextLine().trim();

        System.out.print("Enter Response Code: ");
        int responseCode =
                scanner.nextInt();

        scanner.nextLine();

        if (clientId.isEmpty()
                || endpoint.isEmpty()) {

            System.out.println(
                    "Client ID and endpoint are required."
            );

            return;
        }

        if (responseCode < 100
                || responseCode > 599) {

            System.out.println(
                    "Invalid HTTP response code."
            );

            return;
        }

        recordSample(
                clientId,
                endpoint,
                responseCode
        );

        System.out.println(
                "\nAPI request recorded successfully."
        );
    }

    private static void displayRequests() {

        System.out.println(
                "\n--- API Requests ---"
        );

        if (requests.isEmpty()) {

            System.out.println(
                    "No requests available."
            );

            return;
        }

        for (ApiRequest request : requests) {
            System.out.println(request);
        }

        System.out.println(
                "\nTotal Requests: "
                        + requests.size()
        );
    }

    private static void displayClientStatistics() {

        System.out.println(
                "\n--- Client Request Statistics ---"
        );

        if (clientRequestCount.isEmpty()) {

            System.out.println(
                    "No client data available."
            );

            return;
        }

        for (Map.Entry<String, Integer> entry
                : clientRequestCount.entrySet()) {

            System.out.println(
                    String.format(
                            "Client: %-15s | Requests: %d",
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }
    }

    private static void displayEndpointStatistics() {

        System.out.println(
                "\n--- Endpoint Statistics ---"
        );

        if (endpointRequestCount.isEmpty()) {

            System.out.println(
                    "No endpoint data available."
            );

            return;
        }

        for (Map.Entry<String, Integer> entry
                : endpointRequestCount.entrySet()) {

            System.out.println(
                    String.format(
                            "Endpoint: %-25s | Requests: %d",
                            entry.getKey(),
                            entry.getValue()
                    )
            );
        }
    }

    private static void checkRateLimit(
            Scanner scanner) {

        System.out.println(
                "\n--- Rate Limit Check ---"
        );

        System.out.print("Enter Client ID: ");

        String clientId =
                scanner.nextLine().trim();

        int count =
                clientRequestCount.getOrDefault(
                        clientId,
                        0
                );

        System.out.println(
                "Current Requests: " + count
        );

        System.out.println(
                "Rate Limit      : " + RATE_LIMIT
        );

        if (count >= RATE_LIMIT) {

            System.out.println(
                    "STATUS: RATE LIMIT EXCEEDED"
            );

        } else {

            System.out.println(
                    "STATUS: REQUEST ALLOWED"
            );

            System.out.println(
                    "Remaining: "
                            + (RATE_LIMIT - count)
            );
        }
    }

    private static void displayErrorRequests() {

        System.out.println(
                "\n--- Error Requests ---"
        );

        boolean found = false;

        for (ApiRequest request : requests) {

            if (request.getResponseCode() >= 400) {

                System.out.println(request);

                found = true;
            }
        }

        if (!found) {

            System.out.println(
                    "No error requests found."
            );
        }
    }

    private static void displayOverallStatistics() {

        int successful = 0;
        int clientErrors = 0;
        int serverErrors = 0;

        for (ApiRequest request : requests) {

            int code =
                    request.getResponseCode();

            if (code >= 200 && code < 300) {

                successful++;

            } else if (code >= 400 && code < 500) {

                clientErrors++;

            } else if (code >= 500) {

                serverErrors++;
            }
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "              API STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Requests : "
                        + requests.size()
        );

        System.out.println(
                "Successful     : "
                        + successful
        );

        System.out.println(
                "Client Errors  : "
                        + clientErrors
        );

        System.out.println(
                "Server Errors  : "
                        + serverErrors
        );

        System.out.println(
                "Unique Clients : "
                        + clientRequestCount.size()
        );

        System.out.println(
                "Unique Endpoints: "
                        + endpointRequestCount.size()
        );

        System.out.println(
                "=============================================="
        );
    }
}