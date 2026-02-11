package com.pratik;



import java.util.*;

public class BFSTraversalGraph {

    private int vertices;
    private LinkedList<Integer>[] adjList;

    // Constructor
    BFSTraversalGraph(int v) {
        vertices = v;
        adjList = new LinkedList[v];

        for (int i = 0; i < v; i++) {
            adjList[i] = new LinkedList<>();
        }
    }

    // Add Edge
    void addEdge(int v, int w) {
        adjList[v].add(w);
    }

    // BFS Traversal
    void BFS(int start) {

        boolean[] visited = new boolean[vertices];
        Queue<Integer> queue = new LinkedList<>();

        visited[start] = true;
        queue.add(start);

        while (!queue.isEmpty()) {

            int node = queue.poll();
            System.out.print(node + " ");

            for (int neighbor : adjList[node]) {
                if (!visited[neighbor]) {
                    visited[neighbor] = true;
                    queue.add(neighbor);
                }
            }
        }
    }

    public static void main(String[] args) {

        BFSTraversalGraph g = new BFSTraversalGraph(5);

        g.addEdge(0, 1);
        g.addEdge(0, 2);
        g.addEdge(1, 3);
        g.addEdge(2, 4);

        System.out.println("BFS Traversal starting from node 0:");
        g.BFS(0);
    }
}
