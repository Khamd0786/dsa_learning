package subset;

import java.util.ArrayList;
import java.util.List;

public class SubSet {

    public static void main(String[] args) {

    }

    public static List<List<Integer>> subsets(int[] nums) {
        List<Integer> up = new ArrayList<>();
        for (int num : nums) {
            up.add(num);
        }
        return subsets(new ArrayList<>(), up);
    }

    /**
     *
     * @param p it is processed list
     * @param up it is unprocess list
     * @return A nested array of all the subsets with all the possible combinations.
     */
    private static List<List<Integer>> subsets(List<Integer> p, List<Integer> up) {
        List<List<Integer>> result = new ArrayList<>();
        if (up.isEmpty()) {
            result.add(new ArrayList<>(p));
            return result;
        }

        Integer val = up.get(0);

        result.addAll(subsets(p, up.subList(1, up.size())));

        List<Integer> np = new ArrayList<>(); // new processed to not change the reference
        np.add(val);
        result.addAll(subsets(np, up.subList(1, up.size())));

        return result;
    }

}
