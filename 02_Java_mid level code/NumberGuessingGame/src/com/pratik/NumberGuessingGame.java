package com.pratik;


import java.util.Random;
import java.util.Scanner;

public class NumberGuessingGame {

    public static void main(String[] args) {

        Random random = new Random();
        Scanner scanner = new Scanner(System.in);

        int numberToGuess = random.nextInt(100) + 1;
        int guess = 0;
        int attempts = 0;

        System.out.println("🎯 Welcome to Number Guessing Game!");
        System.out.println("Guess a number between 1 and 100");

        while (guess != numberToGuess) {

            System.out.print("Enter your guess: ");
            guess = scanner.nextInt();
            attempts++;

            if (guess < numberToGuess) {
                System.out.println("Too low! Try again.");
            }
            else if (guess > numberToGuess) {
                System.out.println("Too high! Try again.");
            }
            else {
                System.out.println("🎉 Correct! You guessed in " + attempts + " attempts.");
            }
        }

        scanner.close();
    }
}