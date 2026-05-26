package com.pratik;

import java.util.*;

public class UrlCompressionService {

    static class TinyURL {

        private final Map<String, String> shortToLongUrl;
        private final Map<String, String> longToShortUrl;

        private static final String BASE_URL =
                "http://tiny.url/";

        private static final String CHARACTERS =
                "abcdefghijklmnopqrstuvwxyzABCDEFGHIJKLMNOPQRSTUVWXYZ0123456789";

        private final Random random;

        public TinyURL() {

            shortToLongUrl =
                    new HashMap<>();

            longToShortUrl =
                    new HashMap<>();

            random = new Random();
        }

        // Encode URL
        public String encode(String longUrl) {

            if (longToShortUrl
                    .containsKey(longUrl)) {

                return longToShortUrl
                        .get(longUrl);
            }

            String shortKey;

            do {
                shortKey = generateKey();
            }
            while (shortToLongUrl
                    .containsKey(shortKey));

            String shortUrl =
                    BASE_URL + shortKey;

            shortToLongUrl.put(
                    shortKey,
                    longUrl
            );

            longToShortUrl.put(
                    longUrl,
                    shortUrl
            );

            return shortUrl;
        }

        // Decode URL
        public String decode(
                String shortUrl) {

            String shortKey =
                    shortUrl.replace(
                            BASE_URL,
                            ""
                    );

            return shortToLongUrl
                    .getOrDefault(
                            shortKey,
                            "URL not found"
                    );
        }

        private String generateKey() {

            StringBuilder key =
                    new StringBuilder();

            for (int i = 0; i < 6; i++) {

                int index =
                        random.nextInt(
                                CHARACTERS.length());

                key.append(
                        CHARACTERS.charAt(index)
                );
            }

            return key.toString();
        }
    }

    public static void main(String[] args) {

        TinyURL tinyURL =
                new TinyURL();

        String originalUrl =
                "https://www.example.com/products/mobile";

        String shortUrl =
                tinyURL.encode(
                        originalUrl);

        System.out.println(
                "Short URL: "
                        + shortUrl);

        System.out.println(
                "Decoded URL: "
                        + tinyURL.decode(shortUrl));
    }
}