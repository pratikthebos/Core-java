package com.pratik;

import java.util.Scanner;

public class NimGame {

    public static boolean canWinNim(int n) {
        return n % 4 != 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter number of stones: ");
        int n = sc.nextInt();

        if (canWinNim(n)) {
            System.out.println("You can win!");
        } else {
            System.out.println("You cannot win.");
        }

        sc.close();
    }
}