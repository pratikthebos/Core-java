package com.pratik;

import java.util.*;

public class LongestIncreasingSubsequence {

    public static int lengthOfLIS(int[] nums) {

        List<Integer> list = new ArrayList<>();

        for (int num : nums) {

            int index = Collections.binarySearch(list, num);

            if (index < 0) {
                index = -(index + 1);
            }

            if (index == list.size()) {
                list.add(num);
            } else {
                list.set(index, num);
            }
        }

        return list.size();
    }

    public static void main(String[] args) {

        int[] nums = {10,9,2,5,3,7,101,18};

        int result = lengthOfLIS(nums);

        System.out.println("LIS length: " + result);
    }
}
