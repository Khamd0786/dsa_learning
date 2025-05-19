package subset;

public class MazeObsticals {

    public static void main(String[] args) {
        //1 path
        //2 position we have to reach
        // any number obstical

        int[][] maze = {
                {1, 1, 1},
                {1, 3, 1},
                {1, 1, 2}
        };

        pathWithObstacles(maze, 0, 0, "");
    }

    private static void pathWithObstacles(int[][] maze, int r, int c, String path) {
        if (r >= maze.length || c >= maze[r].length || (maze[r][c] == 3)) {
            return;
        }

        if (maze[r][c] == 2) {
            System.out.println(path);
            return;
        }

        pathWithObstacles(maze, r + 1, c, path + 'D');
        pathWithObstacles(maze, r, c + 1, path + 'R');
        pathWithObstacles(maze, r + 1, c + 1, path + "dr");
    }


}
