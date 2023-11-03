package backtracking;

public class SudokuSolver {
	public static void main(String[] args) {
		char[][] board = {
	            {'5', '3', '.', '.', '7', '.', '.', '.', '.'},
	            {'6', '.', '.', '1', '9', '5', '.', '.', '.'},
	            {'.', '9', '8', '.', '.', '.', '.', '6', '.'},
	            {'8', '.', '.', '.', '6', '.', '.', '.', '3'},
	            {'4', '.', '.', '8', '.', '3', '.', '.', '1'},
	            {'7', '.', '.', '.', '2', '.', '.', '.', '6'},
	            {'.', '6', '.', '.', '.', '.', '2', '8', '.'},
	            {'.', '.', '.', '4', '1', '9', '.', '.', '5'},
	            {'.', '.', '.', '.', '8', '.', '.', '7', '9'}
	        };
		
		if (solveSudokuAlgo(board)) {
			display(board);
		}
	}
	
	private static boolean solveSudokuAlgo(char[][] board) {
		for (int row = 0; row < board.length; row++) {
			for (int col = 0; col < board[0].length; col++) {
				
				if (board[row][col] == '.') {
					for (char i = '1'; i <= '9'; i++) {
						if (isSafe(board, row, col, i)) {
							board[row][col] = i;
							if (solveSudokuAlgo(board)) {
								return true;
							} else {
								board[row][col] = '.'; //backtracking
							}
						}
					}
					return false; //no valid answer found!
				} 
			}
		}
		
		return true;
	}
	
	private static boolean isSafe(char[][] board, int row, int col, char target) {
		
		//check row
		for (int i = 0; i < board.length; i++) {
			if (board[i][col] == target) {
				return false;
			}
		}
		
		//check column
		for (int i = 0; i < board[0].length; i++) {
			if (board[row][i] == target) {
				return false;
			}
		}
		
		int sqrt = (int) Math.sqrt(board.length);
		int startRow = row - row % sqrt;
		int startCol = col - col % sqrt;
		
		for (int i = startRow; i < startRow + sqrt; i++) {
			for (int j = startCol; j < startCol + sqrt; j++) {
				if (board[i][j] == target) {
					return false;
				}
			}
		}
		
		return true;
	}
	
	    
	private static void display(char[][] rows) {
		for (char[] row : rows) {
			for (char element: row) {
				System.out.print(element + ", ");
			}
			System.out.println();
		}
	}
}
