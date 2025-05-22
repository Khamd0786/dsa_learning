package graph.bfs.questions;

import java.util.*;
import java.util.jar.JarEntry;

public class ValidBFS {

    public static void main(String[] args) {
        int n = 5;

        int[][] edges = {
                {1, 2},
                {1, 3},
                {3, 4},
                {3, 5}
        };

        // Valid BFS sequence example
        int[] sequence = {1, 3, 2, 4, 5};

        boolean result = isValidBFS(n, sequence, edges);
        System.out.println("Is valid BFS? " + result);
    }

    private static boolean isValidBFS(int n, int[] sequence, int[][] edges) {
        if (edges.length < 1) {
            return true;
        }

        Graph graph = new Graph(n + 1);

        for (int[] edge: edges) {
            graph.addEdges(edge[0], edge[1]);
        }

        return graph.isValidBFS(edges[0][0], sequence);
    }

    private static class Graph {
        int V;
        HashSet<Integer>[] adjs;

        public Graph(int v) {
            this.V = v;
            adjs = new HashSet[V];

            for (int i = 0; i < V; i++) {
                adjs[i] = new HashSet<>();
            }
        }

        public void addEdges(int i, int j) {
            addEdges(i, j, true);
        }

        public void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
            }
        }

        public boolean isValidBFS(int src, int[] sequence) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[V];

            queue.offer(src);
            visited[src] = true;
            int s = 1;
            while (!queue.isEmpty()) {
                int f = queue.poll();
                HashSet<Integer> neigbours = adjs[f];

                HashSet<Integer> unvisitedNeigbours = new HashSet<>();
                for (int nbr: neigbours) {
                    if (!visited[nbr]) {
                        unvisitedNeigbours.add(nbr);
                    }
                }

                int e = s + unvisitedNeigbours.size() - 1;

                if (e >= sequence.length) {
                    return false;
                }

                if (!contains(sequence, s, s + unvisitedNeigbours.size() - 1, neigbours)) {
                    return false;
                }

                for (int nbrs : unvisitedNeigbours) {
                    queue.offer(nbrs);
                    visited[nbrs] = true;
                }

                s = e + 1;
            }

            return s == sequence.length;
        } /* O(VlogV + E) */

        private boolean contains(int[] sequnce, int s, int e, HashSet<Integer> nbrs) {
            for (int i = s; i <= e; i++) {
                if (!nbrs.contains(sequnce[i])) {
                    return false;
                }
            }
            return true;
        }
    }

}
