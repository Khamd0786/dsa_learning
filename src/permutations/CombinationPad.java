package permutations;

import javax.print.DocFlavor;
import java.util.ArrayList;
import java.util.List;

public class CombinationPad {

//https://leetcode.com/problems/letter-combinations-of-a-phone-number/submissions/1410889994/

    /**
     * Given a string containing digits from 2-9 inclusive,
     * return all possible letter combinations that the number could represent.
     * Return the answer in any order.
     * A mapping of digits to letters (just like on the telephone buttons) is given below.
     * Note that 1 does not map to any letters.
     */
    public static void main(String[] args) {
        List<String> pad = pad("", "23");
        for (String p : pad) {
            System.out.println(p);
        }

        System.out.println(padCount("", "23"));
    }

    private static final String[] COMB_PAD = {
            "", //0
            "", //1
            "abc", //2
            "def", //3
            "ghi", //4
            "jkl", //5
            "mno", //6
            "pqrs", //7
            "tuv", //8
            "wxyz" //9
    };

    private static List<String> pad(String p, String digits) {
        List<String> result = new ArrayList<>();
        if (digits.isEmpty()) {
            result.add(p);
            return result;
        }

        int digit = digits.charAt(0) - '0';
        String padComb = COMB_PAD[digit];

        for (int i = 0; i < padComb.length(); i++) {
            char ch = padComb.charAt(i);
            result.addAll(pad(p + ch, digits.substring(1)));
        }

        return result;
    }

    private static int padCount(String p, String up) {
        if (up.isEmpty()) {
            return 1;
        }

        int count = 0;

        int digit = up.charAt(0) - '0';
        String padComb = COMB_PAD[digit];

        for (int i = 0; i < padComb.length(); i++) {
            char ch = padComb.charAt(0);
            count += padCount(p + ch, up.substring(1));
        }

        return count;
    }

}
