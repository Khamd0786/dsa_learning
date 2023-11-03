package permutations;

import java.util.ArrayList;

public class PermutationList {

	public static void main(String[] args) {
		ArrayList<String> list = permutations("", "abc");
		for (int i = 0; i < list.size(); i++) {
			System.out.println(list.get(i));
		}
	}
	
	private static ArrayList<String> permutations(String p, String up) {
		if (up.isEmpty()) {
			ArrayList<String> list = new ArrayList<String>();
			list.add(p);
			return list;
		}
		
		char ch = up.charAt(0);
		
		ArrayList<String> ans = new ArrayList<String>();
		
		for (int i = 0; i <= p.length(); i++) {
			String f = p.substring(0, i);
			String s = p.substring(i, p.length());
			
			ans.addAll(permutations(f + ch + s, up.substring(1)));
		}
		
		return ans;	
	}
}
