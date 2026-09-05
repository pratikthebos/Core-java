package com.pratik;

import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);
        Random random = new Random();

        int number = random.nextInt(100) + 1;
        int guess;

        System.out.println("Guess a number between 1 and 100");

        do {
            System.out.print("Enter guess: ");
            guess = sc.nextInt();

            if (guess < number)
                System.out.println("Too Low!");
            else if (guess > number)
                System.out.println("Too High!");
            else
                System.out.println("Correct!");

        } while (guess != number);

        sc.close();
    }
}