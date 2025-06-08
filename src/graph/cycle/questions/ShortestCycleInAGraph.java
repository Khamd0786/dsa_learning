package graph.cycle.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ShortestCycleInAGraph {

    public static void main(String[] args) {

        // Example graph:
        // 0 -- 1
        // |    |
        // 3 -- 2
        int n = 4;
        int[][] edges = {
                {0, 1},
                {1, 2},
                {2, 3},
                {3, 0}
        };

        int result = shortestCycle(n, edges);
        System.out.println("Length of shortest cycle: " + result);
    }

    private static int shortestCycle(int n, int[][] edges) {
        Graph graph = new Graph(n);

        for (int[] edge : edges) {
            graph.addEdges(edge[0], edge[1], true);
        }

        return graph.shortestCycle(0);
    }

    private static class Graph {
        private int V;
        private List<Integer>[] adjs;

        public Graph(int v) {
            this.V = v;
            adjs = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                adjs[i] = new ArrayList<>();
            }
        }

        public void addEdges(int i, int j, boolean isBidirectional) {
            adjs[i].add(j);
            if (isBidirectional) {
                adjs[j].add(i);
            }
        }

        public int shortestCycle(int src) {
            int result = Integer.MAX_VALUE;
            for (int i = src; i < V; i++) {
                int[] dist = new int[V];
                Arrays.fill(dist, -1);
                dist[i] = 0;
                result = Math.min(dfs(i, -1, dist), result);
            }

            return result == Integer.MAX_VALUE ? -1 : result;
        }

        private int dfs(int node, int parent, int[] dist) {

            for (int nbr : adjs[node]) {
                if (dist[nbr] == -1) {
                    dist[nbr] = dist[node] + 1;
                    int result = dfs(nbr, node, dist);
                    if (result != Integer.MAX_VALUE) {
                        return result;
                    }
                } else if (nbr != parent) {
                    return dist[node] - dist[nbr] + 1;
                }
            }

            return Integer.MAX_VALUE;
        }
    }
}
