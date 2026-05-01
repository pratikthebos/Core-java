package com.pratik;

public class MaximumProductSubarray {
	public class MaximumProductSubarray {

	    public static int maxProduct(int[] nums) {

	        int max = nums[0];
	        int min = nums[0];
	        int result = nums[0];

	        for (int i = 1; i < nums.length; i++) {

	            int curr = nums[i];

	            if (curr < 0) {
	                // swap max and min
	                int temp = max;
	                max = min;
	                min = temp;
	            }

	            max = Math.max(curr, max * curr);
	            min = Math.min(curr, min * curr);

	            result = Math.max(result, max);
	        }

	        return result;
	    }

	    public static void main(String[] args) {

	        int[] nums = {2, 3, -2, 4};

	        int result = maxProduct(nums);

	        System.out.println("Max product: " + result);
	    }
	}