package sort;

import java.util.Arrays;

public class CycleSort {
	
	public static void main(String[] args) {
		
		int[] arr = {5, 3, 2, 4, 1};
		doCycleSort(arr);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void doCycleSort(int[] arr) {
		
		int i = 0;
		while (i < arr.length) {
			int currIndex = arr[i] - 1;
			if (currIndex != i) {
				int temp = arr[currIndex];
				arr[currIndex] = arr[i];
				arr[i] = temp;
			} else {
				i++;
			}
		}
		
		
	}

}
