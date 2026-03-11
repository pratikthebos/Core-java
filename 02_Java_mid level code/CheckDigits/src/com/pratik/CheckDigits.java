package com.pratik;

import java.util.Scanner;

public class CheckDigits {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        boolean onlyDigits = true;

        for (int i = 0; i < input.length(); i++) {

            if (!Character.isDigit(input.charAt(i))) {
                onlyDigits = false;
                break;
            }
        }

        if (onlyDigits) {
            System.out.println("The string contains only digits.");
        } else {
            System.out.println("The string contains non-digit characters.");
        }

        scanner.close();
    }
}