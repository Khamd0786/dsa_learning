package graph.dsu;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class DSUCycleDetectByRankUndirectedGraph {

    public static void main(String[] args) {
        Graph g = new Graph(4); // 4 nodes: 0, 1, 2, 3

        g.addEdge(0, 1);
        g.addEdge(1, 2);
        g.addEdge(2, 3);
        g.addEdge(3, 0); // This edge creates a cycle

        if (g.cycleDetected()) {
            System.out.println("Cycle detected ✅");
        } else {
            System.out.println("No cycle ❌");
        }
    }

    private static class Graph {
        int v;
        List<Pair<Integer, Integer>> edges;

        public Graph(int v) {
            this.v = v;
            edges = new ArrayList<>();
        }

        public void addEdge(int i, int j) {
            edges.add(new Pair<>(i, j));
        }

        private int findSet(int i, int[] parent) {
            if (parent[i] == -1) {
                return i;
            }

            return parent[i] = findSet(parent[i], parent); //path compression optimization
        }

        private void unionSet(int i, int j, int[] parent, int[] rank) {
            int s1  = findSet(i, parent);
            int s2 = findSet(j, parent);

            if (s1 != s2) {
                if(rank[s2] > rank[s1]) {
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                } else {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }

        public boolean cycleDetected() {
            int[] parent = new int[v];
            Arrays.fill(parent, -1);

            int[] rank = new int[v]; //rank optimization
            Arrays.fill(rank, 1);

            for (Pair<Integer, Integer> edge: edges) {
                int s1 = findSet(edge.getFirst(), parent);
                int s2 = findSet(edge.getSecond(), parent);

                if (s1 != s2) {
                    unionSet(s1, s2, parent, rank);
                } else {
                    System.out.print("Cycle Detected At Edges " + edge.getFirst() + ", " + edge.getSecond() + " With Value-> " + s1 + " Hence: ");
                    return true;
                }
            }

            return false;
        }
    }

}
