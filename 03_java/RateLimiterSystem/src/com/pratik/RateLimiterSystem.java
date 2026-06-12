package com.pratik;

import java.util.*;

public class RateLimiterSystem {

    static class RateLimiter {

        private final int maxRequests;
        private final long timeWindowMillis;

        private final Map<String, Queue<Long>>
                requestLogs = new HashMap<>();

        public RateLimiter(
                int maxRequests,
                long timeWindowMillis) {

            this.maxRequests = maxRequests;
            this.timeWindowMillis = timeWindowMillis;
        }

        public boolean allowRequest(
                String userId) {

            long currentTime =
                    System.currentTimeMillis();

            requestLogs.putIfAbsent(
                    userId,
                    new LinkedList<>());

            Queue<Long> timestamps =
                    requestLogs.get(userId);

            while (!timestamps.isEmpty() &&
                    currentTime - timestamps.peek()
                            > timeWindowMillis) {

                timestamps.poll();
            }

            if (timestamps.size()
                    >= maxRequests) {

                return false;
            }

            timestamps.offer(currentTime);

            return true;
        }

        public void printUserStats(
                String userId) {

            Queue<Long> timestamps =
                    requestLogs.get(userId);

            int count = timestamps == null
                    ? 0
                    : timestamps.size();

            System.out.println(
                    userId +
                    " Active Requests: " +
                    count);
        }
    }

    public static void main(String[] args)
            throws Exception {

        RateLimiter limiter =
                new RateLimiter(
                        3,
                        5000);

        String user = "USER_101";

        for (int i = 1; i <= 5; i++) {

            boolean allowed =
                    limiter.allowRequest(user);

            System.out.println(
                    "Request " + i +
                    " -> " +
                    (allowed
                            ? "ALLOWED"
                            : "BLOCKED"));

            Thread.sleep(1000);
        }

        limiter.printUserStats(user);
    }
}