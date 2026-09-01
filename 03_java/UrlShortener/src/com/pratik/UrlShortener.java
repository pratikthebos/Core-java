package com.pratik;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class UrlShortener {

    private static final Map<String, String> urlMap = new HashMap<>();

    private static int counter = 1000;

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        while (true) {

            System.out.println("\n=== URL SHORTENER ===");
            System.out.println("1. Shorten URL");
            System.out.println("2. Open Short URL");
            System.out.println("3. Exit");

            System.out.print("Choose: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    shortenUrl(scanner);
                    break;

                case 2:
                    openUrl(scanner);
                    break;

                case 3:
                    System.out.println("Application closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("Invalid choice.");
            }
        }
    }

    private static void shortenUrl(Scanner scanner) {

        System.out.print("Enter original URL: ");
        String originalUrl = scanner.nextLine().trim();

        if (originalUrl.isEmpty()) {
            System.out.println("URL cannot be empty.");
            return;
        }

        String shortCode = "prk" + counter++;

        urlMap.put(shortCode, originalUrl);

        System.out.println(
                "Short URL: https://short.local/" + shortCode
        );
    }

    private static void openUrl(Scanner scanner) {

        System.out.print("Enter short code: ");
        String shortCode = scanner.nextLine().trim();

        String originalUrl = urlMap.get(shortCode);

        if (originalUrl == null) {
            System.out.println("Short URL not found.");
            return;
        }

        System.out.println(
                "Original URL: " + originalUrl
        );
    }
}