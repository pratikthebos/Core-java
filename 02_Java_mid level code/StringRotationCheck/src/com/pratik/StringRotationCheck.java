package com.pratik;

import java.util.Scanner;

public class StringRotationCheck {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter first string: ");
        String str1 = scanner.nextLine();

        System.out.print("Enter second string: ");
        String str2 = scanner.nextLine();

        if (areRotations(str1, str2)) {
            System.out.println("Strings are rotations of each other.");
        } else {
            System.out.println("Strings are NOT rotations of each other.");
        }

        scanner.close();
    }

    public static boolean areRotations(String str1, String str2) {

        if (str1.length() != str2.length()) {
            return false;
        }

        String combined = str1 + str1;

        return combined.contains(str2);
    }
}