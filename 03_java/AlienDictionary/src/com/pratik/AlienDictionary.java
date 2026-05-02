package com.pratik;

import java.util.*;

public class AlienDictionary {

    public static String alienOrder(String[] words) {

        Map<Character, Set<Character>> graph = new HashMap<>();
        Map<Character, Integer> indegree = new HashMap<>();

        // Initialize
        for (String word : words) {
            for (char c : word.toCharArray()) {
                graph.putIfAbsent(c, new HashSet<>());
                indegree.putIfAbsent(c, 0);
            }
        }

        // Build graph
        for (int i = 0; i < words.length - 1; i++) {

            String w1 = words[i];
            String w2 = words[i + 1];

            int len = Math.min(w1.length(), w2.length());

            for (int j = 0; j < len; j++) {

                char c1 = w1.charAt(j);
                char c2 = w2.charAt(j);

                if (c1 != c2) {

                    if (!graph.get(c1).contains(c2)) {
                        graph.get(c1).add(c2);
                        indegree.put(c2, indegree.get(c2) + 1);
                    }
                    break;
                }
            }
        }

        // Topological sort
        Queue<Character> queue = new LinkedList<>();

        for (char c : indegree.keySet()) {
            if (indegree.get(c) == 0) {
                queue.offer(c);
            }
        }

        StringBuilder result = new StringBuilder();

        while (!queue.isEmpty()) {

            char c = queue.poll();
            result.append(c);

            for (char neighbor : graph.get(c)) {

                indegree.put(neighbor, indegree.get(neighbor) - 1);

                if (indegree.get(neighbor) == 0) {
                    queue.offer(neighbor);
                }
            }
        }

        // Check cycle
        if (result.length() != indegree.size()) return "";

        return result.toString();
    }

    public static void main(String[] args) {

        String[] words = {"wrt","wrf","er","ett","rftt"};

        String result = alienOrder(words);

        System.out.println("Alien Order: " + result);
    }
}