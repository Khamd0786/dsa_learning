package backtracking;

public class WordSearch {

	public static void main(String[] args) {
		char[][] board = { { 'A', 'B', 'C', 'E' }, { 'S', 'F', 'C', 'S' }, { 'A', 'D', 'E', 'E' } };
		String word = "SEE";

		System.out.println(searchWord(board, word));
	}

	private static boolean searchWord(char[][] board, String word) {

		for (int r = 0; r < board.length; r++) {
			for (int c = 0; c < board[0].length; c++) {
				if (board[r][c] == word.charAt(0) && searchWord(board, r, c, 0, word)) {
					return true;
				}
			}
		}

		return false;
	}

	private static boolean searchWord(char[][] board, int row, int col, int count, String word) {
		if (count == word.length()) {
			return true;
		}

		if (row == -1 || row >= board.length || col == -1 || col >= board[0].length
				|| board[row][col] != word.charAt(count)) {
			return false;
		}
		
		char originalChar = board[row][col];
		board[row][col] = '.';
		boolean isExist = 
				searchWord(board, row + 1, col, count + 1, word)
				|| searchWord(board, row - 1, col, count + 1, word)
				|| searchWord(board, row, col + 1, count + 1, word)
				|| searchWord(board, row, col - 1, count + 1, word);
		
		board[row][col] = originalChar;
		
		return isExist;
	}
}
