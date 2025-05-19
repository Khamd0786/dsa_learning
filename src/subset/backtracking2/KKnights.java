package subset.backtracking2;

public class KKnights {

    private static int counter = 1;

    public static void main(String[] args) {
        System.out.println(kKnight(3));
    }

    private static int kKnight(int n) {
        counter = 1;
        boolean[][] board = new boolean[n][n];
        return kKnight(board, 0, 0, n);
    }

    private static int kKnight(boolean[][] board, int row, int col, int knights) {
        if (knights == 0) {
            display(board);
            System.out.println();
            return 1;
        }

        int count  = 0;

        if (col == board.length) {
            count += kKnight(board, row + 1, 0, knights);
            return count;
        }

        if (row == board.length) {
            return count;
        }

        if (isValid(board, row, col)) {
            board[row][col] = true;
            count += kKnight(board, row, col + 1, knights - 1);
            board[row][col] = false;
        }

        count += kKnight(board, row, col  + 1, knights);
        return count;
    }

    private static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean isKnight: row) {
                if (isKnight) {
                    System.out.print("K ");
                } else {
                    System.out.print("· ");
                }
            }
            System.out.println();
        }
        System.out.println("--------------------------" + counter++);
    }

    private static boolean isValid(boolean[][] board, int row, int col) {
        int n = board.length - 1;
        //Up Left
        if (row - 1 >= 0 && col - 2 >= 0 && board[row - 1][col - 2]) {
            return false;
        }

        //Up Right
        if (row - 1 >= 0 && col + 2 <= n && board[row - 1][col + 2]) {
            return false;
        }

        //Down Left
        if (row + 1 <= n && col - 2 >= 0 && board[row + 1][col - 2]) {
            return false;
        }

        //Down Right
        if (row + 1 <= n && col + 2 <=  n && board[row + 1][col + 2]) {
            return false;
        }
        //Right Left
        if (row - 2 >= 0 && col + 1 <= n && board[row - 2][col + 1]) {
            return false;
        }

        //Right right
        if (row + 2 <= n && col + 1 <= n && board[row + 2][col + 1]) {
            return false;
        }

        //Left Left
        if (row - 2 >= 0 && col - 1 >= 0 && board[row - 2][col - 1]) {
            return false;
        }

        //Left Right
        if (row + 2 <= n && col - 1 >= 0 && board[row + 2][col - 1]) {
            return false;
        }

        return true;
    }
}
