package graph.MST.questions;

import graph.MST.KruskalMST;
import kotlin.Pair;

import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.PriorityQueue;

public class MinimumSpanningCostPrim {

    public static void main(String[] args) {
        int[][] edges = new int[][] {{1,  2,  7}, {1,  4,  6}, {4 ,2,  9}, {4,  3,  8}, {2,  3, 6}};
        int n = 4;

        int result = MST(n, edges);
        System.out.println("Result-> " + result);
    }

    private static int MST(int n, int[][] edges) {
        Graph graph = new Graph(n + 1);

        for (int[] edge : edges) {
            graph.addEdges(edge[0], edge[1], edge[2]);
        }
        return graph.primsMst();
    }

    private static class Graph {
        List<Pair<Integer, Integer>>[] adjs;
        int v;

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

        public int primsMst() {
            PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getSecond));

            queue.offer(new Pair<>(1, 0));
            boolean[] visited = new boolean[v];

            int totalWeight = 0;

            while (!queue.isEmpty()) {
                Pair<Integer, Integer> pair = queue.poll();

                int node = pair.getFirst();
                int weight = pair.getSecond();

                if (visited[node]) continue;

                totalWeight += weight;
                visited[node] = true;

                for (Pair<Integer, Integer> nbr: adjs[node]) {
                    int nbrNode = nbr.getFirst();
                    int nbrWeight = nbr.getSecond();

                    if (!visited[nbrNode]) {
                        queue.offer(new Pair<>(nbrNode, nbrWeight));
                    }
                }
            }

            return totalWeight;
        }
    }

}
