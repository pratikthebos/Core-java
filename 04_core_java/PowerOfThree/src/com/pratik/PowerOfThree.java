package com.pratik;

import java.util.Scanner;

public class PowerOfThree {

    public static boolean isPowerOfThree(int n) {
        return n > 0 && 1162261467 % n == 0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter a number: ");
        int n = sc.nextInt();

        boolean result = isPowerOfThree(n);

        System.out.println("Is " + n + " a power of three? " + result);

        sc.close();
    }
}