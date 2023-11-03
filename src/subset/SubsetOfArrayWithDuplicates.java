package subset;

import java.util.ArrayList;
import java.util.Arrays;

public class SubsetOfArrayWithDuplicates {

	public static void main(String[] args) {
		int[] arr = {1, 2, 2};
		quickSort(arr, 0, arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
		ArrayList<ArrayList<Integer>> list = getAllSubsets(arr); 
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
		
		int start = 0;
		int end = 0;
		for (int i = 0; i < arr.length; i++) {
			start = 0;
			if (i > 0 && arr[i] == arr[i - 1]) {
				start = end;
			}
			end = outer.size();
			int n = outer.size();
			for (int j = start; j < n; j++) {
				ArrayList<Integer> inner = new ArrayList<Integer>(outer.get(j));
				inner.add(arr[i]);
				outer.add(inner);
			}
		}
		
		return outer;
		
	}
	
	private static void quickSort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		
		int start = low;
		int end = high;
		int mid = start + (end - start)/2;
		int pivot = arr[mid];
		
		while(start <= end) {
			while (arr[start] < pivot) {
				start++;
			}
			
			while (arr[end] > pivot) {
				end--;
			}
			
			if (start <= end) {
				int temp = arr[start];
				arr[start] = arr[end];
				arr[end] = temp;
				
				start++;
				end--;
			}
		}		
		
		quickSort(arr, low, end);
		quickSort(arr, start, high);
	}
}
