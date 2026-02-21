package com.pratik;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class PermutationsProgram {

    public static void main(String[] args) {

        int[] nums = {1, 2, 3};

        System.out.println("Input: " + Arrays.toString(nums));

        List<List<Integer>> result = permute(nums);

        System.out.println("Permutations:");
        for (List<Integer> list : result) {
            System.out.println(list);
        }
    }

    public static List<List<Integer>> permute(int[] nums) {

        List<List<Integer>> result = new ArrayList<>();
        backtrack(nums, new ArrayList<>(), result);
        return result;
    }

    private static void backtrack(int[] nums,
                                  List<Integer> current,
                                  List<List<Integer>> result) {

        if (current.size() == nums.length) {
            result.add(new ArrayList<>(current));
            return;
        }

        for (int num : nums) {

            if (current.contains(num)) continue;

            current.add(num);
            backtrack(nums, current, result);
            current.remove(current.size() - 1);
        }
    }
}