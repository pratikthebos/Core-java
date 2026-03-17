package com.pratik;

public class MissingNumber {

    public static int missingNumber(int[] nums) {

        int n = nums.length;

        // Sum of first n natural numbers
        int expectedSum = n * (n + 1) / 2;

        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        return expectedSum - actualSum;
    }

    public static void main(String[] args) {

        int[] nums = {3, 0, 1};

        int result = missingNumber(nums);

        System.out.println("Missing number: " + result);
    }
}