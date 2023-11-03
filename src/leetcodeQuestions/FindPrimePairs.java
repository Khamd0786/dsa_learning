package leetcodeQuestions;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

public class FindPrimePairs {

	public static void main(String[] args) {
		
		List<List<Integer>> pairs = findPrimePairs(10);
		
		for (int i =0 ; i < pairs.size(); i++) {
			for(int j = 0; j < pairs.get(i).size(); j++) {
				System.out.print(pairs.get(i).get(j) + " ");
			}
		}
		
	}
	
	public static List<List<Integer>> findPrimePairs(int n) {
        if (n <= 2) {
            return new ArrayList<List<Integer>>();
        }

        List<Integer> primes = primeNumbers(n);
        List<List<Integer>> pairs = new ArrayList<List<Integer>>();


        int start = 0;
        int end = pairs.size() - 1;

        while (start <= end) {//2, 3, 5, 7 == 10
            int sum = primes.get(start) + primes.get(end);
            if (sum == n) {
                List<Integer> pair = new ArrayList<>();
                        pair.add(primes.get(start));
                        pair.add(primes.get(end));
                    pairs.add(pair);

                    start++;
                    end--;
            } else if (sum < n) {
                start++;
            } else {
                end--;
            }
        }
        // for (int i = 0; i < primes.size(); i++) { 
        //     int start = i;
        //     int end = primes.size() - 1;
 
        //     while (start <= end) {
        //         int mid = start  + (end - start) / 2;
        //         int sum = primes.get(i) + primes.get(mid);

        //         if (sum == n) {
        //              List<Integer> pair = new ArrayList<>();
        //                 pair.add(primes.get(i));
        //                 pair.add(primes.get(mid));
        //             pairs.add(pair);
        //             break;
        //         } else if (sum < n) { // 2, 3, 5, 7
        //             start = mid + 1;
        //         } else {
        //             end = mid - 1;
        //         }
        //     }
        // }


        // for (int i = 0; i < primes.size(); i++) {
        //     for (int j = i; j < primes.size(); j++) {
        //         if ((primes.get(i) + primes.get(j)) == n) {
        //             List<Integer> pair = new ArrayList<>();
        //                 pair.add(primes.get(i));
        //                 pair.add(primes.get(j));
        //             pairs.add(pair);
        //         }
        //     }
        // }

        return pairs;

    }

    private static List<Integer> primeNumbers(int n) {
        boolean[] nPrimes = new boolean[n+1];
        for (int i = 2; i * i <= n; i++) {
            for(int j = i + i; j <= n; j += i) {
                if(!nPrimes[j]) { 
                    nPrimes[j] = true;
                }
            }
        }

        List<Integer> list = new ArrayList<Integer>();
        for (int i = 2; i < nPrimes.length; i++) {
            if (!nPrimes[i]) {
                list.add(i);
            }
        }

        return list;
    }

	
	
}
