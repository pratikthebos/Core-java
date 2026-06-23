package com.pratik;

import java.util.*;

public class ApiGatewaySimulator {

    static class Service {

        private String serviceName;
        private String endpoint;

        public Service(
                String serviceName,
                String endpoint) {

            this.serviceName = serviceName;
            this.endpoint = endpoint;
        }

        public String getEndpoint() {
            return endpoint;
        }

        @Override
        public String toString() {
            return serviceName +
                    " -> " + endpoint;
        }
    }

    static class ApiGateway {

        private Map<String, Service> services =
                new HashMap<>();

        private Map<String, Integer> requestCount =
                new HashMap<>();

        private static final int LIMIT = 5;

        public void registerService(
                String route,
                Service service) {

            services.put(route, service);
        }

        public void handleRequest(
                String route,
                String apiKey) {

            if (!authenticate(apiKey)) {
                System.out.println("Unauthorized");
                return;
            }

            int count =
                    requestCount.getOrDefault(
                            apiKey, 0);

            if (count >= LIMIT) {
                System.out.println(
                        "Rate Limit Exceeded");
                return;
            }

            requestCount.put(
                    apiKey,
                    count + 1);

            Service service =
                    services.get(route);

            if (service == null) {
                System.out.println(
                        "Route Not Found");
                return;
            }

            System.out.println(
                    "Routing request to: "
                            + service.getEndpoint());
        }

        private boolean authenticate(
                String apiKey) {

            return apiKey != null &&
                    apiKey.startsWith("KEY");
        }

        public void showStats() {
            System.out.println(
                    "\nRequest Statistics");
            System.out.println(requestCount);
        }
    }

    public static void main(String[] args) {

        ApiGateway gateway =
                new ApiGateway();

        gateway.registerService(
                "/payment",
                new Service(
                        "PaymentService",
                        "http://payment-service"));

        gateway.registerService(
                "/orders",
                new Service(
                        "OrderService",
                        "http://order-service"));

        gateway.handleRequest(
                "/payment",
                "KEY123");

        gateway.handleRequest(
                "/orders",
                "KEY123");

        gateway.showStats();
    }
}