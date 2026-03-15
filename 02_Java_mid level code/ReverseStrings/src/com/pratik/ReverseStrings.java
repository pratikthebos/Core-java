package com.pratik;

public class ReverseStrings {

    public static void main(String[] args) {

        String str = "Hello Java";
        String reverse = "";

        for (int i = str.length() - 1; i >= 0; i--) {
            reverse += str.charAt(i);
        }

        System.out.println("Original: " + str);
        System.out.println("Reversed: " + reverse);
    }
}