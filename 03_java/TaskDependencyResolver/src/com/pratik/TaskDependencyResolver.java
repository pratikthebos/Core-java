package com.pratik;

import java.util.*;

public class TaskDependencyResolver {

    public static List<Integer> findTaskOrder(
            int totalTasks,
            int[][] dependencies) {

        List<List<Integer>> graph =
                new ArrayList<>();

        for (int i = 0; i < totalTasks; i++) {
            graph.add(new ArrayList<>());
        }

        int[] inDegree = new int[totalTasks];

        // Build graph
        for (int[] dependency : dependencies) {

            int prerequisite = dependency[0];
            int task = dependency[1];

            graph.get(prerequisite).add(task);
            inDegree[task]++;
        }

        Queue<Integer> queue =
                new LinkedList<>();

        // Add tasks with no dependency
        for (int i = 0; i < totalTasks; i++) {

            if (inDegree[i] == 0) {
                queue.offer(i);
            }
        }

        List<Integer> order =
                new ArrayList<>();

        while (!queue.isEmpty()) {

            int currentTask =
                    queue.poll();

            order.add(currentTask);

            for (int nextTask :
                    graph.get(currentTask)) {

                inDegree[nextTask]--;

                if (inDegree[nextTask] == 0) {
                    queue.offer(nextTask);
                }
            }
        }

        // Cycle check
        if (order.size() != totalTasks) {
            return new ArrayList<>();
        }

        return order;
    }

    public static void main(String[] args) {

        int totalTasks = 4;

        int[][] dependencies = {
                {0, 1},
                {0, 2},
                {1, 3},
                {2, 3}
        };

        List<Integer> result =
                findTaskOrder(
                        totalTasks,
                        dependencies);

        System.out.println(
                "Task Execution Order: "
                        + result);
    }
}