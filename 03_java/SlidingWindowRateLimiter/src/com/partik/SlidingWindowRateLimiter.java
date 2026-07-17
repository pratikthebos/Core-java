package com.partik;

import java.time.Instant;
import java.util.ArrayDeque;
import java.util.Deque;
import java.util.Map;
import java.util.concurrent.ConcurrentHashMap;

public class SlidingWindowRateLimiter {

    static class RateLimiter {

        private final int maxRequests;
        private final long windowMillis;

        private final Map<String, Deque<Long>> requestLog =
                new ConcurrentHashMap<>();

        public RateLimiter(int maxRequests,
                           long windowMillis) {

            this.maxRequests = maxRequests;
            this.windowMillis = windowMillis;
        }

        public synchronized boolean allowRequest(String userId) {

            long currentTime = Instant.now().toEpochMilli();

            requestLog.putIfAbsent(
                    userId,
                    new ArrayDeque<>());

            Deque<Long> requests =
                    requestLog.get(userId);

            while (!requests.isEmpty() &&
                    currentTime - requests.peekFirst()
                            >= windowMillis) {

                requests.pollFirst();
            }

            if (requests.size() >= maxRequests) {

                return false;
            }

            requests.offerLast(currentTime);

            return true;
        }

        public void printStatistics() {

            System.out.println("\nCurrent Statistics");

            requestLog.forEach(
                    (user, requests) ->
                            System.out.println(
                                    user +
                                    " -> " +
                                    requests.size() +
                                    " request(s)"));
        }
    }

    public static void main(String[] args)
            throws Exception {

        RateLimiter limiter =
                new RateLimiter(
                        5,
                        10000);

        String user = "USER-101";

        for (int i = 1; i <= 7; i++) {

            System.out.println(
                    "Request " + i +
                            " : " +
                            limiter.allowRequest(user));
        }

        limiter.printStatistics();

        Thread.sleep(11000);

        System.out.println(
                "\nAfter Window Reset");

        System.out.println(
                limiter.allowRequest(user));

        limiter.printStatistics();
    }
}