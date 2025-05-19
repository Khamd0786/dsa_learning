package graph.adjacents;


import java.util.ArrayList;
import java.util.List;

public class GraphAdjacencyList {


    public static void main(String[] args) {
        Graph g = new Graph(6);

        g.addEdge(0, 1, true);
        g.addEdge(0, 4, true);
        g.addEdge(2, 1, true);
        g.addEdge(3, 4, true);
        g.addEdge(4, 5, true);
        g.addEdge(2, 3, true);
        g.addEdge(3, 5, true);

        g.printGraph();
    }


    private static class Graph {
        int V;
        List<Integer>[] adjList;

        Graph(int v) {
            this.V = v;
            this.adjList = new ArrayList[V];
            for (int i = 0; i < V; i++) {
                this.adjList[i] = new ArrayList<>();
            }
        }

        public void addEdge(int i, int j, boolean undirected) {
            adjList[i].add(j);
            if (undirected) {
                adjList[j].add(i);
            }
        }

        public void printGraph() {
            for (int i = 0; i < V; i++) {
                System.out.print(i + "---> ");
                for (int node: adjList[i]) {
                    System.out.print(node + ", ");
                }
                System.out.println();
            }
        }
    }

}

