package com.pratik;

public class SumOfDigits {

    public static int sumDigits(int num) {

        int sum = 0;

        num = Math.abs(num); // handle negative numbers

        while (num > 0) {
            sum += num % 10;
            num = num / 10;
        }

        return sum;
    }

    public static void main(String[] args) {

        int number = 1234;

        int result = sumDigits(number);

        System.out.println("Sum of digits: " + result);
    }
}