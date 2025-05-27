package graph.cycle;

import java.util.ArrayList;
import java.util.List;

public class CycleInDirectedGraph {
    public static void main(String[] args) {
        Graph g = new Graph(4);  // Number of vertices: 4

        g.addEdges(0, 1);
        g.addEdges(1, 2);
        g.addEdges(2, 3);
        g.addEdges(3, 1);  // This creates a cycle: 1 → 2 → 3 → 1

        if (g.detectCycle()) {
            System.out.println("Cycle Detected in Directed Graph");
        } else {
            System.out.println("No Cycle Found");
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

        void addEdges(int i, int j) {
            adjs[i].add(j);
        }

        boolean detectCycle() {
            boolean[] visited = new boolean[V];
            boolean[] stack = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    if (dfs(i, visited, stack)) {
                        return true;
                    }
                }
            }

            return false;
        }

        boolean dfs(int node, boolean[] visited, boolean[] stack) {
            visited[node] = true;

            for (int nbr: adjs[node]) {
                if (!visited[nbr]) {
                    stack[nbr] = true;
                    if (dfs(nbr, visited, stack)) {
                        return true;
                    }
                    stack[nbr] = false;
                } else if (visited[nbr] && stack[nbr]) {
                    return true;
                }
            }

            return false;
        }
    }
}
