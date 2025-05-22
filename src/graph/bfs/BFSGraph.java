package graph.bfs;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class BFSGraph {

    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdge(0, 1, true);
        g.addEdge(1, 2, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 5, true);
        g.addEdge(5, 6, true);
        g.addEdge(4, 5, true);
        g.addEdge(0, 4, true);
        g.addEdge(3,    4, true);

        g.bfs(1);
        g.shortestPath(1, 6);
//        g.printGraph();
    }

    private static class Graph {
        int V;
        List<Integer>[] nbrs;

        public Graph(int v) {
            this.V = v;
            nbrs = new ArrayList[V];

            for (int i = 0; i < V; i++) {
                nbrs[i] = new ArrayList<>();
            }
        }

        public void addEdge(int i, int j, boolean isUndirected) {
            nbrs[i].add(j);
            if (isUndirected) {
                nbrs[j].add(i);
            }
        }

        public void bfs(int source) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[V];

            queue.add(source);
            visited[source] = true;
            while (!queue.isEmpty()) {
                int u = queue.poll();
                System.out.print(u + ", ");

                for (int nbr : nbrs[u]) {
                    if (!visited[nbr]) {
                        queue.offer(nbr);
                        visited[nbr] = true;
                    }
                }
            }

            System.out.println();
            System.out.println("------- BFS Done ----------");
        }


        public void shortestPath(int source, int dest) {
            Queue<Integer> queue = new LinkedList<>();
            boolean[] visited = new boolean[V];

            int[] dist = new int[V];
            int[] parent = new int[V];

            for (int i = 0; i < V; i++) {
                parent[i] = -1;
            }

            queue.offer(source);
            visited[source] = true;

            while (!queue.isEmpty()) {
                int p = queue.poll();

                for (int nbr: nbrs[p]) {
                    if (!visited[nbr]) {
                        queue.offer(nbr);
                        parent[nbr] = p;
                        dist[nbr] = dist[p] + 1;
                        visited[nbr] = true;
                    }
                }
            }

            for (int i = 0; i < V; i++) {
                System.out.println("Shortest dist to " + i + " is " + dist[i]);
            }

            if (dest != -1) {
                int temp = dest;
                while (temp != source) {
                    System.out.print(temp + "-- ");
                    temp = parent[temp];
                }
                System.out.println(source);
            }
        }

        public void printGraph() {
            for (int i = 0; i < V; i++) {
                System.out.print(i + " --> ");
                for (int j : nbrs[i]) {
                    System.out.print(j + ", ");
                }
                System.out.println();
            }
        }
    }
}
