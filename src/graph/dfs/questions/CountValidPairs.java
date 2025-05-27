package graph.dfs.questions;

import java.util.*;

public class CountValidPairs {

    public static void main(String[] args) {
        int N = 5;
        int[][] astronauts = {
                {0, 1},
                {2, 3},
                {0, 4}
        };

        int result = countPairs(N, astronauts);
        System.out.println("Number of valid pairs: " + result);
    }

    private static int countPairs(int N, int[][] astronauts) {
        Graph graph = new Graph(N);

        for (int[] ast: astronauts) {
            int u = ast[0];
            int v = ast[1];
            graph.addEdges(u, v, true);
        }

        return graph.numberOfPairs();
    }

    private static class Graph {
        private int size;
        private List<Integer>[] adjs;

        public Graph(int v) {
            this.size = v;
            adjs = new ArrayList[size];

            for (int i = 0; i < size; i++) {
                adjs[i] = new ArrayList<>();
            }
        }

        public void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
            }
        }

        public int numberOfPairs() {
            boolean[] visited = new boolean[size];
            List<Integer> groupsCount = new ArrayList<>();

            for (int i = 0; i < size; i++) {
                if (!visited[i]) {
                    int groupCount = dfs(i, visited);
                    groupsCount.add(groupCount);
                }
            }

            int totalPairs = 0;
            int sum = 0;

            for (int g : groupsCount) {
                totalPairs += g * sum;
                sum += g;
            }

            return totalPairs;
        }

        private int dfs(int node, boolean[] visited) {
            visited[node] = true;
            int count = 1;
            for (int nbr: adjs[node]) {
                if (!visited[nbr]) {
                    count += dfs(nbr, visited);
                }
            }

            return count;
        }

    }
}
