package subset.backtracking2;

import subset.Maze2;

public class SudokuSolver {

    public static void main(String[] args) {
        char[][] board = {
                {'9', '5', '.', '.', '4', '.', '.', '.', '1'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '5', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '5', '.', '.', '.'},
                {'.', '.', '.', '.', '6', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '.'},
                {'.', '.', '.', '.', '7', '.', '.', '5', '.'},
                {'.', '.', '.', '.', '.', '.', '.', '.', '2'},
                {'8', '6', '.', '1', '.', '.', '7', '3', '.'}
        };
        aSudokuSolver(board, 0, 0);
    }

    private static boolean aSudokuSolver(char[][] board, int row, int col) {
        if (row == board.length) {
            display(board);
            return true;
        }

        if (col == board.length) {
            return aSudokuSolver(board, row + 1, 0);
        }

        if (board[row][col] != '.') {
            return aSudokuSolver(board, row, col + 1);
        }

        for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(board, row, col, ch)) {
                board[row][col] = ch;
                boolean isFound = aSudokuSolver(board, row, col + 1);
                if (isFound) {
                    return true;
                }
                board[row][col] = '.';
            }
        }

        return false;
    }

    private static void sudokuSolver(char[][] board, int row, int col) {
        if (row == board.length) {
            display(board);
            return;
        }

        if (col == board.length) {
            sudokuSolver(board, row + 1, 0);
            return;
        }

        if (board[row][col] != '.') {
            sudokuSolver(board, row, col + 1);
            return;
        }


        for (char ch = '1'; ch <= '9'; ch++) {
            if (isValid(board, row, col, ch)) {
                board[row][col] = ch;
                sudokuSolver(board, row, col + 1);
                board[row][col] = '.';
            }
        }
    }

    private static boolean isValid(char[][] board, int row, int col, char t) {
        for (char[] chars : board) {
            if (chars[col] == t) {
                return false;
            }
        }

        for (int i  = 0; i < board[row].length; i++) {
            if (board[row][i] == t) {
                return false;
            }
        }

        int sqrtLength = (int) Math.sqrt(board.length);
        int sr = row - row % sqrtLength;
        int sc = col - col % sqrtLength;

        for (int i = sr; i < sr + sqrtLength; i++) {
            for (int j = sc; j < sc + sqrtLength; j++) {
                if (board[i][j] == t) {
                    return false;
                }
            }
        }

        return true;
    }

    private static void display(char[][] board) {
        for (char[] row: board) {
            for (char ch : row) {
                System.out.print( (ch - '0') + " ");
            }
            System.out.println();
        }

        System.out.println("---------------------------");
    }
}
