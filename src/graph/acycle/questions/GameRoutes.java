package graph.acycle.questions;

import java.sql.Array;
import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class GameRoutes {

    private static final int MOD = 1000000007;

    public static void main(String[] args) {
        int n = 5;
        int[][] teleporters = {{1, 2}, {1, 3}, {2, 3}, {1, 4}, {2, 4}, {4, 5}};

        Graph g = new Graph(n + 1);
        for (int[] t : teleporters) {
            g.addEdges(t[0], t[1]);
        }

        System.out.println("Number of ways to complete the game: " + g.topo());
    }

    private static class Graph {
        int V;
        List<Integer>[] adjs;

        public Graph(int v) {
            this.V = v;
            adjs = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                adjs[i] = new ArrayList<>();
            }
        }

        public void addEdges(int i, int j) {
            adjs[i].add(j);
        }

        private int dfs(int node, boolean[] visited, boolean[] stack) {
            visited[node] = true;
            stack[node] = true;

            if (node == V) {
                return 1;
            }

            int result = 0;
            for (int nbr: adjs[node]) {
                if (stack[nbr]) {
                    return -1;
                }

                if (!visited[nbr]) {
                    int val = dfs(nbr, visited, stack);
                    if (val == -1) {
                        return -1;
                    }

                    result = (result + val) % MOD;
                }
            }

            stack[node] = false;
            return result;
        }

        public int findAllPaths() {
            boolean[] visited = new boolean[V];
            boolean[] stack = new boolean[V];

            return dfs(1, visited, stack);
        }

        private int topo() {
            int[] indegree = new int[V];
            long[] ways = new long[V];

            for (int i = 1; i < V; i++) {
                for (int nbr: adjs[i]) {
                    indegree[nbr]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();
            int processed = 0;

            ways[1] = 1;

            for (int i = 1; i < V; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();
                processed++;

                for (int nbr: adjs[node]) {
                    ways[nbr] = (ways[node] + ways[nbr]) % MOD;
                    indegree[nbr]--;
                    if (indegree[nbr] == 0) {
                        queue.offer(nbr);
                    }
                }
            }

            if (processed != V - 1) return -1;

            return (int) ways[V - 1];
        }
    }
}
