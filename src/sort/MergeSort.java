package sort;

import java.util.Arrays;

public class MergeSort {

    public static void main(String[] args) {

        int[] arr = new int[]{3, 4, 7789, 2112, 5, 100, 4,199 ,300 ,54 ,65, 766, 222,11111 ,432, 64, 70, 81};
        sortInPlace(arr, 0, arr.length - 1);
        System.out.println(Arrays.toString(arr));
    }

    private static int[] sort(int[] nums) {
        return sort(nums, 0, nums.length - 1);
    }

    private static int[] sort(int[] nums, int s, int e) {

        if (s == e) {
            return new int[]{nums[s]};
        }

        int m = s + (e - s) / 2;

        int[] left = sort(nums, s, m);
        int[] right = sort(nums, m + 1, e);

        return merge(left, right);
    }

    private static int[] merge(int[] leftArr, int[] rightArr) {
        int[] result = new int[leftArr.length + rightArr.length];

        int i = 0;
        int j = 0;
        int k = 0;
        while (i < leftArr.length && j < rightArr.length) {
            if (leftArr[i] <= rightArr[j]) {
                result[k] = leftArr[i];
                i++;
            } else {
                result[k] = rightArr[j];
                j++;
            }
            k++;
        }

        while (i < leftArr.length) {
            result[k++] = leftArr[i++];
        }

        while (j < rightArr.length) {
            result[k++] = rightArr[j++];
        }

        return result;
    }



    private static void sortInPlace(int[] nums, int s, int e) {
        if (s >= e) {
            return;
        }

        int m = s + (e - s) / 2;

        sortInPlace(nums, s, m);
        sortInPlace(nums, m + 1, e);

        mergeInplace(nums, s, m, e);
    }

    private static void mergeInplace(int[] nums, int s, int m, int e) {
        int[] merge = new int[e - s + 1];

        int i = s;
        int j = m + 1;
        int k = 0;

        while (i <= m && j <= e) {
            if (nums[i] <= nums[j]) {
                merge[k] = nums[i];
                i++;
            } else {
                merge[k] = nums[j];
                j++;
            }
            k++;
        }

        while (i <= m) {
            merge[k++] = nums[i++];
        }

        while (j <= e) {
            merge[k++] = nums[j++];
        }

        for (int l = 0; l < merge.length; l++) {
            nums[s+l] = merge[l];
        }
    }

}
