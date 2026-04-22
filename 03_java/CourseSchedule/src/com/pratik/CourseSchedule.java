package com.pratik;

import java.util.*;

public class CourseSchedule {

    public static boolean canFinish(int numCourses, int[][] prerequisites) {

        List<List<Integer>> graph = new ArrayList<>();

        for (int i = 0; i < numCourses; i++) {
            graph.add(new ArrayList<>());
        }

        // Build graph
        for (int[] p : prerequisites) {
            graph.get(p[1]).add(p[0]);
        }

        int[] visited = new int[numCourses]; // 0 = unvisited, 1 = visiting, 2 = visited

        for (int i = 0; i < numCourses; i++) {
            if (dfs(i, graph, visited)) {
                return false; // cycle detected
            }
        }

        return true;
    }

    private static boolean dfs(int node, List<List<Integer>> graph, int[] visited) {

        if (visited[node] == 1) return true;  // cycle
        if (visited[node] == 2) return false; // already processed

        visited[node] = 1;

        for (int neighbor : graph.get(node)) {
            if (dfs(neighbor, graph, visited)) {
                return true;
            }
        }

        visited[node] = 2;
        return false;
    }

    public static void main(String[] args) {

        int numCourses = 2;
        int[][] prerequisites = {{1, 0}, {0, 1}};

        boolean result = canFinish(numCourses, prerequisites);

        System.out.println("Can finish courses: " + result);
    }
}