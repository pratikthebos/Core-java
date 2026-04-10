package com.pratik;

public class FindGCD {

    public static int gcd(int a, int b) {

        while (b != 0) {
            int temp = b;
            b = a % b;
            a = temp;
        }

        return a;
    }

    public static void main(String[] args) {

        int a = 12;
        int b = 18;

        int result = gcd(a, b);

        System.out.println("GCD is: " + result);
    }
}