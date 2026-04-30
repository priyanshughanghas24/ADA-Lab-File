package ADACodesFiles;

public class Practical10 {

    // A class to represent a connected, directed and weighted graph
    static class Graph {
        // A class to represent a weighted edge in graph
        class Edge {
            int src, dest, weight;
            Edge() {
                src = dest = weight = 0;
            }
        };

        int V, E;
        Edge edge[];

        // Creates a graph with V vertices and E edges
        Graph(int v, int e) {
            V = v;
            E = e;
            edge = new Edge[e];
            for (int i = 0; i < e; ++i)
                edge[i] = new Edge();
        }

        // The main function that finds shortest distances from src
        // to all other vertices using Bellman-Ford algorithm.
        void BellmanFord(Graph graph, int src) {
            int V = graph.V, E = graph.E;
            int dist[] = new int[V];

            // Step 1: Initialize distances from src to all other vertices as INFINITE
            for (int i = 0; i < V; ++i)
                dist[i] = Integer.MAX_VALUE;
            dist[src] = 0;

            // Step 2: Relax all edges |V| - 1 times.
            for (int i = 1; i < V; ++i) {
                for (int j = 0; j < E; ++j) {
                    int u = graph.edge[j].src;
                    int v = graph.edge[j].dest;
                    int weight = graph.edge[j].weight;
                    if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v])
                        dist[v] = dist[u] + weight;
                }
            }

            // Step 3: check for negative-weight cycles.
            for (int j = 0; j < E; ++j) {
                int u = graph.edge[j].src;
                int v = graph.edge[j].dest;
                int weight = graph.edge[j].weight;
                if (dist[u] != Integer.MAX_VALUE && dist[u] + weight < dist[v]) {
                    System.out.println("Graph contains negative weight cycle");
                    return;
                }
            }
            printArr(dist, V);
        }

        // A utility function used to print the solution
        void printArr(int dist[], int V) {
            System.out.println("Vertex \t\t Distance from Source");
            for (int i = 0; i < V; ++i)
                System.out.println(i + " \t\t " + dist[i]);
        }
    }

    public static void main(String[] args) {
        int V = 5; // Number of vertices in graph
        int E = 8; // Number of edges in graph

        Graph graph = new Graph(V, E);

        // Edge 0-1
        graph.edge[0].src = 0;
        graph.edge[0].dest = 1;
        graph.edge[0].weight = -1;

        // Edge 0-2
        graph.edge[1].src = 0;
        graph.edge[1].dest = 2;
        graph.edge[1].weight = 4;

        // Edge 1-2
        graph.edge[2].src = 1;
        graph.edge[2].dest = 2;
        graph.edge[2].weight = 3;

        // Edge 1-3
        graph.edge[3].src = 1;
        graph.edge[3].dest = 3;
        graph.edge[3].weight = 2;

        // Edge 1-4
        graph.edge[4].src = 1;
        graph.edge[4].dest = 4;
        graph.edge[4].weight = 2;

        // Edge 3-2
        graph.edge[5].src = 3;
        graph.edge[5].dest = 2;
        graph.edge[5].weight = 5;

        // Edge 3-1
        graph.edge[6].src = 3;
        graph.edge[6].dest = 1;
        graph.edge[6].weight = 1;

        // Edge 4-3
        graph.edge[7].src = 4;
        graph.edge[7].dest = 3;
        graph.edge[7].weight = -3;

        System.out.println("--- Bellman-Ford Algorithm ---");
        System.out.println("Source Vertex: 0\n");
        graph.BellmanFord(graph, 0);
    }
}