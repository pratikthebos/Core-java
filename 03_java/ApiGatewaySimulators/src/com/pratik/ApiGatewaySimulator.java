package com.pratik;

import java.util.*;

public class ApiGatewaySimulator {

    static class BackendService {

        private final String serviceName;
        private boolean healthy;

        public BackendService(String serviceName) {
            this.serviceName = serviceName;
            this.healthy = true;
        }

        public String getServiceName() {
            return serviceName;
        }

        public boolean isHealthy() {
            return healthy;
        }

        public void setHealthy(boolean healthy) {
            this.healthy = healthy;
        }
    }

    static class Client {

        private final String apiKey;
        private int requestCount;

        public Client(String apiKey) {
            this.apiKey = apiKey;
            this.requestCount = 0;
        }

        public String getApiKey() {
            return apiKey;
        }

        public boolean allowRequest(int limit) {

            if (requestCount >= limit) {
                return false;
            }

            requestCount++;
            return true;
        }
    }

    static class ApiGateway {

        private final Map<String, BackendService> services =
                new HashMap<>();

        private final Map<String, Client> clients =
                new HashMap<>();

        private static final int RATE_LIMIT = 5;

        public void registerService(BackendService service) {

            services.put(service.getServiceName(), service);
        }

        public void registerClient(String apiKey) {

            clients.put(apiKey, new Client(apiKey));
        }

        public void processRequest(
                String apiKey,
                String serviceName) {

            Client client = clients.get(apiKey);

            if (client == null) {
                System.out.println("401 Unauthorized");
                return;
            }

            if (!client.allowRequest(RATE_LIMIT)) {
                System.out.println("429 Too Many Requests");
                return;
            }

            BackendService service =
                    services.get(serviceName);

            if (service == null) {
                System.out.println("404 Service Not Found");
                return;
            }

            if (!service.isHealthy()) {
                System.out.println(
                        "503 Service Unavailable");
                return;
            }

            System.out.println(
                    "200 OK -> Routed to "
                            + service.getServiceName());
        }
    }

    public static void main(String[] args) {

        ApiGateway gateway =
                new ApiGateway();

        gateway.registerService(
                new BackendService("User-Service"));

        gateway.registerService(
                new BackendService("Order-Service"));

        gateway.registerClient("API_KEY_123");

        for (int i = 1; i <= 7; i++) {

            gateway.processRequest(
                    "API_KEY_123",
                    "User-Service");
        }
    }
}