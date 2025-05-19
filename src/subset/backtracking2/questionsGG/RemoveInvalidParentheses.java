package subset.backtracking2.questionsGG;

import java.util.*;

public class RemoveInvalidParentheses {
    private static int validLength = Integer.MIN_VALUE;

    public static void main(String[] args) {
        HashSet<String> set = new HashSet<>();
        removeInvalidParentheses("(((aaaaa))", 0, set);
        System.out.println(Arrays.toString(set.toArray()));
    }

    private static boolean isValid(String str) {
        Stack<Character> stack = new Stack<>();

        for (int i = 0; i < str.length(); i++) {
            if (str.charAt(i) == '(' || str.charAt(i) == ')') {
                if (str.charAt(i) == '(') {
                    stack.push(str.charAt(i));
                } else {
                    if (!stack.isEmpty()) {
                        stack.pop();
                    } else {
                        return false;
                    }
                }
            }
        }

        return stack.isEmpty();
    }

    private static void removeInvalidParentheses(String str, int r, HashSet<String> mResult) {

        if (isValid(str)) {
            if (str.length() > validLength) {
                validLength = str.length();
                mResult.clear();
                mResult.add(str);
            } else if (str.length() == validLength) {
                mResult.add(str);
            }
            return;
        }

        for (int i = 0; i < str.length(); i++) {
            String f = str.substring(0, i);
            String s = str.substring(i + 1);
            if (!mResult.contains(f + s)) {
                removeInvalidParentheses(f + s, r + 1, mResult);
            }
        }
    }
}
