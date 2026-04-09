package com.pratik;

public class ArmstrongNumber {

    public static boolean isArmstrong(int num) {

        int original = num;
        int sum = 0;

        int digits = String.valueOf(num).length();

        while (num > 0) {
            int digit = num % 10;
            sum += Math.pow(digit, digits);
            num = num / 10;
        }

        return sum == original;
    }

    public static void main(String[] args) {

        int number = 153;

        boolean result = isArmstrong(number);

        System.out.println("Is Armstrong: " + result);
    }
}