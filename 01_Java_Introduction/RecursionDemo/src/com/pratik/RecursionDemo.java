package com.pratik;



public class RecursionDemo {

    // 1️⃣ Factorial using recursion
    static int factorial(int n) {
        if (n == 0 || n == 1) {
            return 1;  // base case
        }
        return n * factorial(n - 1); // recursive call
    }

    // 2️⃣ Fibonacci using recursion
    static int fibonacci(int n) {
        if (n == 0) return 0;
        if (n == 1) return 1;
        return fibonacci(n - 1) + fibonacci(n - 2);
    }

    // 3️⃣ Sum of first n numbers
    static int sum(int n) {
        if (n == 0) return 0;
        return n + sum(n - 1);
    }

    public static void main(String[] args) {

        int num = 5;

        System.out.println("Factorial of " + num + ": " + factorial(num));
        System.out.println("Fibonacci of " + num + ": " + fibonacci(num));
        System.out.println("Sum of first " + num + " numbers: " + sum(num));
    }
}
