package com.pratik;

import java.util.*;

public class MultiArrayMergeProcessor {

    static class Element {
        int value;
        int arrayIndex;
        int elementIndex;

        Element(int value, int arrayIndex, int elementIndex) {
            this.value = value;
            this.arrayIndex = arrayIndex;
            this.elementIndex = elementIndex;
        }
    }

    public static List<Integer> mergeSortedArrays(int[][] arrays) {

        List<Integer> result = new ArrayList<>();

        PriorityQueue<Element> minHeap =
                new PriorityQueue<>((a, b) -> a.value - b.value);

        // Add first element of each array
        for (int i = 0; i < arrays.length; i++) {

            if (arrays[i].length > 0) {
                minHeap.offer(new Element(arrays[i][0], i, 0));
            }
        }

        while (!minHeap.isEmpty()) {

            Element current = minHeap.poll();
            result.add(current.value);

            int nextIndex = current.elementIndex + 1;

            // Add next element from same array
            if (nextIndex < arrays[current.arrayIndex].length) {

                minHeap.offer(
                        new Element(
                                arrays[current.arrayIndex][nextIndex],
                                current.arrayIndex,
                                nextIndex
                        )
                );
            }
        }

        return result;
    }

    public static void main(String[] args) {

        int[][] arrays = {
                {1, 4, 5},
                {1, 3, 4},
                {2, 6}
        };

        List<Integer> result = mergeSortedArrays(arrays);

        System.out.println("Merged Sorted Array: " + result);
    }
}