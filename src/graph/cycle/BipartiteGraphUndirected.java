package graph.cycle;

import java.util.ArrayList;
import java.util.List;

public class BipartiteGraphUndirected {

    public static void main(String[] args) {
        Graph graph = new Graph(5);

        // Example 1: Bipartite
        graph.addEdges(0, 1, true);
        graph.addEdges(1, 2, true);
        graph.addEdges(2, 3, true);
        graph.addEdges(3, 4, true);

        System.out.println("Is Bipartite (Example 1): " + graph.isBipartite(0));  // Expected: true

        // Example 2: Not Bipartite (add a cycle of odd length)
        Graph graph2 = new Graph(3);
        graph2.addEdges(0, 1, true);
        graph2.addEdges(1, 2, true);
        graph2.addEdges(2, 0, true);

        System.out.println("Is Bipartite (Example 2): " + graph2.isBipartite(0)); // Expected: false

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

        public void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
            }
        }

        private boolean isBipartite(int src) {
            int[] visited = new int[V];

            for (int i = src; i < V; i++) {
                if (visited[i] == 0) {
                    if (!dfs(i, -1, 1,  visited)) {
                        return false;
                    }
                }
            }

            return true;
        }

        private boolean dfs(int node, int parent, int color, int[] visited) {
            visited[node] = color;

            for (int nbr: adjs[node]) {
                if (visited[nbr] == 0) {
                    if (!dfs(nbr, node, 3-color, visited)) {
                        return false;
                    }
                } else if (nbr != parent && visited[nbr] == color) {
                    return false;
                }
            }

            return true;
        }
    }
}
