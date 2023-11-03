package stack_queue.questions;

import java.util.Arrays;
import java.util.Stack;

public class NearestGreaterElement {

    public static void main(String[] args) {
        int[] arr = {4, 12, 5, 3, 1, 2, 5, 3, 1, 2, 4, 6};
        System.out.println("Greatest Element towards right -> " + Arrays.toString(getAllRightGreatestNumber(arr)));
        System.out.println(getNearestNumber(arr, 11) + "");

        System.out.println("Greatest Element towards left -> " + Arrays.toString(getAllLeftGreatestElement(arr)));

    }

    //it will tell the right
    public static int[] getAllRightGreatestNumber(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();
        for (int i = arr.length - 1; i >= 0; i--) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }
            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }
        return ans;
    }

    //it will tell the left
    public static int[] getAllLeftGreatestElement(int[] arr) {
        int[] ans = new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && stack.peek() <= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                ans[i] = -1;
            } else {
                ans[i] = stack.peek();
            }
            stack.push(arr[i]);
        }

        return ans;

    }

    //right side of the array, weather it will return -1 or the next greatest element
    public static int getNearestNumber(int[] arr, int index) {
        int target = arr[index];
        int nMax = -1;
        for (int i = index + 1; i < arr.length; i++) {
            if (arr[i] > target) {
                nMax = arr[i];
                break;
            }
        }

        return nMax;
    }


}
