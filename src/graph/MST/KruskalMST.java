package graph.MST;

import kotlin.Pair;

import java.util.*;

/**
 * Kruskal's Algorith to find Minimum Spanning of a Tree
 */
public class KruskalMST {

    public static void main(String[] args) {
        Graph g  = new Graph(5);

        g.addEdges(0, 1, 2);
        g.addEdges(0, 3, 6);
        g.addEdges(1, 2, 3);
        g.addEdges(1, 3, 8);
        g.addEdges(1, 4, 5);
        g.addEdges(2, 4, 7);
        g.addEdges(3, 4, 9);

        int minCost = g.kruskalMST();
        System.out.println("Minimum Total Cost of MST using Kruskal: " + minCost);
    }

    private static class Graph {
        int v;
        List<int[]> edgeList;

        public Graph(int v) {
            this.v = v;
            edgeList = new ArrayList<>();
        }

        public void addEdges(int i, int j, int w) {
            edgeList.add(new int[]{w, i, j});
        }

        public int kruskalMST() {
            //sort according to weight
            edgeList.sort(Comparator.comparingInt(a -> a[0]));
            int totalWeight = 0;

            DSU dsu = new DSU(v);
            for (int[] edge : edgeList) {
                int w = edge[0];
                int i = edge[1];
                int j = edge[2];

                if (dsu.find(i) != dsu.find(j)) {
                    dsu.union(i, j);
                    totalWeight += w;
                }
            }

            return totalWeight;
        }
    }


    public static class DSU {
        int[] parents;
        int[] ranks;

        public DSU(int n) {
            parents = new int[n];
            Arrays.fill(parents, -1);

            ranks = new int[n];
            Arrays.fill(ranks, 1);
        }

        private int find(int i) {
            if (parents[i] == -1) {
                return i;
            }

            return parents[i] = find(parents[i]);
        }

        private void union(int i, int j) {
            int s1 = find(i);
            int s2 = find(j);

            if (s1 != s2) {
                if (ranks[s1] < ranks[s2]) {
                    parents[s1] = s2;
                    ranks[s2] += ranks[s1];
                } else {
                    parents[s2] = s1;
                    ranks[s1] += ranks[s2];
                }
            }
        }
    }
}
