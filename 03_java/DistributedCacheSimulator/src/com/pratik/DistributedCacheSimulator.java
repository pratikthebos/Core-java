package com.pratik;

import java.util.*;

public class DistributedCacheSimulator {

    static class CacheEntry {

        private String value;
        private long expiryTime;

        public CacheEntry(
                String value,
                long ttlMillis) {

            this.value = value;
            this.expiryTime =
                    System.currentTimeMillis()
                            + ttlMillis;
        }

        public boolean isExpired() {

            return System.currentTimeMillis()
                    > expiryTime;
        }

        public String getValue() {
            return value;
        }
    }

    static class CacheService {

        private final Map<String, CacheEntry>
                cache = new HashMap<>();

        private int hits = 0;
        private int misses = 0;

        public void put(
                String key,
                String value,
                long ttlMillis) {

            cache.put(
                    key,
                    new CacheEntry(
                            value,
                            ttlMillis));
        }

        public String get(String key) {

            CacheEntry entry =
                    cache.get(key);

            if (entry == null ||
                    entry.isExpired()) {

                cache.remove(key);
                misses++;
                return null;
            }

            hits++;
            return entry.getValue();
        }

        public void showStats() {

            System.out.println(
                    "\nCache Stats");

            System.out.println(
                    "Hits: " + hits);

            System.out.println(
                    "Misses: " + misses);

            double ratio =
                    (hits + misses == 0)
                            ? 0
                            : (hits * 100.0)
                            / (hits + misses);

            System.out.println(
                    "Hit Ratio: "
                            + ratio + "%");
        }
    }

    public static void main(String[] args)
            throws Exception {

        CacheService cache =
                new CacheService();

        cache.put(
                "user_1",
                "Pratik",
                3000);

        System.out.println(
                cache.get("user_1"));

        Thread.sleep(4000);

        System.out.println(
                cache.get("user_1"));

        cache.showStats();
    }
}