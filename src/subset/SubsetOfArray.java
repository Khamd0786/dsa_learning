package subset;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsetOfArray {
	
	public static void main(String[] args) {
		ArrayList<ArrayList<Integer>> list = getAllSubsets(new int[] {1, 2, 3, 4}); 
		for (int i  =0; i < list.size(); i++) {
			System.out.print("[");
			for (int j = 0; j < list.get(i).size(); j++) {
				System.out.print(list.get(i).get(j) + ", ");
			}
			System.out.print("]");
			System.out.println();
		}
		
	}
	
	private static ArrayList<ArrayList<Integer>> getAllSubsets(int[] arr) {
		ArrayList<ArrayList<Integer>> outer = new ArrayList<ArrayList<Integer>>();
		outer.add(new ArrayList<Integer>());
		for (int num: arr) {
		int n = outer.size();
			for (int j = 0; j < n; j++) {
				ArrayList<Integer> inner = new ArrayList<Integer>(outer.get(j));
				inner.add(num);
				outer.add(inner);
			}
		}
		
		return outer;
		
	}

}
