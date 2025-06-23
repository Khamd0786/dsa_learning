package graph.MST.questions;

import java.util.*;

public class FindCriticalAndPseudo_CriticalEdgesMST {

    public static void main(String[] args) {
        int n = 5;
        int[][] edges = {
                {0, 1, 1},
                {0, 2, 1},
                {0, 3, 2},
                {0, 4, 2},
                {1, 2, 2},
                {1, 3, 3},
                {1, 4, 3},
                {2, 3, 3},
                {2, 4, 3},
                {3, 4, 4}
        };

        List<List<Integer>> result = findCriticalAndPseudoCriticalEdges(n, edges);
        System.out.println("Critical edges: " + result.get(0));
        System.out.println("Non-critical edges: " + result.get(1));
    }

    public static List<List<Integer>> findCriticalAndPseudoCriticalEdges(int n, int[][] edges) {
        Graph graph = new Graph(n);
        for (int[] edge: edges) {
            graph.addEdges(edge[0], edge[1], edge[2]);
        }

        int baseMst = graph.kruskalMstExcept();

        List<Integer> critical = new ArrayList<>();
        List<Integer> pseudo = new ArrayList<>();

        for (int i = 0; i < edges.length; i++) {
            int withoutMst = graph.kruskalMstExcept(edges[i]);
            if (withoutMst > baseMst) {
                // very important edge, we can't miss it
                critical.add(i);
            }  else {
                // not very important edge
                int withMst = graph.kruskalMstWith(edges[i]);
                if (withMst == baseMst) {
                    pseudo.add(i);
                }
            }

        }

        List<List<Integer>> result = new ArrayList<>();

        result.add(critical);
        result.add(1, pseudo);
        return result;
    }

    private static class Graph {
        List<int[]> edges;
        int v;

        public Graph(int v) {
            this.v = v;
            edges = new ArrayList<>();
        }

        public void addEdges(int i, int j, int w) {
            edges.add(new int[]{w, i, j});
        }

        private int kruskalMstExcept() {
            edges.sort(Comparator.comparingInt(a -> a[0]));

            DSU dsu = new DSU(v);
            int totalCount = 0;
            for (int[] edge: edges) {
                int w = edge[0];
                int i = edge[1];
                int j = edge[2];

                if (dsu.find(i) != dsu.find(j)) {
                    dsu.union(i, j);
                    totalCount += w;
                }
            }

            return totalCount;
        }

        private int kruskalMstExcept(int[] except) {
            DSU dsu = new DSU(v);
            int totalCount = 0;
            int totalEdges = 0;
            for (int[] edge: edges) {
                int w = edge[0];
                int i = edge[1];
                int j = edge[2];

                if ((i == except[0] && j == except[1]) || (i == except[1] && j == except[0])) {
                    continue;
                }

                if (dsu.find(i) != dsu.find(j)) {
                    dsu.union(i, j);
                    totalCount += w;
                    totalEdges++;
                }
            }

            return (totalEdges < v - 1) ? Integer.MAX_VALUE : totalCount;
        }


        private int kruskalMstWith(int[] forced) {
            DSU dsu = new DSU(v);
            dsu.union(forced[0], forced[1]);
            int totalCount = forced[2];
            for (int[] edge: edges) {
                int w = edge[0];
                int i = edge[1];
                int j = edge[2];

                if (dsu.find(i) != dsu.find(j)) {
                    dsu.union(i, j);
                    totalCount += w;
                }
            }

            return totalCount;
        }
    }

    private static class DSU {
        int[] parent;
        int[] rank;

        public DSU(int v) {
            parent = new int[v];
            Arrays.fill(parent, -1);
            rank = new int[v];
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
                } else {
                    parent[s2] = s1;
                    rank[s1] += rank[s2];
                }
            }
        }
    }

}
