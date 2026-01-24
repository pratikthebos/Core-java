package com.pratik;



public class StringClass {

    public static void main(String[] args) {

        // 1️⃣ String creation
        String s1 = "Hello";                  // String literal (String pool)
        String s2 = new String("Hello");      // Using new keyword (Heap)

        // 2️⃣ Immutability
        s1.concat(" World");
        System.out.println("After concat (s1): " + s1); // Still "Hello"

        s1 = s1.concat(" World");
        System.out.println("After reassignment (s1): " + s1); // "Hello World"

        // 3️⃣ Common String methods
        String str = "Java Programming";

        System.out.println("Length: " + str.length());
        System.out.println("Char at index 2: " + str.charAt(2));
        System.out.println("Substring: " + str.substring(5));
        System.out.println("Uppercase: " + str.toUpperCase());
        System.out.println("Lowercase: " + str.toLowerCase());
        System.out.println("Replace: " + str.replace("Java", "Core Java"));
        System.out.println("Contains 'Pro': " + str.contains("Pro"));
        System.out.println("Starts with 'Java': " + str.startsWith("Java"));
        System.out.println("Ends with 'ing': " + str.endsWith("ing"));

        // 4️⃣ String comparison
        String a = "Test";
        String b = "Test";
        String c = new String("Test");

        System.out.println("a == b : " + (a == b));         // true (same reference)
        System.out.println("a == c : " + (a == c));         // false (different object)
        System.out.println("a.equals(c): " + a.equals(c)); // true (same content)

        // 5️⃣ StringBuilder for performance
        StringBuilder sb = new StringBuilder("Hello");
        sb.append(" ");
        sb.append("Java");
        System.out.println("StringBuilder result: " + sb);
    }
}
