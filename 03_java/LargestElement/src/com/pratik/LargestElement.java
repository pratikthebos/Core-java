package com.pratik;

public class LargestElement {

    public static int findLargest(int[] nums) {

        int max = nums[0];

        for (int i = 1; i < nums.length; i++) {
            if (nums[i] > max) {
                max = nums[i];
            }
        }

        return max;
    }

    public static void main(String[] args) {

        int[] nums = {10, 25, 7, 99, 56};

        int result = findLargest(nums);

        System.out.println("Largest element: " + result);
    }
}