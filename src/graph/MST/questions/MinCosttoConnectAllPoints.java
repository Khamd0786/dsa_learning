package graph.MST.questions;

import kotlin.Pair;

import java.util.*;

/**
 * You are given an array points
 * representing integer coordinates of some
 * points on a 2D-plane, where points[i] = [xi, yi].
 * <p>
 * The cost of connecting two points [xi, yi] and [xj, yj]
 * is the manhattan distance between them: |xi - xj| + |yi - yj|,
 * where |val| denotes the absolute value of val.
 * <p>
 * Return the minimum cost to make all points connected.
 * All points are connected if there is exactly one simple path between any two points.
 */
public class MinCosttoConnectAllPoints {

    public static void main(String[] args) {
        int[][] points = {
                {0, 0},
                {2, 2},
                {3, 10},
                {5, 2},
                {7, 0}
        };

        int result = minCostConnectPoints(points);
        System.out.println("Minimum cost to connect all points: " + result);
    }

    public static int minCostConnectPoints(int[][] points) {
        PriorityQueue<Pair<Integer, Integer>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getSecond));
        boolean[] visited = new boolean[points.length];
        int totalWeight = 0;

        queue.offer(new Pair<>(0, 0));
        while (!queue.isEmpty()) {
            Pair<Integer, Integer> pair = queue.poll();
            int node = pair.getFirst();
            int weight = pair.getSecond();

            if (visited[node]) continue;

            totalWeight += weight;
            visited[node] = true;

            for (int i = 0; i < points.length; i++) {
                if (!visited[i]) {
                    int w = Math.abs(points[node][0] - points[i][0]) + Math.abs(points[node][1] - points[i][1]);
                    queue.offer(new Pair<>(i, w));
                }
            }
        }

        return totalWeight;
    }

}
