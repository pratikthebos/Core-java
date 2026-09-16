package com.pratik;

import java.util.Collections;
import java.util.PriorityQueue;
import java.util.Scanner;

public class MedianFinderSystem {

    private static final PriorityQueue<Integer> maxHeap =
            new PriorityQueue<>(Collections.reverseOrder());

    private static final PriorityQueue<Integer> minHeap =
            new PriorityQueue<>();

    public static void addNumber(int num) {
        maxHeap.offer(num);
        minHeap.offer(maxHeap.poll());

        if (minHeap.size() > maxHeap.size()) {
            maxHeap.offer(minHeap.poll());
        }
    }

    public static double findMedian() {
        if (maxHeap.size() > minHeap.size()) {
            return maxHeap.peek();
        }

        return (maxHeap.peek() + minHeap.peek()) / 2.0;
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("How many numbers? ");
        int n = sc.nextInt();

        System.out.println("Enter numbers:");

        for (int i = 0; i < n; i++) {
            addNumber(sc.nextInt());
            System.out.println("Current Median: " + findMedian());
        }

        sc.close();
    }
}