package graph.MST;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class PrimsMST {

    public static void main(String[] args) {
        Graph g = new Graph(5);

        g.addEdges(0, 1, 2);
        g.addEdges(0, 3, 6);
        g.addEdges(1, 2, 3);
        g.addEdges(1, 3, 8);
        g.addEdges(1, 4, 5);
        g.addEdges(2, 4, 7);
        g.addEdges(3, 4, 9);

        int minCost = g.primMST();
        System.out.println("Minimum Total Cost of MST: " + minCost);
    }

    private static class Graph {
        int v;
        List<Pair<Integer, Integer>>[] adjs;

        public Graph(int v) {
            this.v = v;
            adjs = new ArrayList[v];

            for (int i = 0; i < v; i++) {
                adjs[i] = new ArrayList<>();
            }
        }

        public void addEdges(int i, int j, int w) {
            adjs[i].add(new Pair<>(j, w));
            adjs[j].add(new Pair<>(i, w));
        }

        public int primMST() {
            PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getSecond));
            int totalCost = 0;
            queue.offer(new Pair<>(0, 0));

            boolean[] visited = new boolean[v];

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pair = queue.poll();
                int node = pair.getFirst();
                int weight = pair.getSecond();

                if (visited[node]) continue;

                totalCost += weight;
                visited[node] = true;

                for (Pair<Integer, Integer> nbrPair : adjs[node]) {
                    int nbrNode = nbrPair.getFirst();
                    int nbrWeight = nbrPair.getSecond();

                    if (!visited[nbrNode]) {
                        queue.offer(new Pair<>(nbrNode, nbrWeight));
                    }
                }
            }

            return totalCost;
        }
    }
}
