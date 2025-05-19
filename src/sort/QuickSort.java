package sort;

import java.util.Arrays;

public class QuickSort {

    public static void main(String[] args) {
        int[] arr = new int[]{3, 4, 7789, 2112, 5, 100, 4,199 ,300 ,54 ,65, 766, 222,11111 ,432, 64, 70, 81};
        sort(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static void sort(int[] nums, int low, int hi) {
        if (low >= hi) {
            return;
        }

        int s = low;
        int e = hi;
        int m = low + (hi -  low) / 2;
        int pivot = nums[m];


        while (s <= e) {
            while (nums[s] < pivot) {
                s++;
            }

            while (nums[e] > pivot) {
                e--;
            }

            if (s <= e) {
                swap(nums, s, e);
                s++;
                e--;
            }
        }

        sort(nums, low, e);
        sort(nums, s, hi);
    }


    private static void swap(int[] nums, int i, int j) {
        int temp = nums[i];
        nums[i] = nums[j];
        nums[j] = temp;
    }
}
