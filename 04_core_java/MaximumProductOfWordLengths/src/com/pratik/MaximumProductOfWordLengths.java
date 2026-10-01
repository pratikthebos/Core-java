
package com.pratik;

import java.util.Scanner;

public class MaximumProductOfWordLengths {

    public static int maxProduct(String[] words) {
        int n = words.length;
        int[] masks = new int[n];

        // Create a bitmask for each word
        for (int i = 0; i < n; i++) {
            for (char c : words[i].toCharArray()) {
                masks[i] |= 1 << (c - 'a');
            }
        }

        int maxProduct = 0;

        // Compare every pair of words
        for (int i = 0; i < n; i++) {
            for (int j = i + 1; j < n; j++) {
                if ((masks[i] & masks[j]) == 0) {
                    int product = words[i].length() * words[j].length();
                    maxProduct = Math.max(maxProduct, product);
                }
            }
        }

        return maxProduct;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter the number of words: ");
        int n = sc.nextInt();

        String[] words = new String[n];

        System.out.println("Enter the words in lowercase:");
        for (int i = 0; i < n; i++) {
            words[i] = sc.next();
        }

        int result = maxProduct(words);

        System.out.println("Maximum product of word lengths: " + result);

        sc.close();
    }
}