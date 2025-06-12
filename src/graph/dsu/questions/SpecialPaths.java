package graph.dsu.questions;


import kotlin.Triple;

import java.util.*;

/**
 * Special Paths
 * You are given an undirected graph with n nodes numbered 1 to n. Every node i has a value  a[i-1] assigned to it.
 *
 * The tree is given as an array of edges where edges[i] = [ui, vi] is a bidirectional edge between node ui and node vi .
 *
 * The value of a simple path between node u and v is as follows:
 *
 * The maximum absolute difference between the values of adjacent nodes present in the simple path.
 *
 * Return the minimum possible path value of any simple paths between start and end nodes.
 *
 * Constraints:
 *
 * 1<= n <= 10^5
 *
 * n-1 <= edges.length <= min( n*(n-1)/2 , 10^5)
 *
 * 1<= ui,vi <=n
 *
 * 0 <= ai <= 10^4
 *
 * Input : n = 7,a = {56,32,67,29,16,6,64}, edges = {{5,3},{4,5},{5,1},{5,2}}, start = 1, end = 2
 * Output : 40
 */
public class SpecialPaths {

    public static void main(String[] args) {
        int n = 7;
        int[] a = {56, 32, 67, 29, 16, 6, 64};
        int[][] edges = {{5,3},{4,5},{5,1},{5,2}};
        int start = 1, end = 2;
        System.out.println(specialPath(n, a, edges, start, end)); // Expected: 40
    }

    private static int specialPath(int n, int[] a, int[][] edges,int start, int end) {
//        Graph graph = new Graph(n + 1);
//        for (int[] edge: edges) {
//            graph.addEdges(edge[0], edge[1], true);
//        }

//        return graph.specialPath(start, end, a);

        DSU dsu = new DSU(n + 1);
        return dsu.shortestPath(a, edges, start, end);
    }

    private static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int v) {
            parent = new int[v];
            rank = new int[v];

            Arrays.fill(parent, -1);
            Arrays.fill(rank, 1);
        }

        public int findSet(int i) {
            if (parent[i] == -1) {
                return i;
            }

            return parent[i] = findSet(parent[i]);
        }

        private void unionSet(int i, int j) {
            int s1 = findSet(i);
            int s2 = findSet(j);

            if (s1 != s2) {
                if (rank[s1] < rank[s2]) {
                    parent[s1] = s2;
                    rank[s2] += rank[s1];
                } else {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }

        private int shortestPath(int[] a, int[][] edges,  int start, int end) {
            List<Triple<Integer, Integer, Integer>> l = new ArrayList<>();
            for (int[] edge: edges) {
                int u = edge[0];
                int v = edge[1];
                int diff = Math.abs(a[u - 1] - a[v - 1]);
                l.add(new Triple<>(diff, u, v));
            }

            l.sort(Comparator.comparingInt(Triple::getFirst));

            for (Triple<Integer, Integer, Integer> t: l) {
                int diff = t.getFirst();
                int u = t.getSecond();
                int v = t.getThird();
                unionSet(u, v);
                if (findSet(start) == findSet(end)) {
                   return diff;
                }
            }

            return -1;
        }
    }


    private static class Graph {
        int V;
        List<Integer>[] adjs;


        public Graph(int v) {
            this.V = v;
            adjs = new ArrayList[v];

            for (int i = 0; i < v; i++) {
                adjs[i] = new ArrayList<>();
            }
        }

        public void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
            }
        }

        private int specialPath(int src, int dest, int[] a) {
            int[] absValue = new int[V];
            Arrays.fill(absValue, Integer.MAX_VALUE);

            Queue<Integer> queue = new LinkedList<>();
            queue.offer(src);
            absValue[src] = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();

                for (int nbr: adjs[node]) {
                    int diff = Math.abs(a[node - 1] - a[nbr - 1]);
                    int newVal = Math.max(diff, absValue[node]);

                    if (newVal < absValue[nbr]) {
                        absValue[nbr] = newVal;
                        queue.offer(nbr);
                    }
                }
            }

            return absValue[dest];
        }

    }
}
