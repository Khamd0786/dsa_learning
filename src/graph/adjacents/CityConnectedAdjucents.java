package graph.adjacents;

import java.util.*;

public class CityConnectedAdjucents {

    public static void main(String[] args) {
        String[] cities = new String[] {"D", "L", "P", "N"};
        Graph grp = new Graph(cities);

        grp.addEdge("D", "L", false);
        grp.addEdge("N", "L", false);
        grp.addEdge("D", "P", false);
        grp.addEdge("P", "N", false);

        grp.print();
    }


    private static class Node {
        String name;
        List<String> nbrs;
        public Node(String name) {
            this.name = name;
            nbrs = new ArrayList<>();
        }
    }

    private static class Graph {
        Map<String, Node> adjs = new HashMap<>();

        public Graph(String[] cities) {
            for (String city: cities) {
                adjs.put(city, new Node(city));
            }
        }

        public void addEdge(String x, String y, boolean isUndirected) {
            adjs.get(x).nbrs.add(y);
            if (isUndirected) {
                adjs.get(y).nbrs.add(x);
            }
        }

        public void print() {

            for (Map.Entry<String, Node> set : adjs.entrySet()) {
                String city = set.getKey();
                Node node = set.getValue();
                System.out.print(city + " --> ");
                for (String nbrs: node.nbrs) {
                    System.out.print(nbrs + ", ");
                }
                System.out.println();
            }
        }
    }
}
