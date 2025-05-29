package graph.cycle.questions;

import java.util.ArrayList;
import java.util.List;

public class CourseSchedule {


    public static void main(String[] args) {
        // Example 1: No cycle
        int[][] prerequisites1 = {{1, 0}, {2, 1}, {3, 2}};
        System.out.println("Can finish courses (Example 1): " + canFinish(prerequisites1, 4));  // Expected: true

        // Example 2: With a cycle (3 -> 2 -> 3)
        int[][] prerequisites2 = {{1, 0}, {2, 1}, {3, 2}, {2, 3}};
        System.out.println("Can finish courses (Example 2): " + canFinish(prerequisites2, 4));  // Expected: false
    }

    private static boolean canFinish(int[][] prerequisites, int numCourses) {
        Graph graph = new Graph(numCourses);

        for (int[] prerequisite : prerequisites) {
            graph.addEdges(prerequisite[0], prerequisite[1]);
        }

        return graph.canFinish(0);
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

        public boolean canFinish(int src) {
            boolean[] visited = new boolean[V];
            boolean[] stack = new boolean[V];

            for (int i = src; i < V; i++) {
                if (!visited[i]) {
                    if (!dfs(i, visited, stack)) {
                        return false;
                    }
                }
            }
            return true;
        }

        private boolean dfs(int node, boolean[] visited, boolean[] stack) {
            visited[node] = true;
            stack[node] = true;

            for (int nbr : adjs[node]) {
                if (!visited[nbr]) {
                    if (!dfs(nbr, visited, stack)) {
                        return false;
                    }
                } else if (visited[nbr] && stack[nbr]) {
                    return false;
                }
            }

            stack[node] = false;
            return true;
        }
    }
}
