package com.pratik;

import java.time.Year;
import java.util.Scanner;

public class AgeCalculator {

    public static void main(String[] args) {

        Scanner sc = new Scanner(System.in);

        System.out.print("Enter your birth year: ");
        int birthYear = sc.nextInt();

        int currentYear = Year.now().getValue();
        int age = currentYear - birthYear;

        System.out.println("Your age is: " + age);

        sc.close();
    }
}