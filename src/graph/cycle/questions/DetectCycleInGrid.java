package graph.cycle.questions;


/**
 * Detect Cycles in Grid
 * Given a 2D array of characters grid of size m x n,
 * you need to find if there exists any cycle consisting of the same value in grid.
 * A cycle is a path of length 4 or more in the grid that starts and ends at the same cell.
 * From a given cell, you can move to one of the cells adjacent to it - in
 * one of the four directions (up, down, left, or right), if it has the same value as the current cell.
 * Also, you cannot move to the cell that you visited in your last move.
 * For example, the cycle (1, 1) -> (1, 2) -> (1, 1) is invalid because from (1, 2) we visited (1, 1) which was the last visited cell.
 * Return true if any cycle of the same value exists in grid, otherwise, return false.
 *
 * Constraints:
 * m == grid.length
 * n == grid[i].length
 * 1 <= m <= 500
 * 1 <= n <= 500
 * grid consists only of lowercase English letters.*/
public class DetectCycleInGrid {

    public static void main(String[] args) {
        char[][] grid1 = {
                {'a', 'a', 'a'},
                {'a', 'b', 'a'},
                {'a', 'a', 'a'}
        };

        System.out.println(containsCycle(grid1)); // true

        char[][] grid2 = {
                {'c', 'c', 'd'},
                {'c', 'd', 'c'},
                {'d', 'c', 'c'}
        };

        System.out.println(containsCycle(grid2)); // false
    }

    private static boolean containsCycle(char[][] grid) {
        if (grid.length < 1) {
            return false;
        }
        int m = grid.length;
        int n = grid[0].length;
        boolean[][] visited = new boolean[m][n];
        int[] dx = new int[]{0, 0, 1, -1};
        int[] dy = new int[]{-1, 1, 0, 0};

        for (int i = 0; i < grid.length; i++) {
            for (int j = 0; j < grid[i].length; j++) {
                if (!visited[i][j]) {
                    if (dfs(i, j, -1, -1, grid[i][j], visited, grid, dx, dy)) {
                        return true;
                    }
                }
            }
        }

        return false;
    }

    private static boolean dfs(int x, int y, int fromX, int fromY, char target, boolean[][] visited, char[][] grid, int[] dx, int[] dy) {
        visited[x][y] = true;

        for (int d = 0; d < 4; d++) {
            int newX = x + dx[d];
            int newY = y + dy[d];

            if (newX > 0 && newY > 0 && newX < grid.length && newY < grid[newX].length) {
                if (grid[newX][newY] == target) {
                    if (!visited[newX][newY]) {
                        if (dfs(newX, newY, x, y, target, visited, grid, dx, dy)) {
                            return true;
                        }
                    } else if (newX != fromX && newY != fromY) {
                        return true;
                    }
                }
            }
        }
        return false;
    }

}
