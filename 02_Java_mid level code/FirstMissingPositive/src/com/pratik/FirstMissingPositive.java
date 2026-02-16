package com.pratik;

import java.util.Arrays;

public class FirstMissingPositive {

    public static void main(String[] args) {

        int[] nums = {3, 4, -1, 1};

        System.out.println("Array: " + Arrays.toString(nums));

        int result = firstMissingPositive(nums);

        System.out.println("First Missing Positive: " + result);
    }

    public static int firstMissingPositive(int[] nums) {

        int n = nums.length;

        // Place each number in correct index
        for (int i = 0; i < n; i++) {
            while (nums[i] > 0 && nums[i] <= n
                    && nums[nums[i] - 1] != nums[i]) {

                int temp = nums[i];
                nums[i] = nums[temp - 1];
                nums[temp - 1] = temp;
            }
        }

        // Find missing positive
        for (int i = 0; i < n; i++) {
            if (nums[i] != i + 1) {
                return i + 1;
            }
        }

        return n + 1;
    }
}
