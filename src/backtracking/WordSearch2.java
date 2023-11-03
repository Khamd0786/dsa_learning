package backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class WordSearch2 {
	public static void main(String[] args) {
		char[][] board = { { 'o', 'a', 'b', 'n' }, { 'o', 't', 'a', 'e' }, { 'a', 'h', 'k', 'r' },
				{ 'a', 'f', 'l', 'v' } };

		String[] words = { "oa", "oaa" };

		ArrayList<String> result = searchWords(board, words);
		System.out.println(Arrays.toString(result.toArray()));
	}

	private static ArrayList<String> searchWords(char[][] board, String[] words) {
		ArrayList<String> result = new ArrayList<String>();

		for (String word : words) {
			boolean isWordMatched = false;
			for (int r = 0; r < board.length; r++) {
				for (int c = 0; c < board[0].length; c++) {
					if (board[r][c] == word.charAt(0) && searchWord(board, r, c, 0, word)) {
						result.add(word);
						isWordMatched = true;
						break;
					}
				}
				if (isWordMatched) {
					break;
				}
			}
		}

		return result;
	}

	private static boolean searchWord(char[][] board, int row, int col, int count, String word) {
		if (count == word.length()) {
			return true;
		}

		if (row == -1 || row >= board.length || col == -1 || col >= board[0].length
				|| board[row][col] != word.charAt(count)) {
			return false;
		}

		char original = board[row][col];
		board[row][col] = '.';

		boolean isExist = searchWord(board, row + 1, col, count + 1, word)
				|| searchWord(board, row - 1, col, count + 1, word) || searchWord(board, row, col + 1, count + 1, word)
				|| searchWord(board, row, col - 1, count + 1, word);

		board[row][col] = original;

		return isExist;
	}
}
