package leetcodeQuestions;

import java.util.Arrays;

public class QuickSort_Practice {
	
	public static void main(String[] args) {
		
		int[] arr = new int[]{100, 3, -2, 1};
		revQuickSort(arr, 0 , arr.length - 1);
		System.out.println(Arrays.toString(arr));
		
	}
	
	private static void quickSort(int[] arr, int low, int high) {
		if (low >= high) {
			return;
		}
		
		int start = low;
		int end = high;
		int mid = start + (end - start) / 2;
		int pivot = arr[mid];
		
		while (start <= end) {
			
			while (arr[start] > pivot) {
				start++;
			}
			
			while (arr[end] < pivot) {
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
	
    private static void revQuickSort(int[] nums, int low, int high) {
        if (low >= high) {
            return;
        }

        int s = low;
        int e = high;
        int mid = s + (e - s) / 2;
        int pivot = nums[mid];

        while (s <= e) {
            while (nums[s] > pivot) {
                s++;
            }
            
            while (nums[e] < pivot) {
                e--;
            }

            if (s <= e) {
                int temp = nums[s];
                nums[s] = nums[e];
                nums[e] = temp;

                s++;
                e--;
            }
        }

        revQuickSort(nums, low, e);
        revQuickSort(nums, s, high);
    }
}
