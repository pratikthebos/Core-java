package com.pratik;

import java.util.*;

public class ApiRateLimiter {

    static class RateLimiter {

        private final int maxRequests;
        private final long timeWindow;

        private final Map<String,
                Queue<Long>> requestTracker;

        public RateLimiter(
                int maxRequests,
                long timeWindowInSeconds) {

            this.maxRequests =
                    maxRequests;

            this.timeWindow =
                    timeWindowInSeconds
                            * 1000;

            requestTracker =
                    new HashMap<>();
        }

        public boolean allowRequest(
                String userId) {

            long currentTime =
                    System.currentTimeMillis();

            requestTracker.putIfAbsent(
                    userId,
                    new LinkedList<>());

            Queue<Long> timestamps =
                    requestTracker
                            .get(userId);

            // Remove expired requests
            while (!timestamps.isEmpty() &&
                    currentTime
                            - timestamps.peek()
                            >= timeWindow) {

                timestamps.poll();
            }

            // Check limit
            if (timestamps.size()
                    < maxRequests) {

                timestamps.offer(
                        currentTime);

                return true;
            }

            return false;
        }
    }

    public static void main(String[] args)
            throws InterruptedException {

        RateLimiter limiter =
                new RateLimiter(
                        3, 10);

        String user =
                "employee_101";

        System.out.println(
                limiter.allowRequest(user));

        System.out.println(
                limiter.allowRequest(user));

        System.out.println(
                limiter.allowRequest(user));

        // Exceeds limit
        System.out.println(
                limiter.allowRequest(user));
    }
}