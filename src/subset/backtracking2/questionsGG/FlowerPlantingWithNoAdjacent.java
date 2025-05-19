package subset.backtracking2.questionsGG;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;

public class FlowerPlantingWithNoAdjacent {

    public static void main(String[] args) {
        int[][] paths = {{1, 2}, {2, 3}, {3, 1}};
        int[] arr = gardenNoAdj(3, paths);
        System.out.println(Arrays.toString(arr));
    }

    public static int[] gardenNoAdj(int n, int[][] paths) {
        HashMap<Integer, List<Integer>> graph = new HashMap<>();

        for (int i = 0; i < n; i++) {
            graph.put(i, new ArrayList<>());
        }

        for (int[] path : paths) {
            int u = path[0] - 1;
            int v = path[1] - 1;
            graph.get(u).add(v);
            graph.get(v).add(u);
        }

        int[] arr = new int[n];
        gardenNoAdj(0, arr, graph);
        return arr;
    }

    public static boolean gardenNoAdj(int i, int[] colors, HashMap<Integer, List<Integer>> graph) {
        if (i == colors.length) {
            return true;
        }

        List<Integer> neighbors = graph.get(i);
        boolean canAssign = true;

        for (int m = 1; m <= 4; m++) {
            for (Integer neighbor : neighbors) {
                if (colors[neighbor] == m) {
                    canAssign = false;
                    break;
                }
            }

            if (canAssign) {
                colors[i] = m;
                boolean isSolved = gardenNoAdj(i + 1, colors, graph);
                if (isSolved) {
                    return true;
                }
                colors[i] = 0;
            }
        }

        return false;
    }


}
