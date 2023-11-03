package stack_queue.questions;

import java.util.Stack;

public class GameOfTwoStacks {

    public static void main(String[] args) {
        int[] a = {10, 10, 8, 4, 5};
        int[] b = {10, 7, 8, 9};

        System.out.println(twoStacks(10, a, b) + "");
    }

    public static int twoStacks(int maxSum, int[] a, int[] b) {
        Stack<Integer> first = new Stack<>();
        Stack<Integer> second = new Stack<>();

        for (int i = a.length - 1; i >= 0; i--) {
            first.push(a[i]);
        }

        for (int i = b.length - 1; i >= 0; i--) {
            second.push(b[i]);
        }

        return maxCount(first, second, 0, maxSum) - 1;
    }

    private static  int maxCount(Stack<Integer> firstStack, Stack<Integer> secondStack, int count, int maxSum) {
        if (maxSum <= 0 || (firstStack.isEmpty() && secondStack.isEmpty())) {
            return count;
        }

        int first =  0;
        if (!firstStack.isEmpty()) {
            int fp = firstStack.pop();
            first = maxCount(firstStack, secondStack, count + 1, maxSum -  fp);
        }

        int second = 0;
        if (!secondStack.isEmpty()) {
            int sp = secondStack.pop();
            second = maxCount(firstStack, secondStack, count + 1, maxSum - sp);
        }

        return Math.max(first, second);
    }

}
