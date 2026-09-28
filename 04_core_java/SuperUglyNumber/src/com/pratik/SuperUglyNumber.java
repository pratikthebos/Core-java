package com.pratik;

import java.util.Scanner;

public class SuperUglyNumber {

    public static int nthSuperUglyNumber(int n, int[] primes) {
        int k = primes.length;

        int[] ugly = new int[n];
        int[] index = new int[k];
        long[] next = new long[k];

        ugly[0] = 1;

        for (int i = 0; i < k; i++) {
            next[i] = primes[i];
        }

        for (int i = 1; i < n; i++) {
            long min = Long.MAX_VALUE;

            for (int j = 0; j < k; j++) {
                min = Math.min(min, next[j]);
            }

            ugly[i] = (int) min;

            for (int j = 0; j < k; j++) {
                if (next[j] == min) {
                    index[j]++;
                    next[j] = (long) ugly[index[j]] * primes[j];
                }
            }
        }

        return ugly[n - 1];
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter n: ");
        int n = sc.nextInt();

        System.out.print("Enter number of primes: ");
        int k = sc.nextInt();

        int[] primes = new int[k];

        System.out.println("Enter the prime numbers:");
        for (int i = 0; i < k; i++) {
            primes[i] = sc.nextInt();
        }

        int result = nthSuperUglyNumber(n, primes);

        System.out.println("The " + n + "th Super Ugly Number is: " + result);

        sc.close();
    }
}