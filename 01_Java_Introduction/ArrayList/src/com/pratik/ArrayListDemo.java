package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Collections;

public class ArrayListDemo {

    public static void main(String[] args) {

        // Create List using ArrayList
        List<String> languages = new ArrayList<>();

        languages.add("Java");
        languages.add("Python");
        languages.add("C++");
        languages.add("JavaScript");

        System.out.println("Initial List: " + languages);

        System.out.println("First element: " + languages.get(0));

        languages.set(2, "Go");
        System.out.println("After update: " + languages);

        languages.remove("Python");
        System.out.println("After removal: " + languages);

        System.out.println("Size: " + languages.size());
        System.out.println("Contains Java: " + languages.contains("Java"));

        System.out.println("Traversing list:");
        for (String lang : languages) {
            System.out.println(lang);
        }

        Collections.sort(languages);
        System.out.println("Sorted List: " + languages);

        languages.clear();
        System.out.println("List after clear: " + languages);
    }
}
