package com.pratik;

public class MissingNumber {

    public static void moveZeroes(int[] nums) {

        int index = 0;

        // Move non-zero elements forward
        for (int i = 0; i < nums.length; i++) {
            if (nums[i] != 0) {
                nums[index++] = nums[i];
            }
        }

        // Fill remaining with zeroes
        while (index < nums.length) {
            nums[index++] = 0;
        }
    }

    public static void main(String[] args) {

        int[] nums = {0, 1, 0, 3, 12};

        moveZeroes(nums);

        System.out.print("Result: ");
        for (int num : nums) {
            System.out.print(num + " ");
        }
    }
}

public class MissingNumber {

    public static int missingNumber(int[] nums) {
        int n = nums.length;

        // Sum of numbers from 0 to n
        int expectedSum = n * (n + 1) / 2;

        // Sum of numbers present in the array
        int actualSum = 0;

        for (int num : nums) {
            actualSum += num;
        }

        // Difference is the missing number
        return expectedSum - actualSum;
    }

    public static void main(String[] args) {
        int[] nums = {3, 0, 1};

        int result = missingNumber(nums);

        System.out.println("Missing number: " + result);
    }
}
