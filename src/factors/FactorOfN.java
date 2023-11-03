package factors;

import java.util.ArrayList;

public class FactorOfN {

	public static void main(String[] args) {
		int n = 20;
		printAllFactors(n);
		System.out.println();
		printAllFactors2(n);
		System.out.println();
		printAllFactors2Sroted(n);
	}
	
	//O(n)
	private static void printAllFactors(int n) {
		for (int i = 1; i <= n; i++) {
			if (n % i == 0) {
				System.out.print(i + " ");
			}
		}
	}
	
	//O(sqrt(N))
	private static void printAllFactors2(int n) {
		for (int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				if (n/i == i) {
					System.out.print(i + " ");
				} else {
					System.out.print(n / i + " " + i + " ");
				}
			}
		}
	}
	
	//Both time and space will be O(sqrt(N))
	private static void printAllFactors2Sroted(int n) {
		ArrayList<Integer> list = new ArrayList<Integer>();
		for(int i = 1; i * i <= n; i++) {
			if (n % i == 0) {
				if (n/i == i) {
					System.out.print(i + " ");
				} else {
					System.out.print(i + " ");
					list.add(n/i);
				}
			}
		}
		
		
		for(int i = list.size() - 1; i >= 0; i--) {
			System.out.print(list.get(i) + " ");
		}
	}
	
	
	
}
