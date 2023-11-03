package backtracking;

public class NBiceps {
	public static void main(String[] args) {
		
		int n = 4;
		boolean[][] board = new boolean[n][n];
		System.out.println(bicepsAlgo(board, 0, 0, n));
	} 
	
	private static int bicepsAlgo(boolean[][] board, int row, int col, int biceps) {
		if (biceps == 0) {
			display(board);
			System.out.println();
			return 1;
		}
		
		if (row >= board.length - 1 && col >= board.length) {
			return 0;
		}
		
		int count = 0;
		if (col == board.length) {
			count += bicepsAlgo(board, row + 1, 0, biceps);
			return count;
		}
		
		
		if (isSafe(board, row, col)) {
			board[row][col] = true;
			count += bicepsAlgo(board, row, col + 1, biceps - 1);
			board[row][col] = false;
		}
		
		count += bicepsAlgo(board, row, col + 1, biceps);

		return count;
	}
	
	
	
	private static boolean isSafe(boolean[][] board, int row, int col) {
		//check the left upper diagonal
		int maxLeft = Math.min(row, col);
		for (int i = 1; i <= maxLeft; i++) {
			if (board[row - i][col - i]) {
				return false;
			}
		}
		
		int maxRight = Math.min(row, board.length - col - 1);
		for (int i = 1; i <= maxRight; i++) {
			if (board[row-i][col+i]) {
				return false;
			}
		}
		
		return true;
	}
	
	private static void display(boolean[][] rows) {
		for (boolean[] row : rows) {
			for (boolean element: row) {
				if (element) {
					System.out.print("Q ");
				} else {
					System.out.print("X ");
				}
			}
			System.out.println();
		}
	}
}
