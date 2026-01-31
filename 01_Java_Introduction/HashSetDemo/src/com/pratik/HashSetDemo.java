package com.pratik;


import java.util.HashSet;
import java.util.Set;

public class HashSetDemo {

    public static void main(String[] args) {

        // Create HashSet
        Set<String> fruits = new HashSet<>();

        // Add elements (duplicates are ignored)
        fruits.add("Apple");
        fruits.add("Banana");
        fruits.add("Orange");
        fruits.add("Apple");   // Duplicate

        System.out.println("HashSet: " + fruits);

        // Check size and contains
        System.out.println("Size: " + fruits.size());
        System.out.println("Contains Banana: " + fruits.contains("Banana"));

        // Remove element
        fruits.remove("Orange");
        System.out.println("After removal: " + fruits);

        // Traversal
        System.out.println("Traversing HashSet:");
        for (String fruit : fruits) {
            System.out.println(fruit);
        }

        // Clear set
        fruits.clear();
        System.out.println("After clear: " + fruits);
    }
}
