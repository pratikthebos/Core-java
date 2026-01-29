package com.pratik;



import java.util.LinkedList;

public class LinkedListDemo {

    public static void main(String[] args) {

        // Create LinkedList
        LinkedList<String> cities = new LinkedList<>();

        // Add elements
        cities.add("Mumbai");
        cities.add("Pune");
        cities.add("Delhi");
        cities.add("Bangalore");

        System.out.println("Initial List: " + cities);

        // Add at first and last
        cities.addFirst("Chennai");
        cities.addLast("Hyderabad");

        System.out.println("After addFirst & addLast: " + cities);

        // Access elements
        System.out.println("First city: " + cities.getFirst());
        System.out.println("Last city: " + cities.getLast());

        // Remove elements
        cities.removeFirst();
        cities.removeLast();
        cities.remove("Pune");

        System.out.println("After removals: " + cities);

        // Traversal
        System.out.println("Traversing list:");
        for (String city : cities) {
            System.out.println(city);
        }

        // Check size & contains
        System.out.println("Size: " + cities.size());
        System.out.println("Contains Delhi: " + cities.contains("Delhi"));
    }
}
