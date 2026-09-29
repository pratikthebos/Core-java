package com.pratik;

import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

public class CountSmallerNumbersAfterSelf {

    public static List<Integer> countSmaller(int[] nums) {
        int n = nums.length;

        int[] indices = new int[n];
        int[] temp = new int[n];
        int[] counts = new int[n];

        for (int i = 0; i < n; i++) {
            indices[i] = i;
        }

        mergeSort(nums, indices, temp, counts, 0, n - 1);

        List<Integer> result = new ArrayList<>();

        for (int count : counts) {
            result.add(count);
        }

        return result;
    }

    private static void mergeSort(int[] nums, int[] indices, int[] temp,
                                  int[] counts, int left, int right) {
        if (left >= right) {
            return;
        }

        int mid = left + (right - left) / 2;

        mergeSort(nums, indices, temp, counts, left, mid);
        mergeSort(nums, indices, temp, counts, mid + 1, right);

        merge(nums, indices, temp, counts, left, mid, right);
    }

    private static void merge(int[] nums, int[] indices, int[] temp,
                              int[] counts, int left, int mid, int right) {
        int i = left;
        int j = mid + 1;
        int k = left;
        int rightSmaller = 0;

        while (i <= mid && j <= right) {
            if (nums[indices[j]] < nums[indices[i]]) {
                temp[k++] = indices[j++];
                rightSmaller++;
            } else {
                counts[indices[i]] += rightSmaller;
                temp[k++] = indices[i++];
            }
        }

        while (i <= mid) {
            counts[indices[i]] += rightSmaller;
            temp[k++] = indices[i++];
        }

        while (j <= right) {
            temp[k++] = indices[j++];
        }

        for (int p = left; p <= right; p++) {
            indices[p] = temp[p];
        }
    }

    public static void main(String[] args) {
        Scanner sc = new Scanner(System.in);

        System.out.print("Enter array size: ");
        int n = sc.nextInt();

        int[] nums = new int[n];

        System.out.println("Enter array elements:");
        for (int i = 0; i < n; i++) {
            nums[i] = sc.nextInt();
        }

        List<Integer> result = countSmaller(nums);

        System.out.println("Count of smaller numbers after self: " + result);

        sc.close();
    }
}