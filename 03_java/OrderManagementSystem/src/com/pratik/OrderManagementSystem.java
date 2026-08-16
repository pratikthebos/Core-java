package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderManagementSystem {

    static class Order {

        private int orderId;
        private String customerName;
        private String productName;
        private int quantity;
        private double price;

        public Order(int orderId, String customerName,
                     String productName, int quantity, double price) {

            this.orderId = orderId;
            this.customerName = customerName;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
        }

        public int getOrderId() {
            return orderId;
        }

        public String getCustomerName() {
            return customerName;
        }

        public String getProductName() {
            return productName;
        }

        public int getQuantity() {
            return quantity;
        }

        public double getTotalAmount() {
            return quantity * price;
        }

        @Override
        public String toString() {

            return String.format(
                    "Order ID: %-5d | Customer: %-12s | Product: %-15s | Quantity: %-3d | Total: %.2f",
                    orderId,
                    customerName,
                    productName,
                    quantity,
                    getTotalAmount()
            );
        }
    }

    private static final List<Order> orders = new ArrayList<>();

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        loadSampleOrders();

        while (true) {

            displayMenu();

            System.out.print("Enter your choice: ");
            int choice = scanner.nextInt();
            scanner.nextLine();

            switch (choice) {

                case 1:
                    addOrder(scanner);
                    break;

                case 2:
                    displayOrders();
                    break;

                case 3:
                    searchOrder(scanner);
                    break;

                case 4:
                    calculateTotalSales();
                    break;

                case 5:
                    findHighestOrder();
                    break;

                case 6:
                    deleteOrder(scanner);
                    break;

                case 7:
                    System.out.println("\nOrder Management System closed.");
                    scanner.close();
                    return;

                default:
                    System.out.println("\nInvalid choice.");
            }
        }
    }

    private static void displayMenu() {

        System.out.println("\n============================================");
        System.out.println("          ORDER MANAGEMENT SYSTEM");
        System.out.println("============================================");
        System.out.println("1. Add Order");
        System.out.println("2. Display All Orders");
        System.out.println("3. Search Order");
        System.out.println("4. Calculate Total Sales");
        System.out.println("5. Find Highest Order");
        System.out.println("6. Delete Order");
        System.out.println("7. Exit");
        System.out.println("============================================");
    }

    private static void loadSampleOrders() {

        orders.add(
                new Order(1001, "Rahul", "Laptop", 1, 75000)
        );

        orders.add(
                new Order(1002, "Priya", "Keyboard", 2, 2500)
        );

        orders.add(
                new Order(1003, "Amit", "Monitor", 2, 15000)
        );

        orders.add(
                new Order(1004, "Sneha", "Mouse", 3, 1200)
        );
    }

    private static void addOrder(Scanner scanner) {

        System.out.println("\n--- Add New Order ---");

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        if (findOrderById(orderId) != null) {
            System.out.println("Order ID already exists.");
            return;
        }

        System.out.print("Enter Customer Name: ");
        String customerName = scanner.nextLine();

        System.out.print("Enter Product Name: ");
        String productName = scanner.nextLine();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();

        if (quantity <= 0 || price <= 0) {
            System.out.println("Quantity and price must be greater than zero.");
            return;
        }

        orders.add(
                new Order(
                        orderId,
                        customerName,
                        productName,
                        quantity,
                        price
                )
        );

        System.out.println("Order added successfully.");
    }

    private static void displayOrders() {

        System.out.println("\n--- All Orders ---");

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }

        System.out.println("--------------------------------------------");
        System.out.println("Total Orders: " + orders.size());
    }

    private static void searchOrder(Scanner scanner) {

        System.out.print("\nEnter Order ID: ");
        int orderId = scanner.nextInt();

        Order order = findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        System.out.println("\n--- Order Found ---");
        System.out.println(order);
    }

    private static Order findOrderById(int orderId) {

        for (Order order : orders) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    private static void calculateTotalSales() {

        double totalSales = 0;

        for (Order order : orders) {
            totalSales += order.getTotalAmount();
        }

        System.out.printf(
                "\nTotal Sales: %.2f%n",
                totalSales
        );
    }

    private static void findHighestOrder() {

        if (orders.isEmpty()) {
            System.out.println("No orders available.");
            return;
        }

        Order highestOrder = orders.get(0);

        for (Order order : orders) {

            if (order.getTotalAmount()
                    > highestOrder.getTotalAmount()) {

                highestOrder = order;
            }
        }

        System.out.println("\n--- Highest Order ---");
        System.out.println(highestOrder);
    }

    private static void deleteOrder(Scanner scanner) {

        System.out.print("\nEnter Order ID to delete: ");
        int orderId = scanner.nextInt();

        Order order = findOrderById(orderId);

        if (order == null) {
            System.out.println("Order not found.");
            return;
        }

        orders.remove(order);

        System.out.println(
                "Order " + orderId + " deleted successfully."
        );
    }
}