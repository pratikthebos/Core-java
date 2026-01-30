package com.pratik;



import java.util.Vector;
import java.util.Enumeration;

public class VectorDemo{

    public static void main(String[] args) {

        // Create Vector
        Vector<String> names = new Vector<>();

        // Add elements
        names.add("Rahul");
        names.add("Amit");
        names.add("Sneha");
        names.add("Priya");

        System.out.println("Initial Vector: " + names);

        // Add element at index
        names.add(2, "Neha");
        System.out.println("After adding at index 2: " + names);

        // Remove element
        names.remove("Amit");
        System.out.println("After removal: " + names);

        // Access elements
        System.out.println("First: " + names.firstElement());
        System.out.println("Last: " + names.lastElement());

        // Traversal using Enumeration (legacy)
        System.out.println("Using Enumeration:");
        Enumeration<String> e = names.elements();
        while (e.hasMoreElements()) {
            System.out.println(e.nextElement());
        }

        // Traversal using for-each
        System.out.println("Using for-each:");
        for (String name : names) {
            System.out.println(name);
        }

        // Check size & contains
        System.out.println("Size: " + names.size());
        System.out.println("Contains Rahul: " + names.contains("Rahul"));
    }
}
