package com.pratik;

import java.util.Arrays;

public class SecondLargestElement {

    public static void main(String[] args) {

        int[] arr = {10, 5, 20, 8, 15};

        System.out.println("Array: " + Arrays.toString(arr));

        int secondLargest = findSecondLargest(arr);

        if (secondLargest == Integer.MIN_VALUE) {
            System.out.println("No second largest element found.");
        } else {
            System.out.println("Second largest element: " + secondLargest);
        }
    }

    public static int findSecondLargest(int[] arr) {

        if (arr.length < 2) return Integer.MIN_VALUE;

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : arr) {

            if (num > largest) {
                secondLargest = largest;
                largest = num;
            }
            else if (num > secondLargest && num != largest) {
                secondLargest = num;
            }
        }

        return secondLargest;
    }
}