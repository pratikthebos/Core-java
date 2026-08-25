package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class OrderProcessingSystem {

    enum OrderStatus {
        CREATED,
        CONFIRMED,
        SHIPPED,
        DELIVERED,
        CANCELLED
    }

    static class Order {

        private int orderId;
        private String customerName;
        private String productName;
        private int quantity;
        private double price;
        private OrderStatus status;

        public Order(
                int orderId,
                String customerName,
                String productName,
                int quantity,
                double price) {

            this.orderId = orderId;
            this.customerName = customerName;
            this.productName = productName;
            this.quantity = quantity;
            this.price = price;
            this.status = OrderStatus.CREATED;
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

        public double getPrice() {
            return price;
        }

        public OrderStatus getStatus() {
            return status;
        }

        public void setStatus(OrderStatus status) {
            this.status = status;
        }

        public double getTotalAmount() {
            return quantity * price;
        }

        @Override
        public String toString() {

            return String.format(
                    "Order ID: %-5d | Customer: %-15s | Product: %-15s | Qty: %-3d | Total: %-10.2f | Status: %s",
                    orderId,
                    customerName,
                    productName,
                    quantity,
                    getTotalAmount(),
                    status
            );
        }
    }

    private static final List<Order> orders =
            new ArrayList<>();

    private static int nextOrderId = 1001;

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
                    createOrder(scanner);
                    break;

                case 2:
                    displayOrders();
                    break;

                case 3:
                    updateOrderStatus(scanner);
                    break;

                case 4:
                    searchOrder(scanner);
                    break;

                case 5:
                    cancelOrder(scanner);
                    break;

                case 6:
                    calculateRevenue();
                    break;

                case 7:
                    displayStatistics();
                    break;

                case 8:
                    System.out.println(
                            "\nOrder Processing System closed."
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
                "             ORDER PROCESSING SYSTEM"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println("1. Create Order");
        System.out.println("2. Display Orders");
        System.out.println("3. Update Order Status");
        System.out.println("4. Search Order");
        System.out.println("5. Cancel Order");
        System.out.println("6. Calculate Revenue");
        System.out.println("7. Display Statistics");
        System.out.println("8. Exit");

        System.out.println(
                "=============================================="
        );
    }

    private static void loadSampleOrders() {

        orders.add(
                new Order(
                        nextOrderId++,
                        "Rahul",
                        "Laptop",
                        1,
                        75000
                )
        );

        orders.add(
                new Order(
                        nextOrderId++,
                        "Priya",
                        "Keyboard",
                        2,
                        2500
                )
        );

        orders.add(
                new Order(
                        nextOrderId++,
                        "Amit",
                        "Monitor",
                        2,
                        15000
                )
        );
    }

    private static void createOrder(Scanner scanner) {

        System.out.println("\n--- Create Order ---");

        System.out.print("Enter Customer Name: ");
        String customerName =
                scanner.nextLine().trim();

        System.out.print("Enter Product Name: ");
        String productName =
                scanner.nextLine().trim();

        System.out.print("Enter Quantity: ");
        int quantity = scanner.nextInt();

        System.out.print("Enter Product Price: ");
        double price = scanner.nextDouble();
        scanner.nextLine();

        if (customerName.isEmpty()
                || productName.isEmpty()) {

            System.out.println(
                    "Customer and product names are required."
            );

            return;
        }

        if (quantity <= 0 || price < 0) {

            System.out.println(
                    "Invalid quantity or price."
            );

            return;
        }

        Order order = new Order(
                nextOrderId++,
                customerName,
                productName,
                quantity,
                price
        );

        orders.add(order);

        System.out.println(
                "\nOrder created successfully."
        );

        System.out.println(order);
    }

    private static void displayOrders() {

        System.out.println("\n--- All Orders ---");

        if (orders.isEmpty()) {

            System.out.println(
                    "No orders found."
            );

            return;
        }

        for (Order order : orders) {
            System.out.println(order);
        }

        System.out.println(
                "\nTotal Orders: " + orders.size()
        );
    }

    private static void updateOrderStatus(
            Scanner scanner) {

        System.out.println(
                "\n--- Update Order Status ---"
        );

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = findOrder(orderId);

        if (order == null) {

            System.out.println(
                    "Order not found."
            );

            return;
        }

        System.out.println(
                "Current Status: "
                        + order.getStatus()
        );

        System.out.println(
                "\nAvailable Status:"
        );

        for (OrderStatus status : OrderStatus.values()) {
            System.out.println(
                    "- " + status
            );
        }

        System.out.print(
                "Enter new status: "
        );

        String input =
                scanner.nextLine()
                        .trim()
                        .toUpperCase();

        try {

            OrderStatus newStatus =
                    OrderStatus.valueOf(input);

            if (newStatus == OrderStatus.CANCELLED) {

                cancelOrder(order);

            } else {

                order.setStatus(newStatus);

                System.out.println(
                        "Order status updated successfully."
                );
            }

        } catch (IllegalArgumentException e) {

            System.out.println(
                    "Invalid order status."
            );
        }
    }

    private static void searchOrder(
            Scanner scanner) {

        System.out.println(
                "\n--- Search Order ---"
        );

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = findOrder(orderId);

        if (order == null) {

            System.out.println(
                    "Order not found."
            );

            return;
        }

        System.out.println(
                "\nOrder Found:"
        );

        System.out.println(order);
    }

    private static void cancelOrder(
            Scanner scanner) {

        System.out.println(
                "\n--- Cancel Order ---"
        );

        System.out.print("Enter Order ID: ");
        int orderId = scanner.nextInt();
        scanner.nextLine();

        Order order = findOrder(orderId);

        if (order == null) {

            System.out.println(
                    "Order not found."
            );

            return;
        }

        cancelOrder(order);
    }

    private static void cancelOrder(Order order) {

        if (order.getStatus()
                == OrderStatus.DELIVERED) {

            System.out.println(
                    "Delivered orders cannot be cancelled."
            );

            return;
        }

        if (order.getStatus()
                == OrderStatus.CANCELLED) {

            System.out.println(
                    "Order is already cancelled."
            );

            return;
        }

        order.setStatus(
                OrderStatus.CANCELLED
        );

        System.out.println(
                "Order "
                        + order.getOrderId()
                        + " cancelled successfully."
        );
    }

    private static Order findOrder(int orderId) {

        for (Order order : orders) {

            if (order.getOrderId() == orderId) {
                return order;
            }
        }

        return null;
    }

    private static void calculateRevenue() {

        double revenue = 0;

        for (Order order : orders) {

            if (order.getStatus()
                    != OrderStatus.CANCELLED) {

                revenue += order.getTotalAmount();
            }
        }

        System.out.println(
                "\nCurrent Revenue: ₹"
                        + String.format("%.2f", revenue)
        );
    }

    private static void displayStatistics() {

        int created = 0;
        int confirmed = 0;
        int shipped = 0;
        int delivered = 0;
        int cancelled = 0;

        for (Order order : orders) {

            switch (order.getStatus()) {

                case CREATED:
                    created++;
                    break;

                case CONFIRMED:
                    confirmed++;
                    break;

                case SHIPPED:
                    shipped++;
                    break;

                case DELIVERED:
                    delivered++;
                    break;

                case CANCELLED:
                    cancelled++;
                    break;
            }
        }

        System.out.println(
                "\n=============================================="
        );

        System.out.println(
                "             ORDER STATISTICS"
        );

        System.out.println(
                "=============================================="
        );

        System.out.println(
                "Total Orders : " + orders.size()
        );

        System.out.println(
                "Created      : " + created
        );

        System.out.println(
                "Confirmed    : " + confirmed
        );

        System.out.println(
                "Shipped      : " + shipped
        );

        System.out.println(
                "Delivered    : " + delivered
        );

        System.out.println(
                "Cancelled    : " + cancelled
        );

        System.out.println(
                "=============================================="
        );
    }
}