package leetcodeQuestions;

class SumIndicesWithSetBits {
    public static void main(String[] args) {
        System.out.println("sum ->" + sumIndicesWithKSetBits(new int[]{5,10,1,5,2}, 1));
    }
    
     public static int sumIndicesWithKSetBits(int[] nums, int k) {
        int total = 0;
        for (int i = 0; i < nums.length; i++) {
            int l = nums[i];
            int sum = 0;
            while(l != 0) {
                sum += l & 1;
                l >>= 1;
            }

            if (sum == k) {
                total += nums[i];
            }
        }

        return total;
    }
}