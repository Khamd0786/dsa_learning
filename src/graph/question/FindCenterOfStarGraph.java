package graph.question;

import java.util.ArrayList;
import java.util.List;

public class FindCenterOfStarGraph {


    public static void main(String[] args) {
        int[][] gp = new int[][] {
                {1, 2},
                {2, 3},
                {4, 2}
        };

        System.out.println("Center->" + findCenter(gp) );
    }


    //Star Graph, find center
    private static int findCenter(int[][] edges) {
        if (edges.length < 1) {
            return -1;
        }

        int n = edges.length + 1;

        List<List<Integer>> graph = new ArrayList<>();
        for (int i = 0; i <= n; i++) {
            graph.add(new ArrayList<>());
        }

        for (int[] edge : edges) {
            int i = edge[0];
            int j = edge[1];
            graph.get(i).add(j);
            graph.get(j).add(i);
        }

        int maxConnectionSize = 0;
        int node = -1;
        for (int i = 0; i < graph.size(); i++) {
            if (maxConnectionSize < graph.get(i).size()) {
                maxConnectionSize = graph.get(i).size();
                node = i;
            }
        }

        return node;
    }

}
