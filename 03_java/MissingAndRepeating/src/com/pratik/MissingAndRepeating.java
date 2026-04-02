package com.pratik;

public class MissingAndRepeating {

    public static void findNumbers(int[] nums) {

        int n = nums.length;
        int repeating = -1, missing = -1;

        // Step 1: Mark visited
        for (int i = 0; i < n; i++) {

            int index = Math.abs(nums[i]) - 1;

            if (nums[index] < 0) {
                repeating = Math.abs(nums[i]);
            } else {
                nums[index] = -nums[index];
            }
        }

        // Step 2: Find missing
        for (int i = 0; i < n; i++) {
            if (nums[i] > 0) {
                missing = i + 1;
                break;
            }
        }

        System.out.println("Repeating: " + repeating);
        System.out.println("Missing: " + missing);
    }

    public static void main(String[] args) {

        int[] nums = {4,3,6,2,1,1};

        findNumbers(nums);
    }
}