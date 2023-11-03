package backtracking;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;

public class Maze {
	public static void main(String[] args) {
		int c = mazeCount(3, 3, new HashMap<String, Integer>());
		System.out.println("count -> " + c);
		
		printPaths("", 3, 3);
		
		ArrayList<String> l = paths("",3, 3, new HashMap<String, ArrayList<String>>());
		System.out.println(Arrays.toString(l.toArray()));
		
		
		
		ArrayList<String> hvd = pathsWithDiagonals("",3, 3);
		System.out.println(Arrays.toString(hvd.toArray()));

	}
	
	private static int mazeCount(int i, int j, HashMap<String, Integer> map) {
		if (i == 1 || j == 1) {
			return 1;
		}
		
		if (map.containsKey("" + i + "," + j)) {
			return map.get("" + i + "," + j);
		}
		
		int left = mazeCount(i, j-1, map);
		map.put("" + i + "," + (j-1), left);
		int right = mazeCount(i-1, j, map);
		map.put("" + (i-1) + "," + j, right);
		return left + right;
	}
	
	private static void printPaths(String p, int r, int d) {
		if (r == 1 && d == 1) {
			System.out.println(p);
			return;
		}
		
		if (r > 1) {
			printPaths(p + "R", r - 1, d);
		} 
		
		if (d > 1) {
			printPaths(p + "D", r, d - 1);
		}
	}
	
	/*
	 * r -> row
	 * c -> column
	 */
	private static ArrayList<String> paths(String p, int r, int c, HashMap<String, ArrayList<String>> map) {
		if (r == 1 && c == 1) {
			ArrayList<String> l = new ArrayList<String>();
			l.add(p);
			return l;
		}
		
//		if (map.containsKey(r + "," + c)) {
//			return map.get(r + "," + c);
//		}
		
		ArrayList<String> result = new ArrayList<String>();
		
		if (r > 1) {
			ArrayList<String> left = paths(p + 'R', r - 1, c, map);
			map.put((r-1) + "," + c, left);
			result.addAll(left);
		}
		
		if (c > 1) {
			ArrayList<String> right = paths(p + 'D', r, c - 1, map);
			map.put(r + "," + (c-1), right);
			result.addAll(right);
		}
		
		return result;
	}
	
	private static ArrayList<String> pathsWithDiagonals(String p, int r, int c) {
		if (r == 1 && c == 1) {
			ArrayList<String> l = new ArrayList<String>();
			l.add(p);
			return l;
		}
		
		ArrayList<String> result = new ArrayList<String>();
		
		if (r > 1 && c > 1) {
			ArrayList<String> diagonal = pathsWithDiagonals(p + 'D', r - 1, c - 1);
			result.addAll(diagonal);
		}
		
		if (r > 1) {
			ArrayList<String> left = pathsWithDiagonals(p + 'V', r - 1, c);
			result.addAll(left);
		}
		
		if (c > 1) {
			ArrayList<String> right = pathsWithDiagonals(p + 'H', r, c - 1);
			result.addAll(right);
		}
		
		return result;
	}
}
