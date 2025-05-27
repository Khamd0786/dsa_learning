package graph.dfs.questions;

import java.util.ArrayList;
import java.util.List;

/**
 * Largest Island
 * You are given a m x n binary matrix grid. An island is a group of 1's
 * (representing land) connected 4-directionally (horizontal or vertical.)
 * You may assume all four edges of the grid are surrounded by water.
 * <p>
 * The area of an island is the number of cells with a value 1 in the island.
 * <p>
 * Return the maximum area of an island in grid. If there is no island, return 0.
 * <p>
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m, n <= 50
 * <p>
 * grid[i][j] is either 0 or 1.
 * <p>
 * Sample Input
 * grid = [
 * [1, 0, 0, 1, 0],
 * [1, 0, 1, 0, 0],
 * [0, 0, 1, 0, 1],
 * [1, 0, 1, 1, 1],
 * [1, 0, 1, 1, 0]
 * ]
 * Sample Output: 8
 * <p>
 * Explanation
 * There are 4 islands (connected components) of sizes 2,1,8,2 out of which 8 is largest.
 */
public class LargestIsland {

    public static void main(String[] args) {
        int[][] grid = {
                {1, 0, 0, 1, 0},
                {1, 0, 1, 0, 0},
                {0, 0, 1, 0, 1},
                {1, 0, 1, 1, 1},
                {1, 0, 1, 1, 0}
        };

        int maxArea = maxAreaIfIsland(grid);
        System.out.println("Maximum area of an island is: " + maxArea);
    }

    private static int maxAreaIfIsland(int[][] grid) {
        boolean[][] visited = new boolean[grid.length][grid[0].length];
        int maxArea = 0;
        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                maxArea = Math.max(helper(grid, visited,i, j), maxArea);
            }
        }

        return maxArea;
    }



    private static int helper(int[][] grid, boolean[][] visited, int i, int j) {
        if (i < 0 || i >= grid.length || j < 0 || j >= grid[i].length) {
            return 0;
        }

        if (grid[i][j] == 0) {
            return 0;
        }

        if (visited[i][j]) {
            return 0;
        }

        visited[i][j] = true;

        int top = helper(grid, visited, i - 1, j);
        int bottom = helper(grid, visited, i + 1, j);
        int left = helper(grid, visited, i, j - 1);
        int right = helper(grid, visited, i, j + 1);

        return top + bottom + left + right + 1;
    }
}
