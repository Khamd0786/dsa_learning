package stack_queue.questions;

import java.util.Arrays;
import java.util.Stack;

public class NearestRightGreaterElementInCircularArray {

    public static void main(String[] args) {
        int[] arr = {4, 12, 5, 3, 1, 2, 5, 3, 1, 2, 4, 6};

        //[12, -1, 6, 5, 2, 5, 6, 4, 2, 4, 6, -1]
        System.out.println(Arrays.toString(getAllMaximums(arr)));
        System.out.println(Arrays.toString(getAllMaximumsOptimised(arr)));

    }

    public static int[] getAllMaximums(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                //run it to find next big in circular
                int x = i;
                boolean isBigFound = false;
                do {
                    x++;
                    x %= arr.length;
                    if (arr[x] > arr[i]) {
                        ans[i] = arr[x];
                        isBigFound = true;
                        break;
                    }
                } while (x != i);
                if (!isBigFound) {
                    ans[i] = -1;
                    stack.push(arr[i]);
                }
            } else {
                ans[i] = stack.peek();
                stack.push(arr[i]);
            }
        }

        return ans;
    }

    public static int[] getAllMaximumsOptimised(int[] arr) {
        int n = arr.length;
        int[] ans = new int[n];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < 2 * n; i++) {
            while (!stack.isEmpty() && arr[i % n] >= arr[stack.peek()]) {
                int poppedIndex = stack.pop();
                if (poppedIndex < n) {
                    ans[poppedIndex] = arr[i % n];
                }
            }

            if (i < n) {
                stack.push(i);
            }
        }

        while (!stack.isEmpty()) {
            int remainingIndex = stack.pop();
            ans[remainingIndex] = -1;
        }

        return ans;
    }
}
