package permutations;

import java.util.ArrayList;
import java.util.List;

public class DieSumCombination {

    public static void main(String[] args) {
        //suppose we have to create a 4 from a die
        //4 -> [1, 2, 3, 4, 5, 6]
        // possible combination are [4, 22, 112, 31, ...]

        List<String> list = diePermutation("", 4);
        for (String l : list) {
            System.out.println(l);
        }

        System.out.println("Count -> " + dieCount("", 4));
    }

    private static List<String> diePermutation(String p, int target) {
        List<String> result = new ArrayList<>();

        if (target == 0) {
            result.add(p);
            return result;
        }

        if (target < 0) {
            return result;
        }

        for (int i  = 1; i <= 6 && i <= target; i++) {
            result.addAll(diePermutation(p + i, target - i));
        }

        return result;
    }

    private static int dieCount(String p, int target) {
        if (target == 0) {
            return 1;
        }

        if (target < 0) {
            return 0;
        }

        int count = 0;

        for (int i  = 1; i <= target && i <= 6; i++) {
            count += dieCount(p + i, target - i);
        }

        return count;
    }
}
