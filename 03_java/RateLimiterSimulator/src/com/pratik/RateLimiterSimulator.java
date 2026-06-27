package com.pratik;

import java.util.*;

public class RateLimiterSimulator {

    static class TokenBucket {

        private final int capacity;
        private final int refillRate;
        private int tokens;
        private long lastRefillTime;

        public TokenBucket(int capacity, int refillRate) {
            this.capacity = capacity;
            this.refillRate = refillRate;
            this.tokens = capacity;
            this.lastRefillTime = System.currentTimeMillis();
        }

        private void refill() {
            long now = System.currentTimeMillis();
            long seconds = (now - lastRefillTime) / 1000;

            if (seconds > 0) {
                int newTokens = (int) (seconds * refillRate);
                tokens = Math.min(capacity, tokens + newTokens);
                lastRefillTime = now;
            }
        }

        public synchronized boolean allowRequest() {
            refill();

            if (tokens > 0) {
                tokens--;
                return true;
            }

            return false;
        }

        public int getTokens() {
            refill();
            return tokens;
        }
    }

    static class RateLimiterService {

        private Map<String, TokenBucket> clients =
                new HashMap<>();

        public void registerClient(String clientId) {
            clients.put(clientId, new TokenBucket(5, 2));
            System.out.println("Client Registered: " + clientId);
        }

        public void processRequest(String clientId) {
            TokenBucket bucket = clients.get(clientId);

            if (bucket == null) {
                System.out.println("Client not found");
                return;
            }

            if (bucket.allowRequest()) {
                System.out.println(
                        "Request Allowed | Tokens Left: "
                                + bucket.getTokens());
            } else {
                System.out.println("Rate Limit Exceeded");
            }
        }
    }

    public static void main(String[] args)
            throws Exception {

        RateLimiterService service =
                new RateLimiterService();

        service.registerClient("CLIENT_1");

        for (int i = 1; i <= 7; i++) {
            service.processRequest("CLIENT_1");
        }

        Thread.sleep(3000);

        System.out.println("\nAfter Refill:");

        service.processRequest("CLIENT_1");
        service.processRequest("CLIENT_1");
    }
}