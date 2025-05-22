package graph.bfs.questions;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class MessageRoute {

    public static void main(String[] args) {
        int n = 5;  // number of computers

        int[][] edges = {
                {1, 2},
                {2, 3},
                {3, 4},
                {4, 5}
        };

        int result = messageRoute(n, edges);
        System.out.println("Minimum number of computers on route: " + result);

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

        private void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
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
                int f = queue.poll();

                if (f == dest) {
                    return dist[dest] + 1; //add one to get the computers visited, not the routes.
                }

                for (int nbr : adjs[f]) {
                    if(!visited[nbr]) {
                        queue.offer(nbr);
                        dist[nbr] = dist[f] + 1;
                        visited[nbr] = true;
                    }
                }
            }

            return -1;
        }
    }

    private static int messageRoute(int n, int[][] edges) {
        Graph graph = new Graph(n + 1);
        for (int[] edge: edges) {
            int f = edge[0];
            int s = edge[1];

            graph.addEdges(f, s, true);
        }

        return graph.minCostBFS(1, n);
    }
}
