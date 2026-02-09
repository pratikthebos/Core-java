package com.pratik;


public class StringBasicDemo {

    public static void main(String[] args) {

        // String declaration
        String str = "Hello Java";

        // Print string
        System.out.println("Original String: " + str);

        // String length
        System.out.println("Length: " + str.length());

        // Convert to uppercase
        System.out.println("Uppercase: " + str.toUpperCase());

        // Convert to lowercase
        System.out.println("Lowercase: " + str.toLowerCase());

        // Check substring
        System.out.println("Contains 'Java': " + str.contains("Java"));

        // Replace word
        System.out.println("Replace Java with World: " + str.replace("Java", "World"));
    }
}
