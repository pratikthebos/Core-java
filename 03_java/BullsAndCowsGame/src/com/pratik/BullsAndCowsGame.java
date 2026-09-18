package com.pratik;

import java.util.Scanner;

public class BullsAndCowsGame {

    static String getHint(String secret, String guess) {
        int bulls = 0;
        int cows = 0;
        int[] count = new int[10];

        for (int i = 0; i < secret.length(); i++) {
            if (secret.charAt(i) == guess.charAt(i)) {
                bulls++;
            } else {
                count[secret.charAt(i) - '0']++;
            }
        }

        for (int i = 0; i < guess.length(); i++) {
            if (secret.charAt(i) != guess.charAt(i)) {
                int digit = guess.charAt(i) - '0';

                if (count[digit] > 0) {
                    cows++;
                    count[digit]--;
                }
            }
        }

        return bulls + "A" + cows + "B";
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter secret: ");
        String secret = sc.next();

        System.out.print("Enter guess: ");
        String guess = sc.next();

        System.out.println("Hint: " + getHint(secret, guess));

        sc.close();
    }
}