package graph.dsu.questions;

import java.util.*;

public class CommunicationBetweenTowers {

    public static void main(String[] args) {
// Test Case 1: Original example
        int[][] matrix1 = {
                {1, 1, 0},
                {0, 0, 1},
                {0, 0, 1}
        };
        System.out.println("Test Case 1: " + solve(matrix1)); // Expected: 2

        // Test Case 2: All towers connected
        int[][] matrix2 = {
                {1, 1},
                {1, 1}
        };
        System.out.println("Test Case 2: " + solve(matrix2)); // Expected: 1

        // Test Case 3: No towers
        int[][] matrix3 = {
                {0, 0},
                {0, 0}
        };
        System.out.println("Test Case 3: " + solve(matrix3)); // Expected: 0

        // Test Case 4: Diagonal towers (not connected)
        int[][] matrix4 = {
                {1, 0},
                {0, 1}
        };
        System.out.println("Test Case 4: " + solve(matrix4)); // Expected: 2

        // Test Case 5: Complex case with multiple groups
        int[][] matrix5 = {
                {1, 0, 1, 0},
                {0, 1, 0, 1},
                {1, 0, 1, 0},
                {0, 1, 0, 1}
        };
        System.out.println("Test Case 5: " + solve(matrix5)); // Expected: 4
    }

    private static int solve(int[][] matrix) {
        DSU dsu = new DSU();
        HashMap<Integer, String> row = new HashMap<>();
        HashMap<Integer, String> col = new HashMap<>();
        List<String> tower = new ArrayList<>();

        for (int i = 0; i < matrix.length; i++) {
            for (int j = 0; j < matrix[0].length; j++) {
                if (matrix[i][j] == 1) {
                    String pos = i + "," + j;
                    tower.add(pos);

                    if (row.containsKey(i)) {
                        dsu.union(row.get(i), pos);
                    } else {
                        row.put(i, pos);
                    }

                    if (col.containsKey(j)) {
                        dsu.union(col.get(j), pos);
                    } else {
                        col.put(j, pos);
                    }
                }
            }
        }

        HashSet<String> root = new HashSet<>();

        for (String t : tower) {
            root.add(dsu.find(t));
        }

        return root.size();
    }

    private static class DSU {
        HashMap<String, String> parent;
        HashMap<String, Integer> rank;

        public DSU() {
            parent = new HashMap<>();
            rank = new HashMap<>();
        }

        public String find(String x) {
            if (!parent.containsKey(x)) {
                parent.put(x, x);
                rank.put(x, 1);
            }

            if (!parent.get(x).equals(x)) {
                parent.put(x, find(parent.get(x)));
            }

            return parent.get(x);
        }

        public void union(String x, String y) {
            String s1 = find(x);
            String s2 = find(y);

            if (s1.equals(s2)) {
                return;
            }

            if (rank.get(s1) < rank.get(s2)) {
                parent.put(s1, s2);
                rank.put(s2, rank.get(s2) + 1);

            } else {
                parent.put(s2, s1);
                rank.put(s1, rank.get(s1) + 1);
            }
        }
    }

}
