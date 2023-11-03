package algos;

public class SeivePrimeNumbers {


	public static void main(String[] args) {
		int n = 40;    
		printAllPrimesTillRange(n);
	}
	
	private static void printAllPrimesTillRange(int n) {
		
		boolean[] arr = new boolean[n + 1];
		seiveOfPrimes(n, arr);
		
		
		for (int i = 2; i < arr.length; i++) {
			if (!arr[i]) {
				System.out.println(i);
			}
		}
	}
	
	private static void seiveOfPrimes(int n, boolean[] arr) {
		
		for(int i = 2; i * i <= n; i++) {
			if (!arr[i]) { //false means unchanged or prime
				for (int j = i + i; j < arr.length; j += i) {
					arr[j] = true;
				}
			}
		}
	}
}
