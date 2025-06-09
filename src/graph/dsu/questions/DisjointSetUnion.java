package graph.dsu.questions;


import kotlin.Pair;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Disjoint Set Union
 * Implement disjoint sets union data structure. You have to perform queries of two types:
 * 1 u v &mdash; unite two sets that contain u and v , respectively;
 * 2 u v &mdash; check that two elements u and v belong to the same set.
 * You will be given an array of query, each query contains three integer query type x , u, and v .
 * Output a vector containing answer for each type 2 query True
 * if u and v belong to the same set otherwise false  respectively.
 * <p>
 * Constraints:
 * 1<=u, v<=10^5
 * Example:
 * Input: query = [[1,1,2],[1,1,3],[2,1,4],[2,2,3]]
 * Output: {false, true}
 */
public class DisjointSetUnion {
    public static void main(String[] args) {
        int[][] query = {
                {1, 1, 2},
                {1, 1, 3},
                {2, 1, 4},
                {2, 2, 3}
        };

        List<Boolean> result = DSU(query);
        System.out.println(result);  // Output: [false, true]
    }

    private static List<Boolean> DSU(int[][] query) {
        List<Boolean> result = new ArrayList<>();
        Graph graph = new Graph(100005);

        for (int[] q : query) {
            int x = q[0];
            int u = q[1];
            int v = q[2];

            if (x == 1) {
                graph.unionSet(u, v);
            } else {
                result.add(graph.checkSameSet(u, v));
            }
        }

        return result;
    }

    private static class Graph {
        int V;
        int[] parents;


        public Graph(int v) {
            this.V = v;
            parents = new int[V];
            Arrays.fill(parents, -1);
        }

        private int findSet(int i) {
            if (parents[i] == -1) {
                return i;
            }

            return findSet(parents[i]);
        }

        private void unionSet(int i, int j) {
            int s1 = findSet(i);
            int s2 = findSet(j);

            if (s1 != s2) {
                parents[s2] = s1;
            }
        }

        private boolean checkSameSet(int i, int j) {
            return findSet(i) == findSet(j);
        }
    }
}
