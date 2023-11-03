package leetcodeQuestions;

import java.util.List;
import java.util.ArrayList;

public class LetterCombinationOfAPhoneNumber {
	
	public static void main(String[] args) {
		phone("", "29");
//		letterCombination("", "71");
	}
	
	private static void phone(String p, String up) {
		
		if (up.isEmpty()) {
			System.out.println(p);
			return;
		}
		
		int digit = up.charAt(0) - '0'; //convert chat 2 into int 2
		
		if (digit == 1) {
			phone(p, up.substring(1));
		}
		
		if (digit >= 2 && digit <= 6) {
			for (int i = (digit - 2) * 3; i < (digit - 1) * 3; i++) {
				char ch = (char) ('a' + i);
				phone(p+ch, up.substring(1));
			}
		} else if (digit == 7) {
			for (int i = (digit - 2) * 3; i <= (digit - 1) * 3; i++) {
				char ch = (char) ('a' + i);
				phone(p+ch, up.substring(1));
			}
		} else if (digit == 8) {
			for (int i = ((digit - 2) * 3) + 1; i <= (digit - 1)* 3; i++) {
				char ch = (char) ('a' + i);
				phone(p+ch, up.substring(1));
			}
		} else if  (digit == 9) {
			for (int i = ((digit - 2) * 3) + 1; i <= ((digit - 1) * 3) + 1; i++) {
				char ch = (char) ('a' + i);
				phone(p+ch, up.substring(1));
			}
		}	
	}
}
