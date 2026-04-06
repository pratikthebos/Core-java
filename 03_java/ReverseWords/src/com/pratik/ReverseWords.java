package com.pratik;

public class ReverseWords {

    public static String reverseWords(String str) {

        String[] words = str.trim().split("\\s+");

        StringBuilder result = new StringBuilder();

        for (int i = words.length - 1; i >= 0; i--) {
            result.append(words[i]);
            if (i != 0) {
                result.append(" ");
            }
        }

        return result.toString();
    }

    public static void main(String[] args) {

        String input = "Hello World Java";

        String output = reverseWords(input);

        System.out.println("Reversed Sentence: " + output);
    }
}