package graph.dfs.questions;

import java.util.*;

public class ReconstructItinerary {

    public static void main(String[] args) {
        String[][] tickets = new String[][]{
                {"MUC", "LHR"},
                {"JFK", "MUC"},
                {"SFO", "SJC"},
                {"LHR", "SFO"}
        };

        List<String> itinerary = findItinerary(tickets);

        for (String s : itinerary) {
            System.out.print(s + ", ");
        }

    }

    private static List<String> findItinerary(String[][] tickets) {
        Graph graph = new Graph();

        for (String[] ticket : tickets) {
            graph.addEdges(ticket[0], ticket[1]);
        }

        return graph.reconstructItinerary("JFK");
    }

    private static class Graph {
        private HashMap<String, List<String>> adjs;
        private HashMap<String, boolean[]> edgeVisited;

        public Graph() {
            adjs = new HashMap<>();
            edgeVisited = new HashMap<>();
        }

        private void addEdges(String s1, String s2) {
            adjs.putIfAbsent(s1, new ArrayList<>());
            adjs.get(s1).add(s2);
        }

        public List<String> reconstructItinerary(String src) {
            //sort all paths
            //write sort logic here
            for (Map.Entry<String, List<String>> nbr : adjs.entrySet()) {
                Collections.sort(nbr.getValue());
                edgeVisited.putIfAbsent(nbr.getKey(), new boolean[nbr.getValue().size()]);
            }

            List<String> result = new ArrayList<>();

            dfs(src, result);

            return result;
        }

        private boolean dfs(String node, List<String> result) {
            result.add(node);

            if (result.size() >= edgeVisited.size() + 1) {
                return true;
            }

            List<String> nbrs = adjs.getOrDefault(node, new ArrayList<>());
            boolean[] visited = edgeVisited.getOrDefault(node, new boolean[0]);

            for (int i = 0; i < nbrs.size(); i++) {
                if (!visited[i]) {
                    visited[i] = true;
                    if (dfs(nbrs.get(i), result)) {
                        return true;
                    }
                    visited[i] = false;
                }
            }

            result.removeLast();
            return false;
        }
    }

}
