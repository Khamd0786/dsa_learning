package graph.bfs.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class SnakeAndLadder {

    public static void main(String[] args) {
        int[][] snakes = {
                {2, 15},
                {5, 7},
                {9, 29},
                {18, 29},
                {25, 35}
        };

        // Sample ladders: from -> to
        int[][] ladders = {
                {17, 4},
                {20, 6},
                {34, 12},
                {24, 16},
                {32, 30}
        };

        System.out.println("Minimum dice throws required: " + minDiceThrows(36, ladders, snakes));
    }

    private static int minDiceThrows(int n, int[][] ladders, int[][] snakes) {
        int[] board = new int[n + 1];

        for (int[] ladder: ladders) {
            int s = ladder[0];
            int e = ladder[1];
            board[s] = e - s;
        }

        for (int[] snake: snakes) {
            int s = snake[0];
            int e = snake[1];
            board[s] = e - s;
        }

        Graph graph = new Graph(n + 1);
        for (int u = 1; u < n + 1; u++) {
            for (int dice = 1; dice <= 6; dice++) {
                int v = u + dice;
                if (v <= n) {
                    v += board[v];
                    graph.addEdge(u, v, false);
                }
            }
        }

        return graph.minCostBFS(1, 36);
    }

    private static class Graph {
        int V;
        List<Integer>[] edges;

        public Graph(int v) {
            this.V = v;
            edges = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                edges[i] = new ArrayList<>();
            }
        }

        private void addEdge(int i, int j, boolean isUndirected) {
            edges[i].add(j);
            if (isUndirected) {
                edges[j].add(i);
            }
        }

        private int minCostBFS(int src, int dest) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[V];
            int[] dist = new int[V];


            queue.offer(src);
            visited[src] = true;
            dist[src] = 0;

            while (!queue.isEmpty()) {
                int v = queue.poll();

                for (int edge: edges[v]) {
                    if (!visited[edge]) {
                        queue.offer(edge);
                        dist[edge] = dist[v] + 1;
                        visited[edge] = true;
                    }
                }
            }

            return dist[dest];
        }
    }


}
