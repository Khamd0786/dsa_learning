package leetcodeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

public class FourSum {
	public static void main(String[] args) {
		int[] nums=  new int[]{1000000000,1000000000,1000000000,1000000000};
		
		Arrays.sort(nums);
		int target = -294967296;
        // List<List<Integer>> list = new ArrayList();
        Set<List<Integer>> set =  new HashSet<>();

        for (int i = 0; i < nums.length; i++) {
            if (i > 0 && nums[i] == nums[i - 1]) {
                continue;
            } 

            for (int j = i + 1; j < nums.length; j++) {

                int s = j + 1;
                int e = nums.length - 1;

                while (s < e) {
                    long sum = nums[i] + nums[j] + nums[s] + nums[e];

                    if (sum == (long) target) {
                        set.add(Arrays.asList(nums[i], nums[j], nums[s], nums[e]));

                        while (s < e && nums[s] == nums[s+1]) {
                            s++;
                        }

                        while (s < e && nums[e] == nums[e-1]) {
                            e--;
                        }

                        s++;
                        e--;
                    } else if (sum < target) {
                        s++; 
                    } else {
                        e--;
                    }
                }
            }
        } 

        List<List<Integer>> l =  new ArrayList<>(set);
//        for (int i = 0; i < l.size(); i++) {
//        	System.out.println(Arrays.toString(l.get(i)));
//        }
	}
	
	
}
