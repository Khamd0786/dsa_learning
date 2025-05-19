package binary;

public class CelingOfNumber {

    public static void main(String[] args) {

        int[] arr = new int[] {1, 2, 4, 6, 7, 8 ,10, 20, 22, 26, 28, 30};
        int r = findCelling(arr, 27);
        System.out.println(r);
    }

    private static int findCelling(int[] nums, int target) {
        int s = 0;
        int e = nums.length - 1;

        while (s <= e) {
            int m = s + (e - s) / 2;

            if (nums[m] == target) {
                return nums[m];
            } else if(target > nums[m]) {
                s = m + 1;
            } else {
                e = m - 1;
            }
        }

        return nums[s];
    }
}
