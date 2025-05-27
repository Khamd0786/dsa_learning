package graph.dfs.questions;

import java.util.ArrayList;
import java.util.List;

public class CanVisitAllRooms {

    public static void main(String[] args) {
        int[][] rooms = {
                {1},    // room 0 has key to room 1
                {},     // room 1 has no keys
                {3},    // room 2 has key to room 3, but no one can reach room 2
                {}      // room 3
        };

        System.out.println(canVisitAllRooms(rooms));  // Expected: true
    }

    private static boolean canVisitAllRooms(int[][] rooms) {
        Graph graph = new Graph(rooms.length);

        for (int i = 0; i < rooms.length; i++) {
            for (int j = 0; j < rooms[i].length; j++) {
                graph.addEdges(i, rooms[i][j]);
            }
        }

        return graph.dfs(0);
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

        private void addEdges(int i, int j) {
            addEdges(i, j, false);
        }

        private void addEdges(int i, int j, boolean isUndirected) {
            adjs[i].add(j);
            if (isUndirected) {
                adjs[j].add(i);
            }
        }

        private boolean dfs(int src) {
            boolean[] visited = new boolean[V];
            dfs(src, visited);

            for (boolean v: visited) {
                if (!v) {
                    return false;
                }
            }

            return true;
        }

        private void dfs(int node, boolean[] visited) {
            visited[node] = true;

            for (int nbr: adjs[node]) {
                if (!visited[nbr]) {
                    dfs(nbr, visited);
                }
            }
        }
    }
}
