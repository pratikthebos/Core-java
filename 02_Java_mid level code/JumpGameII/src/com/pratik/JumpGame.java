package com.pratik;

import java.util.Arrays;

public class JumpGame {

    public static void main(String[] args) {

        int[] nums = {2, 3, 1, 1, 4};

        System.out.println("Array: " + Arrays.toString(nums));

        int result = jump(nums);

        System.out.println("Minimum jumps to reach end: " + result);
    }

    public static int jump(int[] nums) {

        int jumps = 0;
        int currentEnd = 0;
        int farthest = 0;

        for (int i = 0; i < nums.length - 1; i++) {

            farthest = Math.max(farthest, i + nums[i]);

            if (i == currentEnd) {
                jumps++;
                currentEnd = farthest;
            }
        }

        return jumps;
    }
}