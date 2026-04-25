package com.pratik;

import java.util.*;

public class DailyTemperatures {

    public static int[] dailyTemperatures(int[] temps) {

        int n = temps.length;
        int[] result = new int[n];

        Stack<Integer> stack = new Stack<>(); // stores indices

        for (int i = 0; i < n; i++) {

            while (!stack.isEmpty() && temps[i] > temps[stack.peek()]) {

                int prevIndex = stack.pop();
                result[prevIndex] = i - prevIndex;
            }

            stack.push(i);
        }

        return result;
    }

    public static void main(String[] args) {

        int[] temps = {73,74,75,71,69,72,76,73};

        int[] result = dailyTemperatures(temps);

        System.out.print("Result: ");
        for (int num : result) {
            System.out.print(num + " ");
        }
    }
}