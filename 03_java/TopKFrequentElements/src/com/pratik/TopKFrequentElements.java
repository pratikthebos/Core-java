package com.pratik;

import java.util.*;

public class TopKFrequentElements {

    public static int[] topKFrequent(int[] nums, int k) {

        // Step 1: Count frequency
        HashMap<Integer, Integer> map = new HashMap<>();

        for (int num : nums) {
            map.put(num, map.getOrDefault(num, 0) + 1);
        }

        // Step 2: Min Heap based on frequency
        PriorityQueue<Integer> heap = new PriorityQueue<>(
            (a, b) -> map.get(a) - map.get(b)
        );

        for (int num : map.keySet()) {
            heap.add(num);

            if (heap.size() > k) {
                heap.poll();
            }
        }

        // Step 3: Build result
        int[] result = new int[k];
        int index = 0;

        while (!heap.isEmpty()) {
            result[index++] = heap.poll();
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1, 1, 1, 2, 2, 3};
        int k = 2;

        int[] result = topKFrequent(nums, k);

        System.out.print("Top K Frequent: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}