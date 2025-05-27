package graph.dfs;

import graph.bfs.BFSGraph;

import java.util.ArrayList;
import java.util.List;

public class DFSGraph {
    public static void main(String[] args) {
        Graph g = new Graph(7);

        g.addEdges(0, 1, true);
        g.addEdges(1, 2, true);
        g.addEdges(2, 3, true);
        g.addEdges(3, 5, true);
        g.addEdges(5, 6, true);
        g.addEdges(4, 5, true);
        g.addEdges(0, 4, true);
        g.addEdges(3,    4, true);

        g.dfs(1);
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

        private void dfs(int src) {
            boolean[] visited = new boolean[V];
            dfsHelper(src, visited);
            System.out.println();
        }

        private void dfsHelper(int node, boolean[] visited) {
            visited[node] = true;
            System.out.print(node + ", ");
            for (int nbr: adjs[node]) {
                if (!visited[nbr]) {
                    dfsHelper(nbr, visited);
                }
            }
        }
    }
}
