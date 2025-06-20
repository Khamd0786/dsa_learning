package graph.MST.questions;


import java.util.Arrays;

/**
 * Alice and Bob have an undirected graph of n nodes and three types of edges:
 * <p>
 * Type 1: Can be traversed by Alice only.
 * Type 2: Can be traversed by Bob only.
 * Type 3: Can be traversed by both Alice and Bob.
 * Given an array edges where edges[i] = [typei, ui, vi]
 * represents a bidirectional edge of type typei between
 * nodes ui and vi, find the maximum number of edges
 * you can remove so that after removing the edges,
 * the graph can still be fully traversed by both Alice and Bob.
 * The graph is fully traversed by Alice and Bob if starting from any node,
 * they can reach all other nodes.
 * <p>
 * Return the maximum number of edges you can remove,
 * or return -1 if Alice and Bob cannot fully traverse the graph.
 */
public class RemoveMaxNumberOfEdgesToKeepGraphFullyTraversable {

    public static void main(String[] args) {
        int n = 4;
        int[][] edges = {
                {3, 1, 2},
                {3, 2, 3},
                {1, 1, 3},
                {1, 2, 4},
                {1, 1, 2},
                {2, 3, 4}
        };

        int result = maxNumEdgesToRemove(n, edges);
        System.out.println("Maximum number of edges we can remove: " + result);
    }

    public static int maxNumEdgesToRemove(int n, int[][] edges) {
        DSU bobDSU = new DSU(n + 1);
        DSU aliceDSU = new DSU(n + 1);

        int count = 0;

        for (int[] edge : edges) {
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (type != 3) continue;

            boolean s1 = bobDSU.union(u, v);
            boolean s2 =  aliceDSU.union(u, v);
            if (!s1 && !s2) {
                count++;
            }
        }

        for (int[] edge : edges) {
            int type = edge[0];
            int u = edge[1];
            int v = edge[2];

            if (type == 3) continue;

            if (type == 1) {
                if (!aliceDSU.union(u, v)) {
                    count++;
                }
            } else if (type == 2) {
                if (!bobDSU.union(u, v)) {
                    count++;
                }
            }
        }

        return (aliceDSU.canTraverse() && bobDSU.canTraverse()) ? count : -1;
    }


    private static class DSU {
        int[] parent;
        int[] rank;
        int parentCount;

        public DSU(int v) {
            parent = new int[v];
            Arrays.fill(parent, -1);

            rank = new int[v];
            Arrays.fill(rank, 1);

            parentCount = v - 1;
        }

        public int find(int i) {
            if (parent[i] == -1) {
                return i;
            }

            return parent[i] = find(parent[i]);
        }

        public boolean union(int i, int j) {
            int s1 = find(i);
            int s2 = find(j);

            if (s1 != s2) {
                if (rank[s1] < rank[s2]) {
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                } else {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            } else {
                return false;
            }

            parentCount--;
            return true;
        }

        private boolean canTraverse() {
            return parentCount == 1;
        }
    }

}
