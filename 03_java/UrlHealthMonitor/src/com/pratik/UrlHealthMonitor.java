package com.pratik;

import java.util.*;

public class UrlHealthMonitor {

    static class Endpoint {

        private String url;
        private boolean active;
        private long responseTime;

        public Endpoint(String url) {
            this.url = url;
        }

        public String getUrl() {
            return url;
        }

        public void updateStatus(
                boolean active,
                long responseTime) {

            this.active = active;
            this.responseTime = responseTime;
        }

        @Override
        public String toString() {

            return "URL: " + url +
                    ", Status: " +
                    (active ? "UP" : "DOWN") +
                    ", Response Time: " +
                    responseTime + " ms";
        }
    }

    static class MonitoringService {

        private final Map<String, Endpoint>
                endpoints = new HashMap<>();

        public void registerUrl(
                String url) {

            endpoints.put(
                    url,
                    new Endpoint(url));

            System.out.println(
                    "URL Registered: " + url);
        }

        public void updateHealth(
                String url,
                boolean status,
                long responseTime) {

            Endpoint endpoint =
                    endpoints.get(url);

            if (endpoint != null) {

                endpoint.updateStatus(
                        status,
                        responseTime);
            }
        }

        public void generateReport() {

            System.out.println(
                    "\nHealth Report");

            for (Endpoint endpoint
                    : endpoints.values()) {

                System.out.println(
                        endpoint);
            }
        }
    }

    public static void main(String[] args) {

        MonitoringService service =
                new MonitoringService();

        service.registerUrl(
                "https://api.company.com");

        service.registerUrl(
                "https://payment.company.com");

        service.updateHealth(
                "https://api.company.com",
                true,
                120);

        service.updateHealth(
                "https://payment.company.com",
                false,
                0);

        service.generateReport();
    }
}