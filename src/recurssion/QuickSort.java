package recurssion;

import java.util.Arrays;

public class QuickSort {
	public static void main(String[] args) {
		
		int[] arr = new int[]{1, 5, 7, 2, 5, 7,3 ,2 ,5, 6, 7,3, 2, 1};
		sort(arr, 0 , arr.length - 1);
		System.out.println(Arrays.toString(arr));
	}
	
	private static void sort(int[] arr, int low, int high) {
		
		if (low >= high) {
			return;
		}
		
		int start = low;
		int end = high;
		int mid = start + (end - start) / 2;
		int pivot = arr[mid];
		
		while (start <= end) {
		
			while (arr[start] < pivot) { //till we find start value greater than pivot
				start++;
			}
			
			while (arr[end] > pivot) { //till we find end value smaller than pivot
				end--;
			}
			
			if (start <= end) {
				int temp = arr[start];
				arr[start] =  arr[end];
				arr[end] = temp;
				
				start++;
				end--;
			}
		}
		
		sort(arr, low, end);
		sort(arr, start, high);
	}
}
