package leetcodeQuestions;

import java.util.Arrays;

public class MergeSort_Practice {

	public static void main(String[] args) {
		int[] arr = new int[] {1, 1, 3, 4, 6, 3, 1, 9, 6, 3};
		int[] mergedArr= mergeSort(arr);
		System.out.println(Arrays.toString(mergedArr));
	}
	
	private static int[] mergeSort(int[] arr) {
		if (arr.length <= 1) {
			return arr;
		}
		
		int mid = arr.length / 2; 
		
		int[] first = mergeSort(Arrays.copyOfRange(arr, 0, mid));
		int[] second = mergeSort(Arrays.copyOfRange(arr, mid, arr.length));
		
		return merge(first, second);
	}
	
	private static int[] merge(int[] first, int[] second) {
		int[] mergeArr = new int[first.length + second.length];
		
		int i = 0;
		int j = 0;
		int k = 0;
		while (i < first.length && j < second.length) {
			
			if (first[i] < second[j]) {
				mergeArr[k] = first[i];
				i++;
			} else {
				mergeArr[k] = second[j];
				j++;
			}
			k++;
		}
		
		while (i < first.length) {
			mergeArr[k] = first[i];
			i++;
			k++;
		}
		
		while (j < second.length) {
			mergeArr[k] = second[j];
			j++;
			k++;
		}
		
		return mergeArr;
		
	}
}
