package subset;

import java.util.Arrays;

public class MazeAllPaths {

    public static void main(String[] args) {

        boolean[][] maze  = {
                {false, false, false},
                {false, false, false},
                {false, false, false},
        };
//        pathAllDirections(maze, 0, 0, "");

        int[][] path = new int[maze.length][maze[0].length];
        pathAllDirection(maze, 0, 0, "", path, 1);
    }

    private static void pathAllDirections(boolean[][] maze, int r, int c, String path) {
        if (r == maze.length  - 1 && c == maze[r].length - 1) {
            System.out.println(path);
            return;
        }

        if (maze[r][c]) {
            return;
        }

        maze[r][c] = true;

        if (r < maze.length - 1) {
            pathAllDirections(maze, r + 1, c, path + 'D');
        }

        if (c < maze[r].length - 1) {
            pathAllDirections(maze, r, c + 1, path + 'R');
        }

        if (r > 0) {
            pathAllDirections(maze, r - 1, c, path + 'U');
        }

        if (c > 0) {
            pathAllDirections(maze, r, c - 1, path + 'L');
        }

        maze[r][c] = false;
    }


    private static void pathAllDirection(boolean[][] maze, int r, int c, String p, int[][] path, int step) {
        if (r  == maze.length - 1 && c ==  maze[r].length - 1) {
            path[r][c] = step;

            for (int[] arr: path) {
                System.out.println(Arrays.toString(arr));
            }
            System.out.println(p);
            System.out.println();
            return;
        }

        if (r < 0 || c < 0 || r > maze.length - 1 || c > maze[r].length - 1 || maze[r][c]) {
            return;
        }

        maze[r][c] = true;
        path[r][c] = step;


        if (r < maze.length - 1) {
            pathAllDirection(maze, r + 1, c,  p + 'D', path, step + 1);
        }

        if (c < maze[r].length - 1) {
            pathAllDirection(maze, r , c + 1, p + 'R', path, step + 1);
        }

        if (r > 0) {
            pathAllDirection(maze, r  - 1, c, p + 'U', path, step + 1);
        }

        if (c > 0) {
            pathAllDirection(maze, r, c - 1, p + 'L', path, step + 1);
        }

        maze[r][c] = false;
        path[r][c] = 0;
    }


}
