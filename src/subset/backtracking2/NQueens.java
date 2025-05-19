package subset.backtracking2;

public class NQueens {

    public static void main(String[] args) {
        System.out.println(nQueens(10));
    }

    private static int nQueens(int n) {
        if (n <= 0) {
            return 0;
        }

        boolean[][] board = new boolean[n][n];
        return helper(board, 0);
    }

    private static int helper(boolean[][] board, int row) {
        if (row == board.length) {
            display(board);
            return 1;
        }

        int count = 0;
        for (int col = 0; col < board.length; col++) {
            if (isSafe(board, row, col)) {
                board[row][col] = true;
                count += helper(board, row + 1);
                board[row][col] = false;
            }
        }

        return count;
    }

    private static void display(boolean[][] board) {
        for (boolean[] row : board) {
            for (boolean b : row) {
                if (b) {
                    System.out.print("Q ");
                } else {
                    System.out.print("X ");
                }
            }
            System.out.println();
        }

        System.out.println("--------------------------");
    }

    private static boolean isSafe(boolean[][] board, int row, int col) {
        //check for upper straight line
        for (int i = row; i >= 0; i--) {
            if (board[i][col]) {
                return false;
            }
        }

        int i = row;
        int j = col;
        while (i >= 0 && j >= 0) {
            if (board[i][j]) {
                return false;
            }
            i--;
            j--;
        }

        int l = row;
        int k = col;
        while (l >= 0 && k <= board.length - 1) {
            if (board[l][k]) {
                return false;
            }
            l--;
            k++;
        }

        return true;
    }
}
