package graph.acycle;

import java.util.ArrayList;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;

public class TopologicalSortBFS {

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
        graph.topologicalSort();
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

        public void topologicalSort() {
            //creating in degree of every nbr
            int[] indegree = new int[V];

            for (int i = 0; i < V; i++) {
                for (int nbr: adjs[i]) {
                    indegree[nbr]++;
                }
            }

            // Providing initial 0 value indegree to the queue
            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < V; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            while (!queue.isEmpty()) {
                int node = queue.poll();

                System.out.print(node + ", ");

                for (int nbr: adjs[node]) {
                    indegree[nbr]--;
                    if (indegree[nbr] == 0) {
                        queue.offer(nbr);
                    }
                }
            }
        }
    }
}
