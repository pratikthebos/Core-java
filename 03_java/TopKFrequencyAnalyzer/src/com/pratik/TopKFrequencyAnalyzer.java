package com.pratik;

import java.util.*;

public class TopKFrequencyAnalyzer {

    public static int[] topKFrequent(int[] nums, int k) {

        Map<Integer, Integer> frequencyMap = new HashMap<>();

        // Count frequencies
        for (int num : nums) {
            frequencyMap.put(num, frequencyMap.getOrDefault(num, 0) + 1);
        }

        // Min Heap based on frequency
        PriorityQueue<Integer> minHeap =
                new PriorityQueue<>((a, b) ->
                        frequencyMap.get(a) - frequencyMap.get(b));

        for (int num : frequencyMap.keySet()) {

            minHeap.offer(num);

            if (minHeap.size() > k) {
                minHeap.poll();
            }
        }

        int[] result = new int[k];

        for (int i = k - 1; i >= 0; i--) {
            result[i] = minHeap.poll();
        }

        return result;
    }

    public static void main(String[] args) {

        int[] nums = {1,1,1,2,2,3};
        int k = 2;

        int[] result = topKFrequent(nums, k);

        System.out.print("Top K Frequent Elements: ");

        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}