package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class ApiResponseAnalyzer {

    static class ApiRequest {

        private int requestId;
        private String endpoint;
        private int statusCode;
        private long responseTime;

        public ApiRequest(int requestId, String endpoint,
                          int statusCode, long responseTime) {

            this.requestId = requestId;
            this.endpoint = endpoint;
            this.statusCode = statusCode;
            this.responseTime = responseTime;
        }

        public int getRequestId() {
            return requestId;
        }

        public String getEndpoint() {
            return endpoint;
        }

        public int getStatusCode() {
            return statusCode;
        }

        public long getResponseTime() {
            return responseTime;
        }

        @Override
        public String toString() {
            return String.format(
                "ID: %-4d | Endpoint: %-20s | Status: %-3d | Response: %d ms",
                requestId,
                endpoint,
                statusCode,
                responseTime
            );
        }
    }

    private static final List<ApiRequest> requests = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleData();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addRequest(scanner);
                    break;

                case 2:
                    displayRequests();
                    break;

                case 3:
                    calculateAverageResponseTime();
                    break;

                case 4:
                    findSlowestRequest();
                    break;

                case 5:
                    countFailedRequests();
                    break;

                case 6:
                    searchByEndpoint(scanner);
                    break;

                case 7:
                    generateReport();
                    break;

                case 8:
                    System.out.println("\nApplication closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void displayMenu() {

        System.out.println("\n==============================================");
        System.out.println("          API RESPONSE ANALYZER");
        System.out.println("==============================================");
        System.out.println("1. Add API Request");
        System.out.println("2. Display All Requests");
        System.out.println("3. Average Response Time");
        System.out.println("4. Find Slowest Request");
        System.out.println("5. Count Failed Requests");
        System.out.println("6. Search By Endpoint");
        System.out.println("7. Generate Performance Report");
        System.out.println("8. Exit");
        System.out.println("==============================================");
    }

    private static void loadSampleData() {

        requests.add(
            new ApiRequest(101, "/api/users", 200, 145)
        );

        requests.add(
            new ApiRequest(102, "/api/login", 200, 320)
        );

        requests.add(
            new ApiRequest(103, "/api/products", 500, 890)
        );

        requests.add(
            new ApiRequest(104, "/api/orders", 200, 210)
        );

        requests.add(
            new ApiRequest(105, "/api/payment", 404, 560)
        );
    }

    private static void addRequest(Scanner scanner) {

        System.out.println("\n--- Add API Request ---");

        System.out.print("Enter Request ID: ");
        int id = scanner.nextInt();
        scanner.nextLine();

        if (findById(id) != null) {
            System.out.println("Request ID already exists.");
            return;
        }

        System.out.print("Enter Endpoint: ");
        String endpoint = scanner.nextLine();

        System.out.print("Enter HTTP Status Code: ");
        int statusCode = scanner.nextInt();

        System.out.print("Enter Response Time (ms): ");
        long responseTime = scanner.nextLong();

        if (responseTime < 0) {
            System.out.println("Response time cannot be negative.");
            return;
        }

        requests.add(
            new ApiRequest(
                id,
                endpoint,
                statusCode,
                responseTime
            )
        );

        System.out.println("API request added successfully.");
    }

    private static void displayRequests() {

        System.out.println("\n--- API Request Logs ---");

        if (requests.isEmpty()) {
            System.out.println("No requests available.");
            return;
        }

        for (ApiRequest request : requests) {
            System.out.println(request);
        }

        System.out.println("----------------------------------------------");
        System.out.println("Total Requests: " + requests.size());
    }

    private static void calculateAverageResponseTime() {

        if (requests.isEmpty()) {
            System.out.println("No requests available.");
            return;
        }

        long total = 0;

        for (ApiRequest request : requests) {
            total += request.getResponseTime();
        }

        double average =
            (double) total / requests.size();

        System.out.printf(
            "\nAverage Response Time: %.2f ms%n",
            average
        );
    }

    private static void findSlowestRequest() {

        if (requests.isEmpty()) {
            System.out.println("No requests available.");
            return;
        }

        ApiRequest slowest = requests.get(0);

        for (ApiRequest request : requests) {

            if (request.getResponseTime()
                    > slowest.getResponseTime()) {

                slowest = request;
            }
        }

        System.out.println("\n--- Slowest API Request ---");
        System.out.println(slowest);
    }

    private static void countFailedRequests() {

        int failed = 0;

        for (ApiRequest request : requests) {

            if (request.getStatusCode() >= 400) {
                failed++;
            }
        }

        System.out.println(
            "\nFailed Requests: " + failed
        );

        System.out.println(
            "Successful Requests: "
            + (requests.size() - failed)
        );
    }

    private static void searchByEndpoint(Scanner scanner) {

        System.out.print("\nEnter endpoint keyword: ");
        String keyword = scanner.nextLine();

        boolean found = false;

        System.out.println("\n--- Search Results ---");

        for (ApiRequest request : requests) {

            if (request.getEndpoint()
                    .toLowerCase()
                    .contains(keyword.toLowerCase())) {

                System.out.println(request);
                found = true;
            }
        }

        if (!found) {
            System.out.println("No matching endpoint found.");
        }
    }

    private static void generateReport() {

        if (requests.isEmpty()) {
            System.out.println("No data available.");
            return;
        }

        long totalTime = 0;
        int successful = 0;
        int failed = 0;

        ApiRequest slowest = requests.get(0);

        for (ApiRequest request : requests) {

            totalTime += request.getResponseTime();

            if (request.getStatusCode() >= 200
                    && request.getStatusCode() < 400) {

                successful++;
            } else {
                failed++;
            }

            if (request.getResponseTime()
                    > slowest.getResponseTime()) {

                slowest = request;
            }
        }

        double average =
            (double) totalTime / requests.size();

        System.out.println("\n==============================================");
        System.out.println("             API PERFORMANCE REPORT");
        System.out.println("==============================================");
        System.out.println("Total Requests      : " + requests.size());
        System.out.println("Successful Requests : " + successful);
        System.out.println("Failed Requests     : " + failed);

        System.out.printf(
            "Average Response   : %.2f ms%n",
            average
        );

        System.out.println(
            "Slowest Endpoint   : " + slowest.getEndpoint()
        );

        System.out.println(
            "Slowest Response   : "
            + slowest.getResponseTime() + " ms"
        );

        System.out.println("==============================================");
    }

    private static ApiRequest findById(int id) {

        for (ApiRequest request : requests) {

            if (request.getRequestId() == id) {
                return request;
            }
        }

        return null;
    }
}