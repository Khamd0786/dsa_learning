package graph.MST.questions;

import kotlin.Pair;

import java.util.Comparator;
import java.util.HashSet;
import java.util.PriorityQueue;

public class BuildRoads {

    public static void main(String[] args) {
        long[][] coordinates = {{8, 3}, {4, 9}, {12, 19}, {18, 1}, {13, 5}, {7, 6}};

        long result = buildRoads(coordinates);
        System.out.println("Minimum total cost: " + result);
    }

    private static long buildRoads(long[][] coordinates) {
        //min cost = min((xi - xj), (yi - yj))
        long totalCost = 0;
        PriorityQueue<Pair<Integer, Long>> queue = new PriorityQueue<>(Comparator.comparing(Pair::getSecond));
        HashSet<Integer> visited = new HashSet<>();
        queue.offer(new Pair<>(0, 0L));
        while (!queue.isEmpty()) {
            Pair<Integer, Long> item = queue.poll();
            int node = item.getFirst();
            long weight = item.getSecond();

            if (visited.contains(node)) continue;

            visited.add(node);
            totalCost += weight;

            for (int nbrNode = 0; nbrNode < coordinates.length; nbrNode++) {
                if (!visited.contains(nbrNode)) {
                    long nbrWeight = Math.min(
                            Math.abs(coordinates[nbrNode][0] - coordinates[node][0]),
                            Math.abs(coordinates[nbrNode][1] - coordinates[node][1])
                    );

                    queue.offer(new Pair<>(nbrNode, nbrWeight));
                }
            }
        }

        return totalCost;
    }
}
