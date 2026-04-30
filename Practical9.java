package ADACodesFiles;

import java.util.*;

public class Practical9 {

    // Function to find the vertex with minimum distance value, from
    // the set of vertices not yet included in shortest path tree
    private static int minDistance(int[] dist, boolean[] sptSet, int V) {
        int min = Integer.MAX_VALUE, min_index = -1;

        for (int v = 0; v < V; v++) {
            if (!sptSet[v] && dist[v] <= min) {
                min = dist[v];
                min_index = v;
            }
        }
        return min_index;
    }

    // Function that implements Dijkstra's single source shortest path algorithm
    // for a graph represented using adjacency matrix representation
    public static void dijkstra(int[][] graph, int src, int V) {
        // The output array. dist[i] holds the shortest distance from src to i
        int[] dist = new int[V];

        // sptSet[i] will be true if vertex i is included in shortest path tree
        boolean[] sptSet = new boolean[V];

        // Initialize all distances as INFINITE and sptSet[] as false
        for (int i = 0; i < V; i++) {
            dist[i] = Integer.MAX_VALUE;
            sptSet[i] = false;
        }

        // Distance of source vertex from itself is always 0
        dist[src] = 0;

        // Find shortest path for all vertices
        for (int count = 0; count < V - 1; count++) {
            // Pick the minimum distance vertex from the set of vertices not yet processed.
            int u = minDistance(dist, sptSet, V);

            // Mark the picked vertex as processed
            sptSet[u] = true;

            // Update dist value of the adjacent vertices of the picked vertex
            for (int v = 0; v < V; v++) {
                // Update dist[v] only if is not in sptSet, there is an edge from u to v,
                // and total weight of path from src to v through u is smaller than current dist[v]
                if (!sptSet[v] && graph[u][v] != 0 && dist[u] != Integer.MAX_VALUE
                        && dist[u] + graph[u][v] < dist[v]) {
                    dist[v] = dist[u] + graph[u][v];
                }
            }
        }

        // Print the constructed distance array
        printSolution(dist, V);
    }

    // A utility function to print the constructed distance array
    private static void printSolution(int[] dist, int V) {
        System.out.println("Vertex \t\t Distance from Source");
        for (int i = 0; i < V; i++) {
            System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        // Let us create an example graph represented as an adjacency matrix
        int[][] graph = new int[][] {
                { 0, 4, 0, 0, 0, 0, 0, 8, 0 },
                { 4, 0, 8, 0, 0, 0, 0, 11, 0 },
                { 0, 8, 0, 7, 0, 4, 0, 0, 2 },
                { 0, 0, 7, 0, 9, 14, 0, 0, 0 },
                { 0, 0, 0, 9, 0, 10, 0, 0, 0 },
                { 0, 0, 4, 14, 10, 0, 2, 0, 0 },
                { 0, 0, 0, 0, 0, 2, 0, 1, 6 },
                { 8, 11, 0, 0, 0, 0, 1, 0, 7 },
                { 0, 0, 2, 0, 0, 0, 6, 7, 0 }
        };
        int V = 9; // Number of vertices in the graph

        System.out.println("--- Dijkstra Algorithm ---");
        System.out.println("Source Vertex: 0\n");
        dijkstra(graph, 0, V);
    }
}