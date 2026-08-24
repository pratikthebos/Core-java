package com.pratik;

import java.util.HashMap;
import java.util.Map;
import java.util.Scanner;

public class InventoryReservationSystem {

    static class Product {

        private int productId;
        private String name;
        private int availableStock;

        public Product(int productId, String name, int availableStock) {
            this.productId = productId;
            this.name = name;
            this.availableStock = availableStock;
        }

        public int getProductId() {
            return productId;
        }

        public String getName() {
            return name;
        }

        public int getAvailableStock() {
            return availableStock;
        }

        public boolean reserve(int quantity) {

            if (quantity <= 0 || quantity > availableStock) {
                return false;
            }

            availableStock -= quantity;
            return true;
        }

        public void release(int quantity) {

            if (quantity > 0) {
                availableStock += quantity;
            }
        }

        @Override
        public String toString() {
            return String.format(
                    "ID: %-5d | Product: %-18s | Available Stock: %d",
                    productId,
                    name,
                    availableStock
            );
        }
    }

    private static final Map<Integer, Product> inventory =
            new HashMap<>();

    private static final Map<String, Integer> reservations =
            new HashMap<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadProducts();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    displayInventory();
                    break;

                case 2:
                    reserveProduct(scanner);
                    break;

                case 3:
                    releaseProduct(scanner);
                    break;

                case 4:
                    checkReservation(scanner);
                    break;

                case 5:
                    displayReservations();
                    break;

                case 6:
                    addProduct(scanner);
                    break;

                case 7:
                    displayStatistics();
                    break;

                case 8:
                    System.out.println(
                            "\nInventory Reservation System closed."
                    );
                    scanner.close();
                    return;

                default:
                    System.out.println(
                            "\nInvalid choice."
                    );
            }
        }
    }

    private static void displayMenu() {

        System.out.println(
                "\n=============================================="
        );
        System.out.println(
                "        INVENTORY RESERVATION SYSTEM"
        );
        System.out.println(
                "=============================================="
        );

        System.out.println("1. Display Inventory");
        System.out.println("2. Reserve Product");
        System.out.println("3. Release Reservation");
        System.out.println("4. Check Reservation");
        System.out.println("5. Display Reservations");
        System.out.println("6. Add Product");
        System.out.println("7. Display Statistics");
        System.out.println("8. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void loadProducts() {

        inventory.put(
                101,
                new Product(101, "Laptop", 20)
        );

        inventory.put(
                102,
                new Product(102, "Keyboard", 50)
        );

        inventory.put(
                103,
                new Product(103, "Monitor", 15)
        );

        inventory.put(
                104,
                new Product(104, "Mouse", 75)
        );
    }

    private static void displayInventory() {

        System.out.println("\n--- Inventory ---");

        if (inventory.isEmpty()) {
            System.out.println("Inventory is empty.");
            return;
        }

        for (Product product : inventory.values()) {
            System.out.println(product);
        }
    }

    private static void reserveProduct(Scanner scanner) {

        System.out.println("\n--- Reserve Product ---");

        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();

        Product product = inventory.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();
        scanner.nextLine();

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();

        if (customerId.isEmpty()) {
            System.out.println("Customer ID is required.");
            return;
        }

        if (!product.reserve(quantity)) {

            System.out.println(
                    "Reservation failed. Available stock: "
                            + product.getAvailableStock()
            );

            return;
        }

        String reservationKey =
                createReservationKey(customerId, productId);

        int previousQuantity =
                reservations.getOrDefault(
                        reservationKey,
                        0
                );

        reservations.put(
                reservationKey,
                previousQuantity + quantity
        );

        System.out.println(
                "\nReservation successful."
        );

        System.out.println(
                "Customer : " + customerId
        );

        System.out.println(
                "Product  : " + product.getName()
        );

        System.out.println(
                "Quantity : " + quantity
        );

        System.out.println(
                "Remaining Stock : "
                        + product.getAvailableStock()
        );
    }

    private static void releaseProduct(Scanner scanner) {

        System.out.println("\n--- Release Reservation ---");

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();

        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();

        String key =
                createReservationKey(
                        customerId,
                        productId
                );

        Integer reservedQuantity =
                reservations.get(key);

        if (reservedQuantity == null) {

            System.out.println(
                    "Reservation not found."
            );

            return;
        }

        Product product = inventory.get(productId);

        if (product == null) {
            System.out.println("Product not found.");
            return;
        }

        product.release(reservedQuantity);

        reservations.remove(key);

        System.out.println(
                "\nReservation released successfully."
        );

        System.out.println(
                "Released Quantity: "
                        + reservedQuantity
        );
    }

    private static void checkReservation(
            Scanner scanner) {

        System.out.println("\n--- Check Reservation ---");

        System.out.print("Enter Customer ID: ");
        String customerId = scanner.nextLine().trim();

        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();

        String key =
                createReservationKey(
                        customerId,
                        productId
                );

        Integer quantity = reservations.get(key);

        if (quantity == null) {

            System.out.println(
                    "No reservation found."
            );

            return;
        }

        Product product = inventory.get(productId);

        System.out.println(
                "\nReservation Found"
        );

        System.out.println(
                "Customer : " + customerId
        );

        System.out.println(
                "Product  : " + product.getName()
        );

        System.out.println(
                "Quantity : " + quantity
        );
    }

    private static void displayReservations() {

        System.out.println(
                "\n--- Active Reservations ---"
        );

        if (reservations.isEmpty()) {

            System.out.println(
                    "No active reservations."
            );

            return;
        }

        for (Map.Entry<String, Integer> entry
                : reservations.entrySet()) {

            System.out.println(
                    "Reservation: "
                            + entry.getKey()
                            + " | Quantity: "
                            + entry.getValue()
            );
        }
    }

    private static void addProduct(Scanner scanner) {

        System.out.println("\n--- Add Product ---");

        System.out.print("Enter Product ID: ");
        int productId = scanner.nextInt();
        scanner.nextLine();

        if (inventory.containsKey(productId)) {

            System.out.println(
                    "Product ID already exists."
            );

            return;
        }

        System.out.print("Enter Product Name: ");
        String name = scanner.nextLine().trim();

        System.out.print("Enter Stock Quantity: ");
        int stock = scanner.nextInt();

        if (name.isEmpty() || stock < 0) {

            System.out.println(
                    "Invalid product information."
            );

            return;
        }

        inventory.put(
                productId,
                new Product(
                        productId,
                        name,
                        stock
                )
        );

        System.out.println(
                "Product added successfully."
        );
    }

    private static void displayStatistics() {

        int totalStock = 0;

        for (Product product : inventory.values()) {

            totalStock += product.getAvailableStock();
        }

        int reservedItems = 0;

        for (int quantity : reservations.values()) {

            reservedItems += quantity;
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "         INVENTORY STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Products    : "
                        + inventory.size()
        );

        System.out.println(
                "Available Items   : "
                        + totalStock
        );

        System.out.println(
                "Reserved Items    : "
                        + reservedItems
        );

        System.out.println(
                "Active Reservations: "
                        + reservations.size()
        );

        System.out.println(
                "=============================================="
        );
    }

    private static String createReservationKey(
            String customerId,
            int productId) {

        return customerId + ":" + productId;
    }
}