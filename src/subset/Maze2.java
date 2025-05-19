package subset;

import java.util.List;

public class Maze2 {

    public static void main(String[] args) {
        boolean[][] maze = {
                {false, false, false},
                {false, false, false},
                {false, false, true},
        };

        allPossiblePaths(maze, 0, 0, "");
        System.out.println("Count -> " + mazeCount(maze, 0, 0));

        pathDiagonal(maze, 0, 0, "");
    }

    private static void  allPossiblePaths(boolean[][] maze, int r, int c, String path) {

        if (r >= maze.length || c >= maze[r].length) {
            return;
        }

        if (maze[r][c]) {
            System.out.println(path);
            return;
        }

        allPossiblePaths(maze, r + 1, c, path + 'D');
        allPossiblePaths(maze, r, c + 1, path + 'R');
    }

    private static int mazeCount(boolean[][] maze, int r, int c) {

        if (r >= maze.length || c >= maze[r].length) {
            return 0;
        }

        if (maze[r][c]) {
            return 1;
        }

        int count = 0;
        count += mazeCount(maze, r + 1, c);
        count += mazeCount(maze, r, c + 1);

        return count;
    }

    private static void pathDiagonal(boolean[][] maze, int r, int c, String path) {
        if (r >= maze.length || c >= maze[r].length) {
            return;
        }

        if (maze[r][c]) {
            System.out.println(path);
            return;
        }

        pathDiagonal(maze, r + 1, c, path + 'D');
        pathDiagonal(maze, r, c + 1, path + 'R');
        pathDiagonal(maze, r + 1, c+ 1, path + "dg");
    }

}
