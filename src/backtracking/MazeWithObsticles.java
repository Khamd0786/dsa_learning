package backtracking;

import java.util.ArrayList;
import java.util.Arrays;

public class MazeWithObsticles {
	public static void main(String[] args) {
		
		boolean[][] board = {
				{true, true, true},
				{true, false, true},
				{true, false, true},
		};
		
		ArrayList<String> p = paths("", board, 0, 0);
		System.out.println(Arrays.toString(p.toArray()));
	
	}
	
	private static ArrayList<String> paths(String p, boolean[][] board, int r, int c) {
		if (r == board.length - 1 && c == board[0].length - 1) {
			ArrayList<String> l = new ArrayList<>();
			l.add(p);
			return l;
		}
		
		ArrayList<String> result = new ArrayList<String>();
		
		if (!board[r][c]) {
			return result;
		}
		
		if (r < board.length - 1) {
			result.addAll(paths(p + 'D', board, r + 1, c));
		}
		
		if (c < board[0].length - 1) {
			result.addAll(paths(p + 'R', board, r,  c + 1));
		}
		
		return result;
	}
	
	
}
