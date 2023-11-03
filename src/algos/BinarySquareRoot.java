package algos;

public class BinarySquareRoot {

    public static void main(String[] args) {
        int n = 40;
        int p = 3; // number of decimal we want for example 6.321
        System.out.print("square root of " + n + " is " + sqrt(n, p));
//		System.out.printf("Square root of %d is %.3f", n, sqrt(n, p));
    }

    private static double sqrt(int n, int p) {
        int start = 0;
        int end = n;

        while (start <= end) {
            int mid = start + (end - start) / 2;

            if (mid * mid == n) {
                return mid;
            }

            if (mid * mid > n) {
                end = mid - 1;
            } else {
                start = mid + 1;
            }
        }

        double root = end;
        double inc = 0.1;
        for (int i = 0; i < p; i++) {
            while ((root + inc) * (root + inc) <= n) {
                root += inc;
            }

            inc /= 10;
        }

        return root;
    }


}
