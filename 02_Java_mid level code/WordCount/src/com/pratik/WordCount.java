package com.pratik;

import java.util.Scanner;

public class WordCount {

    public static void main(String[] args) {

        Scanner scanner = new Scanner(System.in);

        System.out.print("Enter a sentence: ");
        String sentence = scanner.nextLine();

        int wordCount = countWords(sentence);

        System.out.println("Number of words: " + wordCount);

        scanner.close();
    }

    public static int countWords(String str) {

        if (str == null || str.trim().isEmpty()) {
            return 0;
        }

        String[] words = str.trim().split("\\s+");

        return words.length;
    }
}