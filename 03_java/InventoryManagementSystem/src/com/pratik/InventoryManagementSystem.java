package com.pratik;

import java.util.*;

public class InventoryManagementSystem {

    static class Product {
        private int productId;
        private String productName;
        private int quantity;
        private double price;

        public Product(int productId,
                       String productName,
                       int quantity,
                       double price) {
            this.productId = productId;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public int getProductId() {
            return productId;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getPrice() {
            return price;
        }

        public void updateQuantity(int quantity) {
            this.quantity = quantity;
        }

        @Override
        public String toString() {
            return "ID=" + productId +
                    ", Name=" + productName +
                    ", Qty=" + quantity +
                    ", Price=" + price;
        }
    }

    static class Inventory {

        private Map<Integer, Product> products;

        public Inventory() {
            products = new HashMap<>();
        }

        public void addProduct(Product product) {

            if (products.containsKey(product.getProductId())) {
                System.out.println("Product already exists.");
                return;
            }

            products.put(product.getProductId(), product);
            System.out.println("Product added successfully.");
        }

        public void removeProduct(int productId) {

            if (products.remove(productId) != null) {
                System.out.println("Product removed.");
            } else {
                System.out.println("Product not found.");
            }
        }

        public void updateStock(int productId,
                                int quantity) {

            Product product = products.get(productId);

            if (product != null) {
                product.updateQuantity(quantity);
                System.out.println("Stock updated.");
            } else {
                System.out.println("Product not found.");
            }
        }

        public void searchProduct(int productId) {

            Product product = products.get(productId);

            if (product != null) {
                System.out.println(product);
            } else {
                System.out.println("Product not found.");
            }
        }

        public void displayInventory() {

            System.out.println("\nInventory Details:");

            for (Product product : products.values()) {
                System.out.println(product);
            }
        }
    }

    public static void main(String[] args) {

        Inventory inventory = new Inventory();

        inventory.addProduct(
                new Product(101,
                        "Laptop",
                        15,
                        65000));

        inventory.addProduct(
                new Product(102,
                        "Mouse",
                        50,
                        799));

        inventory.addProduct(
                new Product(103,
                        "Keyboard",
                        30,
                        1499));

        inventory.displayInventory();

        inventory.updateStock(102, 75);

        inventory.searchProduct(102);

        inventory.removeProduct(103);

        inventory.displayInventory();
    }
}