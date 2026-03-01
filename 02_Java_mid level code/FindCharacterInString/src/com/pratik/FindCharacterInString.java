package com.pratik;

import java.util.Scanner;

public class FindCharacterInString {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a string: ");
        String input = scanner.nextLine();

        System.out.print("Enter character to find: ");
        char target = scanner.next().charAt(0);

        int index = input.indexOf(target);

        if (index != -1) {
            System.out.println("Character '" + target + "' found at index: " + index);
        } else {
            System.out.println("Character not found.");
        }

        scanner.close();
    }
}