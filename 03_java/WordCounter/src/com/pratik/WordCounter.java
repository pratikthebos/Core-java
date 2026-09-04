package com.pratik;

import java.util.Scanner;

public class WordCounter {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String text = sc.nextLine().trim();

        if (text.isEmpty()) {
            System.out.println("Word Count: 0");
        } else {
            String[] words = text.split("\\s+");
            System.out.println("Word Count: " + words.length);
        }

        sc.close();
    }
}