package graph.MST.questions;

import java.util.*;

/**
 * Minimum Spanning Cost
 * Given a weighted undirected graph. Find the sum of weights of edges of a Minimum Spanning Tree.
 *
 * The graph is given as an array of edges where edges[i] = [u, v, w]
 * is a bidirectional edge between node u and node v with weight w.
 * Each node has labels in the set {1,2,.......,n}.
 */
public class MinimumSpanningCostKruskal {

    public static void main(String[] args) {
        int[][] edges = new int[][] {{1,  2,  7}, {1,  4,  6}, {4 ,2,  9}, {4,  3,  8}, {2,  3, 6}};
        int n = 4;

        int result = MST(n, edges);
        System.out.println("Result-> " + result);
    }

    private static int MST(int n, int[][] edges) {
        Graph graph = new Graph(edges.length);

        for (int[] edge : edges) {
            graph.addEdges(edge[0], edge[1], edge[2]);
        }
        return graph.kruskalMST();
    }

    private static class Graph {
        List<int[]> adjs;
        int v;

        public Graph(int v) {
            this.v = v;
            adjs = new ArrayList<>();
        }

        public void addEdges(int i, int j, int w) {
            adjs.add(new int[] {w, i , j});
        }

        private int kruskalMST() {
            adjs.sort(Comparator.comparingInt(a -> a[0]));

            int totalCost = 0;

            DSU dsu = new DSU(v);

            for (int[] nbr: adjs) {
                int w = nbr[0];
                int i = nbr[1];
                int j = nbr[2];

                if (dsu.find(i) != dsu.find(j)) {
                    dsu.union(i, j);
                    totalCost += w;
                }
            }

            return totalCost;
        }
    }

    private static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int n) {
            parent = new int[n];
            Arrays.fill(parent, -1);
            rank = new int[n];
            Arrays.fill(rank, 1);
        }

        private int find(int i) {
            if (parent[i] == -1) {
                return i;
            }

            return parent[i] = find(parent[i]);
        }

        private void union(int i, int j) {
            int s1 = find(i);
            int s2 = find(j);

            if (s1 != s2) {
                if (rank[s1] < rank[s2]) {
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                } else  {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }
    }
}
