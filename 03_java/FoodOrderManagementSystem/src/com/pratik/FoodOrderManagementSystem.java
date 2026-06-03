package com.pratik;

import java.util.*;

public class FoodOrderManagementSystem {

    static class MenuItem {

        private int itemId;
        private String itemName;
        private double price;

        public MenuItem(int itemId,
                        String itemName,
                        double price) {

            this.itemId = itemId;
            this.itemName = itemName;
            this.price = price;
        }

        public int getItemId() {
            return itemId;
        }

        public double getPrice() {
            return price;
        }

        @Override
        public String toString() {
            return itemId + " - "
                    + itemName
                    + " : ₹" + price;
        }
    }

    static class Order {

        private int orderId;
        private List<MenuItem> items;
        private String status;

        public Order(int orderId) {

            this.orderId = orderId;
            this.items = new ArrayList<>();
            this.status = "PLACED";
        }

        public void addItem(MenuItem item) {
            items.add(item);
        }

        public double calculateBill() {

            double total = 0;

            for (MenuItem item : items) {
                total += item.getPrice();
            }

            return total;
        }

        public void updateStatus(String status) {
            this.status = status;
        }

        @Override
        public String toString() {

            return "Order ID: "
                    + orderId
                    + ", Items: "
                    + items.size()
                    + ", Bill: ₹"
                    + calculateBill()
                    + ", Status: "
                    + status;
        }
    }

    static class FoodService {

        private Map<Integer, MenuItem> menu;
        private Map<Integer, Order> orders;

        public FoodService() {

            menu = new HashMap<>();
            orders = new HashMap<>();
        }

        public void addMenuItem(
                MenuItem item) {

            menu.put(
                    item.getItemId(),
                    item
            );
        }

        public void showMenu() {

            System.out.println("\nMenu:");

            for (MenuItem item
                    : menu.values()) {

                System.out.println(item);
            }
        }

        public void placeOrder(
                int orderId,
                int[] itemIds) {

            Order order =
                    new Order(orderId);

            for (int itemId : itemIds) {

                MenuItem item =
                        menu.get(itemId);

                if (item != null) {
                    order.addItem(item);
                }
            }

            orders.put(orderId, order);

            System.out.println(
                    "Order Placed: "
                            + orderId);
        }

        public void updateOrderStatus(
                int orderId,
                String status) {

            Order order =
                    orders.get(orderId);

            if (order != null) {

                order.updateStatus(status);

                System.out.println(
                        "Order Updated");
            }
        }

        public void viewOrder(
                int orderId) {

            Order order =
                    orders.get(orderId);

            if (order != null) {
                System.out.println(order);
            }
        }
    }

    public static void main(String[] args) {

        FoodService service =
                new FoodService();

        service.addMenuItem(
                new MenuItem(
                        1,
                        "Pizza",
                        299));

        service.addMenuItem(
                new MenuItem(
                        2,
                        "Burger",
                        149));

        service.addMenuItem(
                new MenuItem(
                        3,
                        "Cold Drink",
                        49));

        service.showMenu();

        service.placeOrder(
                1001,
                new int[]{1, 2, 3});

        service.viewOrder(1001);

        service.updateOrderStatus(
                1001,
                "DELIVERED");

        service.viewOrder(1001);
    }
}