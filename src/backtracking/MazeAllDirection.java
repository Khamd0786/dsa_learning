package backtracking;

import java.util.ArrayList;
import java.util.Arrays;

/**
 * This is backtracking question it self
 * Going to a path then mark as false or marked 
 * and while returning undo the changes
 * @param args
 */
public class MazeAllDirection {
	
	public static void main(String[] args) {
		
		boolean[][] board = {
				{true, true, true},
				{true, true, true},
				{true, true, true},
		};	
		
//		ArrayList<String> p = paths("", board, 0, 0);
//		System.out.println(Arrays.toString(p.toArray()));
		
		printMatrixPath("", "", 1,board, 0, 0);
	}
	
	private static ArrayList<String> paths(String p, boolean[][] board, int r, int c) {
		
		if (r == board.length - 1 && c == board[0].length - 1) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(p);
			return list;
		}
		
		ArrayList<String> result = new ArrayList<String>();
		
		if (!board[r][c]) {
			return result;
		}
		
		board[r][c] = false;
		
		if (r < board.length - 1) {
			result.addAll(paths(p + 'D', board, r + 1, c));
		}
		
		if (c < board[0].length - 1) {
			result.addAll(paths(p + 'R', board, r, c + 1));
		}
		
		if (r > 0) {
			result.addAll(paths(p + 'U', board, r - 1, c));
		}
		
		if (c > 0) {
			result.addAll(paths(p + 'L', board, r, c - 1));
		}
		
		board[r][c] = true;
		
		return result;	
	}
	
	private static void printMatrixPath(String p, String pc, int count, boolean[][] board, int r, int c) {
		if (r == board.length - 1 && c == board[0].length - 1) {
			System.out.println(p);
			System.out.println(pc);
			
			return;
		}
		
		if (!board[r][c]) {
			return;
		}
		
		board[r][c] = false;
		
		if (r < board.length - 1) {
			printMatrixPath(p + 'D',pc + count, count + 1, board, r+1, c);
		}
		if (c < board[0].length - 1) {
			printMatrixPath(p + 'R' ,pc + count, count + 1,board, r, c+1);
		}
		if (r > 0) {
			printMatrixPath(p + 'U', pc + count, count + 1,board, r-1, c);
		}
		if (c > 0) {
			printMatrixPath(p + 'L', pc + count, count + 1,board, r, c-1);
		}
		
		board[r][c] = true;
	}
	
	
}
