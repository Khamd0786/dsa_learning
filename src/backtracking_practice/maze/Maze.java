package backtracking_practice.maze;

import java.util.HashMap;

public class Maze {

    public static void main(String[] args) {
        System.out.printf("" + maze(0, 0, 3, 3, 4, 4, new HashMap<String, Long>()));
    }

    private static long maze(int i, int j, int leftTarget, int rightTarget, int maxLeft, int maxRight, HashMap<String, Long> valueProvider) {
        if (i == leftTarget && j == rightTarget) {
            return 1;
        }

        if (i > maxLeft || j > maxRight) {
            return 0;
        }

        if (valueProvider.containsKey(i + "," + j)) {
            return valueProvider.get(i + "," + j);
        }

        long left = maze(i + 1, j, leftTarget, rightTarget, maxLeft, maxRight, valueProvider);
        long right = maze(i, j + 1, leftTarget, rightTarget, maxLeft, maxRight, valueProvider);

        valueProvider.put((i+","+j) , (left + right));
        return left + right;
    }




}
