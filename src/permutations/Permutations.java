package permutations;

import java.util.ArrayList;
import java.util.List;

public class Permutations {

	public static void main(String[] args) {
		
		List<String> arr = permutationList("", "Ravil");
		for (String ar : arr) {
			System.out.println(ar);
		}

		System.out.println(permutationCount("", "Ravil"));
		
	}

	private static void permutations(String p, String up) {
		if (up.isEmpty()) {
			System.out.println(p);
			return;
		}

		char ch = up.charAt(0);

		for(int i = 0; i <= p.length(); i++) {
			String f = p.substring(0, i);
			String s = p.substring(i, p.length());
			permutations(f + ch + s, up.substring(1));
		}
	}

	private static List<String> permutationList(String p, String up) {
		List<String> result = new ArrayList<>();
		if (up.isEmpty()) {
			result.add(p);
			return result;
		}


		for (int i = 0; i <=  p.length(); i++) {
			String f = p.substring(0, i);
			char ch = up.charAt(0);
			String l = p.substring(i, p.length());

			result.addAll(permutationList(f + ch + l, up.substring(1)));
		}

		return result;
	}

	private static int permutationCount(String p, String up) {
		if (up.isEmpty()) {
			return 1;
		}
		int count = 0;

		for (int i = 0; i <= p.length(); i++) {
			String f = p.substring(0, i);
			char ch = up.charAt(0);
			String l = p.substring(i, p.length());
			count += permutationCount(f + ch + l, up.substring(1));
		}

		return count;
	}
}
