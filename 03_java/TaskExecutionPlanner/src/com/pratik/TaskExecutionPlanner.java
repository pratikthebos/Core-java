package com.pratik;

import java.util.*;

public class TaskExecutionPlanner {

    public static int leastInterval(char[] tasks, int n) {

        int[] frequency = new int[26];

        // Count task frequency
        for (char task : tasks) {
            frequency[task - 'A']++;
        }

        Arrays.sort(frequency);

        int maxFreq = frequency[25] - 1;
        int idleSlots = maxFreq * n;

        for (int i = 24; i >= 0; i--) {
            idleSlots -= Math.min(frequency[i], maxFreq);
        }

        idleSlots = Math.max(0, idleSlots);

        return tasks.length + idleSlots;
    }

    public static void main(String[] args) {

        char[] tasks = {'A','A','A','B','B','B'};
        int n = 2;

        int result = leastInterval(tasks, n);

        System.out.println("Minimum CPU Intervals: " + result);
    }
}