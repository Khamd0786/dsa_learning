package graph.dsu.questions;

import kotlin.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class ForestDetection {

    public static void main(String[] args) {
        int[][] edges1 = {
                {1, 2},
                {2, 3},
                {4, 5}
        };

        int[][] edges2 = {
                {1, 2},
                {2, 3},
                {3, 1}
        };

        System.out.println("Is Forest (edges1): " + solve(edges1));  // true
        System.out.println("Is Forest (edges2): " + solve(edges2));  // false
    }

    private static boolean solve(int[][] edges) {
        DSU dsu = new DSU(edges.length);

        for (int[] edge: edges) {
            int u = edge[0];
            int v = edge[1];

            boolean isTree = dsu.unionSet(u, v);
            if (!isTree) {
                return false;
            }
        }

        return  true;
    }

    private static class DSU {
        int V;
        int[] parents;

        public DSU(int v) {
            this.V = v;
            parents = new int[100005];

            Arrays.fill(parents, -1);
        }

        private int findSet(int i) {
            if (parents[i] == -1) {
                return i;
            }

            return parents[i] = findSet(parents[i]);
        }

        private boolean unionSet(int i, int j) {
            int s1 = findSet(i);
            int s2 = findSet(j);

            if (s1 != s2) {
                parents[s2] = s1;
            } else {
                return false;
            }

            return true;
        }
    }
}
