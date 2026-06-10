package com.pratik;

import java.util.*;

public class ApiKeyManagementSystem {

    static class ApiKey {

        private String key;
        private boolean active;
        private int usageCount;

        public ApiKey(String key) {
            this.key = key;
            this.active = true;
            this.usageCount = 0;
        }

        public String getKey() {
            return key;
        }

        public boolean isActive() {
            return active;
        }

        public void revoke() {
            active = false;
        }

        public void incrementUsage() {
            usageCount++;
        }

        @Override
        public String toString() {

            return "API Key: " + key +
                    ", Active: " + active +
                    ", Usage Count: " + usageCount;
        }
    }

    static class ApiKeyService {

        private final Map<String, ApiKey>
                apiKeys = new HashMap<>();

        public String generateKey() {

            String key =
                    UUID.randomUUID()
                            .toString();

            apiKeys.put(
                    key,
                    new ApiKey(key));

            return key;
        }

        public boolean validateKey(
                String key) {

            ApiKey apiKey =
                    apiKeys.get(key);

            if (apiKey == null ||
                    !apiKey.isActive()) {

                return false;
            }

            apiKey.incrementUsage();

            return true;
        }

        public void revokeKey(
                String key) {

            ApiKey apiKey =
                    apiKeys.get(key);

            if (apiKey != null) {
                apiKey.revoke();
            }
        }

        public void showKeys() {

            System.out.println(
                    "\nAPI Key Report");

            for (ApiKey apiKey
                    : apiKeys.values()) {

                System.out.println(
                        apiKey);
            }
        }
    }

    public static void main(String[] args) {

        ApiKeyService service =
                new ApiKeyService();

        String key1 =
                service.generateKey();

        String key2 =
                service.generateKey();

        System.out.println(
                "Generated Key: "
                        + key1);

        service.validateKey(key1);
        service.validateKey(key1);

        service.revokeKey(key2);

        service.showKeys();
    }
}