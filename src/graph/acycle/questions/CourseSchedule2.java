package graph.acycle.questions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

public class CourseSchedule2 {

    public static void main(String[] args) {
        Graph graph = new Graph(4);

        // Adding edges
        graph.addEdges(0, 1);
        graph.addEdges(0, 2);
        graph.addEdges(1, 3);
        graph.addEdges(2, 3);

        // Solve the graph
        List<Integer> result = graph.solve();

        System.out.println("Path from 0 to leaf:");
        System.out.println(result);
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

        public List<Integer> solve() {

            //make lexicographically sorted

            for (List<Integer> nbrs: adjs) {
                Collections.sort(nbrs);
            }

            List<Integer> path = new ArrayList<>();
            path.add(0);
            return dfs(0, path);
        }

        private List<Integer> dfs(int node, List<Integer> path) {
            if (adjs[node].isEmpty()) {
                return new ArrayList<>(path);
            }

            for (int nbr: adjs[node]) {
                path.add(nbr);
                List<Integer> result = dfs(nbr, path);
                if (!result.isEmpty()) {
                    return result;
                }
                path.removeLast();
            }


            return new ArrayList<>();
        }
    }


}
