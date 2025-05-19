package graph.question;

import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;

public class MaximalNetworkRank {

    public static void main(String[] args) {
        int n = 4;  // number of cities (0 to n-1)

        // roads[i] = [a, b] represents a road between city a and city b
        int[][] roads = {
                {0, 1},
                {0, 3},
                {1, 2},
                {1, 3}
        };

        int result = maximalNetworkRank(n, roads);
        System.out.println("Maximal Network Rank is: " + result);
    }


    private static int maximalNetworkRank(int n, int[][] roads) {
        HashSet<Integer>[] graph = new HashSet[n];
        for (int i = 0; i < n; i++) {
            graph[i] = new HashSet<>();
        }

        for (int[] road : roads) {
            int i = road[0];
            int j = road[1];

            graph[i].add(j);
            graph[j].add(i);
        }


        int maxRoadsConn = 0;
        for (int i = 0; i < graph.length; i++) {

            for (int j = 0; j < graph.length; j++) {
                if (i == j) {
                    continue;
                }

                int connCount = graph[i].size() + graph[j].size();

                if (graph[i].contains(j)) {
                    connCount--;
                }

                maxRoadsConn = Math.max(maxRoadsConn, connCount);
            }
        }

        return maxRoadsConn;
    }
}
