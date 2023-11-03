package recurssion;

import java.util.Arrays;

public class MergeSort {

	public static void main(String[] args) {
		
		int[] arr = new int[] {1, 1, 3, 4, 6, 3, 1, 9, 6, 3};
		int[] mergedArr= mergeSort(arr);
		System.out.println(Arrays.toString(mergedArr));
		
	}
	
	private static int[] mergeSort(int[] arr) {
		
		if (arr.length == 1) {
			return arr;
		}
		
		int mid = arr.length / 2;
		int[] left = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		int[] right=  mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		
		return merge(left, right);
	}
	
	private static int[] merge(int[] first, int[] second) {
		
		int[] merger = new int[first.length + second.length];
		int i = 0;
		int j = 0;
		int k = 0;
		
		
		while(i < first.length && j < second.length) {
			
			if(first[i] < second[j]) {
				merger[k] = first[i];
				i++;
			} else {
				merger[k] = second[j];
				j++;
			}
			k++;
		}
		
		while (i < first.length) {
			merger[k] = first[i];
			i++;
			k++;
		}
		
		while (j < second.length) {
			merger[k] = second[j];
			j++;
			k++;
		}
		
		return merger;
	}
}
