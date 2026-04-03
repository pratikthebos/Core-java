package com.pratik;

public class SecondLargestElement {

    public static int findSecondLargest(int[] nums) {

        int largest = Integer.MIN_VALUE;
        int secondLargest = Integer.MIN_VALUE;

        for (int num : nums) {

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

    public static void main(String[] args) {

        int[] nums = {10, 20, 4, 45, 99};

        int result = findSecondLargest(nums);

        System.out.println("Second Largest: " + result);
    }
}