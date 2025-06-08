package graph.acycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;

public class TopologicalSortDFS {

    public static void main(String[] args) {
        Graph graph = new Graph(6);

        // adding edges:
        graph.addEdges(5, 2);
        graph.addEdges(5, 0);
        graph.addEdges(4, 0);
        graph.addEdges(4, 1);
        graph.addEdges(2, 3);
        graph.addEdges(3, 1);

        System.out.print("Topological Sort of the graph: ");
        LinkedList<Integer> result = graph.topologicalSort();

        for (Integer integer : result) {
            System.out.print(integer + ", ");
        }
        System.out.println();
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

        public LinkedList<Integer> topologicalSort() {
            LinkedList<Integer> result = new LinkedList<>();
            boolean[] visited = new boolean[V];

            for (int i = 0; i < V; i++) {
                if (!visited[i]) {
                    dfs(i, result, visited);
                }
            }

            return result;
        }

        public void dfs(int node, LinkedList<Integer> result, boolean[] visited) {
            visited[node] = true;
            for (int nbr: adjs[node]) {
                if (!visited[nbr]) {
                    dfs(nbr, result, visited);
                }
            }
            result.addFirst(node);
        }

    }
}
