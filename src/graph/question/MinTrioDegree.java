package graph.question;

import java.util.HashSet;

public class MinTrioDegree {

    public static void main(String[] args) {
        int n = 6;
        int[][] edges = {
                {1, 2},
                {1, 3},
                {3, 2},
                {4, 1},
                {5, 2},
                {3, 6}
        };

        int result = minTrioDegree(n, edges);
        System.out.println("Minimum Trio Degree: " + result);
    }

    private static int minTrioDegree(int n, int[][] edges) {
        HashSet<Integer>[] graph = new HashSet[n + 1];

        for (int i = 0; i < n + 1; i++) {
            graph[i] = new HashSet<>();
        }

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];
            graph[u].add(v);
            graph[v].add(u);
        }

        int minEdges = Integer.MAX_VALUE;
        for (int i = 0; i < graph.length; i++) {
            for (int j = i + 1; j < graph.length; j++) {
                for (int k = j + 1; k < graph.length; k++) {
                    if (graph[i].contains(j) && graph[j].contains(k) && graph[k].contains(i)) { //triangle
                        minEdges = Math.min(graph[i].size() + graph[j].size() + graph[k].size() - 6, minEdges);
                    }
                }
            }
        }

        return minEdges;
    }
}
