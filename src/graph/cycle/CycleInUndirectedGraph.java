package graph.cycle;

import java.util.ArrayList;
import java.util.List;

public class CycleInUndirectedGraph {

    public static void main(String[] args) {
        Graph g = new Graph(4);

        g.addEdges(0, 1, true);
        g.addEdges(1, 2, true);
        g.addEdges(2, 3, true);
        g.addEdges(3, 0, true); // this creates a cycle

        boolean hasCycle = g.detectCycle(0);

        if (hasCycle) {
            System.out.println("Graph contains a cycle.");
        } else {
            System.out.println("Graph does not contain a cycle.");
        }
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

        public boolean detectCycle(int src) {
            boolean[] visited = new boolean[V];
            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    if (dfs(i, -1, visited)) {
                        return true;
                    }
                }
            }
            return false;
        }

        private boolean dfs(int node, int parent, boolean[] visited) {
            visited[node] = true;

            for (int nbr : adjs[node]) {
                if (!visited[nbr]) {
                    if (dfs(nbr, node, visited)) {
                        return true;
                    }
                } else if (nbr != parent) {
                    return true;
                }
            }

            return false;
        }
    }
}
