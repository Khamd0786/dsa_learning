package leetcodeQuestions;

import java.util.ArrayList;
import java.util.Arrays;

public class ClosestPrimeInRange {

	public static void main(String[] args) {
		int[] closestPrime  = closestPrimes(19, 31);
		System.out.print(Arrays.toString(closestPrime));
	}
	
	private static int[] closestPrimes(int left, int right) {
		ArrayList<Integer> list = getPrimeNumbersInRange(left, right);

		int[] leastComb = new int[]{-1, -1};
		int leastNumber = -1;

		if (list.size() < 2) {
			return leastComb;
		}

		for(int i = 0; i < list.size() - 1; i ++) {
			int diff = list.get(i + 1) - list.get(i);
			if (leastNumber == -1 || leastNumber > diff) {
				leastNumber = diff;
				leastComb[0] = list.get(i);
				leastComb[1] = list.get(i + 1);
			}
   }

   
   // for (int i = 0; i < list.size(); i++) {
   //     for(int j = i + 1; j < list.size(); j++) {
   //         if (leastNumber == -1) {
   //             leastNumber = list.get(j) - list.get(i);
   //              leastComb[0] = list.get(i);
   //             leastComb[1] = list.get(j);
   //         }

   //         int num = list.get(j) - list.get(i);
   //         if ( num < leastNumber) {
   //             leastNumber = num;
   //             leastComb[0] = list.get(i);
   //             leastComb[1] = list.get(j);
   //         }
   //     }
   // }

   return leastComb;

}

	private static ArrayList<Integer> getPrimeNumbersInRange(int from, int to) {
		boolean[] arr = new boolean[to + 1];
		for (int i = 2; i * i <= to; i++) {
			for (int j = i + i; j <= to; j += i) {
				arr[j] = true;
			}
		}

		ArrayList<Integer> list = new ArrayList<Integer>();
		for (int i = from; i <= to; i++) {
			if (!arr[i] && i > 1) {
				list.add(i);
				System.out.print(i + " ");
			}
		}
   
		return list;
	}
}
