package com.pratik;

import java.util.*;

public class UrlShortenerSystem {

    static class UrlEntry {

        private String originalUrl;
        private String shortCode;
        private int clickCount;

        public UrlEntry(
                String originalUrl,
                String shortCode) {

            this.originalUrl = originalUrl;
            this.shortCode = shortCode;
            this.clickCount = 0;
        }

        public void incrementClicks() {
            clickCount++;
        }

        public String getOriginalUrl() {
            return originalUrl;
        }

        @Override
        public String toString() {
            return "Short URL: https://short.ly/" +
                    shortCode +
                    ", Original: " + originalUrl +
                    ", Clicks: " + clickCount;
        }
    }

    static class UrlShortener {

        private Map<String, UrlEntry> shortMap =
                new HashMap<>();

        private Map<String, String> longMap =
                new HashMap<>();

        private int counter = 1;

        public String shortenUrl(String url) {

            if (longMap.containsKey(url)) {
                return longMap.get(url);
            }

            String shortCode =
                    Integer.toHexString(counter++);

            UrlEntry entry =
                    new UrlEntry(url, shortCode);

            shortMap.put(shortCode, entry);
            longMap.put(url, shortCode);

            return shortCode;
        }

        public String resolveUrl(String shortCode) {

            UrlEntry entry =
                    shortMap.get(shortCode);

            if (entry == null) {
                return "URL Not Found";
            }

            entry.incrementClicks();

            return entry.getOriginalUrl();
        }

        public void showAnalytics() {

            System.out.println(
                    "\nURL Analytics");

            for (UrlEntry entry :
                    shortMap.values()) {

                System.out.println(entry);
            }
        }
    }

    public static void main(String[] args) {

        UrlShortener service =
                new UrlShortener();

        String code =
                service.shortenUrl(
                        "https://google.com/search?q=java");

        System.out.println(
                "Short Code: " + code);

        System.out.println(
                "Original URL: " +
                        service.resolveUrl(code));

        service.resolveUrl(code);

        service.showAnalytics();
    }
}