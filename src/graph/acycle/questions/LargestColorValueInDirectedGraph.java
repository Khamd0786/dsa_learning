package graph.acycle.questions;

import java.util.*;

public class LargestColorValueInDirectedGraph {

    public static void main(String[] args) {
        String colors = "abaca";
        int[][] edges = {{0,1},{0,2},{2,3},{3,4}};

        int result = largestPathValue(colors, edges);
        System.out.println("Largest color value: " + result);
    }

    public static int largestPathValue(String colors, int[][] edges) {
        Graph graph = new Graph(colors.length(), colors);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            graph.addEdges(u, v);
        }

        return graph.findLargestColorValueBFSTopo();
    }


    private static class Graph {
        int V;
        List<Integer>[] adjs;
        String[] clr;

        public Graph(int v, String colors) {
            this.V = v;
            adjs = new ArrayList[V];
            clr = new String[V];

            for (int i = 0; i < V; i++) {
                adjs[i] = new ArrayList<>();
                clr[i] = colors.charAt(i) + "";
            }
        }

        public void addEdges(int i, int j) {
            adjs[i].add(j);
        }

        private int largestPathValue() {
            boolean[] visited = new boolean[V];
            boolean[] stack = new boolean[V];
            int maxColorCount = 0;
            for (int i = 0; i < V; i++) {
                if (visited[i]) {
                    continue;
                }

                HashMap<String, Integer> colorCounter = new HashMap<>();
                int colorCount = dfs(i, stack, visited, colorCounter);
                if (colorCount == -1) {
                    //cycle detected
                    return -1;
                }

                maxColorCount = Math.max(maxColorCount, colorCount);
            }

            return maxColorCount;
        }

        private int dfs(int node, boolean[] stack, boolean[] visited, HashMap<String, Integer> colorCounter) {
            visited[node] = true;
            stack[node] = true;

            String colorNode = clr[node];
            colorCounter.put(colorNode, colorCounter.getOrDefault(colorNode, 0) + 1);

            int maxCount = colorCounter.get(colorNode);

            for (int nbr : adjs[node]) {
                if (stack[nbr]) {//cycle detected
                    return -1;
                }

                if (!visited[nbr]) {
                    HashMap<String, Integer> counter = new HashMap<>(colorCounter);

                    int val = dfs(nbr, stack, visited, counter);
                    if (val == -1) {
                        return -1;
                    }

                    maxCount = Math.max(val, maxCount);
                }
            }

            stack[node] = false;
            return maxCount;
        }

        //using topological sort
        private int findLargestColorValueBFSTopo() {
            int[] indegree = new int[V];
            int[][] dp = new int[V][26];

            for (int i = 0; i < V; i++) {
                for (int nbr: adjs[i]) {
                    indegree[nbr]++;
                }
            }

            Queue<Integer> queue = new LinkedList<>();

            for (int i = 0; i < V; i++) {
                if (indegree[i] == 0) {
                    queue.offer(i);
                }
            }

            int processed = 0;
            int maxCount = 0;

            while (!queue.isEmpty()) {
                int node = queue.poll();
                processed++;

                int colorIdx = clr[node].charAt(0) - 'a';
                dp[node][colorIdx]++;

                maxCount = Math.max(maxCount, dp[node][colorIdx]);

                for (int nbr: adjs[node]) {
                    for (int c= 0; c < 26;c++) {
                        dp[nbr][c] = Math.max(dp[nbr][c], dp[node][c]);
                    }

                    indegree[nbr]--;
                    if (indegree[nbr] == 0) {
                        queue.offer(nbr);
                    }
                }
            }

            if (processed != V) {
                return  -1;
            }

            return maxCount;
        }
    }


}
