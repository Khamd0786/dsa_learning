package stack_queue.questions;

import java.util.Stack;

public class LargestRectangleInHistogram {

    public static void main(String[] args) {
        int[] arr = {2,1,5,6,2,3};
        System.out.println(largestRectangleArea(arr));
    }

    public static int largestRectangleArea(int[] arr) {

        //find the left smallest
        int[] leftSmall= new int[arr.length];
        int[] rightSmall= new int[arr.length];
        Stack<Integer> stack = new Stack<>();

        //finding left smallest
        for (int i = 0; i < arr.length; i++) {
            while (!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                leftSmall[i] = 0; //first
            } else  {
                leftSmall[i] = stack.peek() + 1;
            }
            stack.push(i);
        }


        //clear the stack if it is not
        while (!stack.isEmpty()) {
            stack.pop();
        }

        //finding right side smallest
        for (int i = arr.length - 1; i >= 0; i--) {
            while(!stack.isEmpty() && arr[stack.peek()] >= arr[i]) {
                stack.pop();
            }

            if (stack.isEmpty()) {
                rightSmall[i] = arr.length - 1;
            } else {
                rightSmall[i] = stack.peek() - 1;
            }
            stack.push(i);
        }


        return findMaxArea(arr, leftSmall, rightSmall);
    }


    private static int findMaxArea(int[] arr, int[] left, int[] right) {
        int max = -1;
        for (int i = 0; i < arr.length; i++) {
            int val = (right[i] - left[i] + 1) * arr[i];
            max = Math.max(max, val);
        }

        return max;
    }

}
