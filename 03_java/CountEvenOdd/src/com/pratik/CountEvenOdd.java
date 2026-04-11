package com.pratik;

public class CountEvenOdd {

    public static void countEvenOdd(int[] nums) {

        int even = 0;
        int odd = 0;

        for (int num : nums) {
            if (num % 2 == 0) {
                even++;
            } else {
                odd++;
            }
        }

        System.out.println("Even count: " + even);
        System.out.println("Odd count: " + odd);
    }

    public static void main(String[] args) {

        int[] nums = {1, 2, 3, 4, 5, 6};

        countEvenOdd(nums);
    }
}