package leetcodeQuestions;

public class NearestToZeroIndex {
	public static void main(String[] args) {
		int[] arr = new int[] {0, 2, 3,  4, 6, 8};
		System.out.println(nearestToZero(arr) + "");
	}
	

    private static int nearestToZero(int[] nums) {
        int start = 0;
        int end = nums.length - 1;
        int minVal  = Integer.MAX_VALUE;
        int minIndex = 0;

         // -4, -1, -1, 0, 1, 2
        while (start <= end) {

            int mid = start + (end - start) / 2;

            int x = nums[mid];
            if (nums[mid] == 0) {
                return mid;
            }

            if (x < 0) {
                x *= -1;
            }

            if (x < minVal) {
            	minVal = nums[mid];
                minIndex = mid;
            }

            if (nums[mid] < 0) {
                start = mid + 1;
            } else {
                end = mid - 1;
            }
        }

        return minIndex;
    }

}
