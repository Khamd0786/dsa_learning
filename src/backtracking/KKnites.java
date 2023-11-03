package backtracking;

public class KKnites {
	
	public static void main(String[] args) {
		int n = 2;
		boolean[][] board = new boolean[n][n];
		System.out.println(knites(board, 0, 0, n));
	}
	
	private static int knites(boolean[][] board, int row, int col, int knitesCount) {
		if (knitesCount == 0) {
			display(board);
			System.out.println();
			return 1;
		}
		
		if (row >= board.length - 1 && col >= board.length) {
			return 0;
		}
		
		int count = 0;
		if (col == board.length) {
			count += knites(board, row + 1, 0, knitesCount);
			return count;
		}
		
		if (isSafe(board, row, col)) {
			board[row][col] = true;
			count += knites(board, row, col + 1, knitesCount - 1);
			board[row][col] = false;
		}
		
		count += knites(board, row, col + 1, knitesCount);
		
		return count;
	}
	
	private static boolean isSafe(boolean board[][], int row, int col) {
		//top-left
		if (row >= 2 && col >= 1 && board[row-2][col-1]) {
			return false;
		}
		
		//top-right
		if (row >= 2 && col < board.length - 1 && board[row-2][col+1]) {
			return false;
		}
		
		//left-top 
		if (col >= 2 && row >= 1 && board[row-1][col-2]) {
			return false;
		}
		
		//right-top
		if (row >= 1 && col < board.length - 2 && board[row-1][col+2]) {
			return false;
		}
		
		return true;
	}
	
	private static void display(boolean[][] rows) {
		for (boolean[] row : rows) {
			for (boolean element: row) {
				if (element) {
					System.out.print("K ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}
