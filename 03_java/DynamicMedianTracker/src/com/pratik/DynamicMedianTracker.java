package com.pratik;

import java.util.*;

public class DynamicMedianTracker {

    static class MedianFinder {

        // Max Heap (left side)
        private PriorityQueue<Integer> maxHeap;

        // Min Heap (right side)
        private PriorityQueue<Integer> minHeap;

        public MedianFinder() {

            maxHeap = new PriorityQueue<>(
                    Collections.reverseOrder());

            minHeap = new PriorityQueue<>();
        }

        // Add number
        public void addNum(int num) {

            maxHeap.offer(num);

            // Balance heaps
            minHeap.offer(maxHeap.poll());

            if (minHeap.size() > maxHeap.size()) {
                maxHeap.offer(minHeap.poll());
            }
        }

        // Find median
        public double findMedian() {

            if (maxHeap.size() > minHeap.size()) {
                return maxHeap.peek();
            }

            return (maxHeap.peek() +
                    minHeap.peek()) / 2.0;
        }
    }

    public static void main(String[] args) {

        MedianFinder medianFinder =
                new MedianFinder();

        medianFinder.addNum(1);
        System.out.println(
                medianFinder.findMedian());

        medianFinder.addNum(2);
        System.out.println(
                medianFinder.findMedian());

        medianFinder.addNum(3);
        System.out.println(
                medianFinder.findMedian());
    }
}