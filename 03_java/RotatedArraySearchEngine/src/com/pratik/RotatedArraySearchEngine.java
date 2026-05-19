package com.pratik;

public class RotatedArraySearchEngine {

    public static int search(int[] nums, int target) {

        int left = 0;
        int right = nums.length - 1;

        while (left <= right) {

            int mid = left + (right - left) / 2;

            // Target found
            if (nums[mid] == target) {
                return mid;
            }

            // Left half sorted
            if (nums[left] <= nums[mid]) {

                if (target >= nums[left] &&
                        target < nums[mid]) {
                    right = mid - 1;
                } else {
                    left = mid + 1;
                }
            }

            // Right half sorted
            else {

                if (target > nums[mid] &&
                        target <= nums[right]) {
                    left = mid + 1;
                } else {
                    right = mid - 1;
                }
            }
        }

        return -1;
    }

    public static void main(String[] args) {

        int[] nums = {4,5,6,7,0,1,2};
        int target = 0;

        int result = search(nums, target);

        System.out.println("Target Index: " + result);
    }
}