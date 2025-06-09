package graph.dsu.questions;

import java.util.Arrays;

public class UselessConnection {

    public static void main(String[] args) {
        int[][] edges = {
                {1, 2}, {2, 3}, {3, 4}, {1, 4}, {1, 5}
        };

        int[] res = findUselessConnection(edges);
        if (res.length != 0) {
            System.out.println("Useless connection (cycle edge): " + Arrays.toString(res));
        } else {
            System.out.println("No cycle found.");
        }
    }

    private static int[] findUselessConnection(int[][] edges) {
        int[] parents = new int[edges.length + 1];
        Arrays.fill(parents, -1);

        for (int[] edge : edges) {
            int u = edge[0];
            int v = edge[1];

            if (!unionSet(u, v, parents)) {
                return new int[]{u, v}; //as we have to return 2
            }
        }

        return new int[0];
    }

    private static int findSet(int i, int[] parents) {
        if (parents[i] == -1) {
            return i;
        }

        return parents[i] = findSet(parents[i], parents);
    }

    private static boolean unionSet(int u, int v, int[] parents) {
        int s1 = findSet(u, parents);
        int s2 = findSet(v, parents);

        if (s1 != s2) {
            parents[s2] = s1;
        } else {
            return false;
        }

        return true;
    }
}
