package com.pratik;

import java.util.*;

public class DistributedCacheSystem {

    static class CacheEntry {
        private String value;
        private long expiryTime;

        public CacheEntry(String value, long ttlMillis) {
            this.value = value;
            this.expiryTime = System.currentTimeMillis() + ttlMillis;
        }

        public boolean isExpired() {
            return System.currentTimeMillis() > expiryTime;
        }

        public String getValue() {
            return value;
        }
    }

    static class CacheService {
        private Map<String, CacheEntry> cache;
        private int hitCount;
        private int missCount;

        public CacheService() {
            cache = new HashMap<>();
            hitCount = 0;
            missCount = 0;
        }

        public void put(String key, String value, long ttlMillis) {
            cache.put(key, new CacheEntry(value, ttlMillis));
            System.out.println("Cached: " + key);
        }

        public String get(String key) {
            CacheEntry entry = cache.get(key);

            if (entry == null) {
                missCount++;
                return "Cache Miss";
            }

            if (entry.isExpired()) {
                cache.remove(key);
                missCount++;
                return "Cache Expired";
            }

            hitCount++;
            return entry.getValue();
        }

        public void showStats() {
            System.out.println("\nCache Statistics");
            System.out.println("Hits: " + hitCount);
            System.out.println("Misses: " + missCount);
        }
    }

    public static void main(String[] args) throws Exception {

        CacheService cache = new CacheService();

        cache.put("user1", "Pratik", 5000);

        System.out.println(cache.get("user1"));

        Thread.sleep(6000);

        System.out.println(cache.get("user1"));

        cache.showStats();
    }
}