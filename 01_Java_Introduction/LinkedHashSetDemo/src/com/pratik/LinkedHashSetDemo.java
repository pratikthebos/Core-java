package com.pratik;



import java.util.LinkedHashSet;
import java.util.Set;

public class LinkedHashSetDemo {

    public static void main(String[] args) {

        // Create LinkedHashSet
        Set<String> cities = new LinkedHashSet<>();

        // Add elements (keeps insertion order, no duplicates)
        cities.add("Mumbai");
        cities.add("Pune");
        cities.add("Delhi");
        cities.add("Mumbai"); // duplicate ignored
        cities.add("Bangalore");

        System.out.println("LinkedHashSet: " + cities);

        // Size and contains
        System.out.println("Size: " + cities.size());
        System.out.println("Contains Delhi: " + cities.contains("Delhi"));

        // Remove element
        cities.remove("Pune");
        System.out.println("After removal: " + cities);

        // Traversal
        System.out.println("Traversing LinkedHashSet:");
        for (String city : cities) {
            System.out.println(city);
        }

        // Clear
        cities.clear();
        System.out.println("After clear: " + cities);
    }
}
