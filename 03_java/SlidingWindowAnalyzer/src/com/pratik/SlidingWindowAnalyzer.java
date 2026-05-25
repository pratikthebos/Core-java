package com.pratik;

import java.util.*;

public class SlidingWindowAnalyzer {

    public static int[] maxSlidingWindow(
            int[] nums,
            int k) {

        if (nums == null ||
                nums.length == 0) {
            return new int[0];
        }

        int n = nums.length;
        int[] result =
                new int[n - k + 1];

        Deque<Integer> deque =
                new LinkedList<>();

        for (int i = 0; i < n; i++) {

            // Remove out of window
            while (!deque.isEmpty() &&
                    deque.peekFirst()
                            <= i - k) {

                deque.pollFirst();
            }

            // Remove smaller elements
            while (!deque.isEmpty() &&
                    nums[deque.peekLast()]
                            < nums[i]) {

                deque.pollLast();
            }

            deque.offerLast(i);

            // Window formed
            if (i >= k - 1) {

                result[i - k + 1] =
                        nums[deque.peekFirst()];
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {
                1,3,-1,-3,5,3,6,7
        };

        int k = 3;

        int[] result =
                maxSlidingWindow(
                        nums, k);

        System.out.print(
                "Sliding Window Maximum: ");

        for (int num : result) {
            System.out.print(
                    num + " ");
        }
    }
}