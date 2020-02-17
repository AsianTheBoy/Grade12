
/*
 * Min Young Kim
 * 04/01/18
 * Program that uses recursion and loops to find the longest palindrome within a longer word
 */

import java.util.Scanner;

public class Palindrome_MinYoung {

	public static void main(String[] args) {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter a String input");
		String input = scan1.nextLine();
		// Removes all spaces from the string
		input = input.replaceAll("\\s+", "");
		// Sets all the elements of the string to lower case
		input = input.toLowerCase();
		// Splits the strings into arrays
		String[] word = input.split("");
		String[] wordRev = ConvertString(input, input.length() - 1).split("");
		scan(word, wordRev);
		scan1.close();
	}

	// Reverses the string using recursion
	public static String ConvertString(String input, int lastind) {
		if (lastind == -1) {
			return "";
		} else {
			return input.charAt(lastind) + ConvertString(input, lastind - 1);
		}
	}

	// Method to scan the string for palindromes
	public static void scan(String[] input, String[] inputRev) {
		String result = "";
		String test = "";
		boolean end = false;
		// Scans through each value of each array
		for (int i = 0; i < input.length - 1; i++) {
			for (int z = 0; z < inputRev.length - 1; z++) {
				// Checks where the two arrays are equal
				if (input[i].equals(inputRev[z])) {
					// Count value to prevent out of bounds exception
					int count = max(i, z);
					// Checks if the subsequent values are equal in both arrays
					for (int f = 0; f < inputRev.length - count; f++) {
						if (input[i + f].equals(inputRev[z + f])) {
							result += inputRev[z + f];
							// Checks if the palindromes are valid then stores the valid ones
							test = palindromecheck(result, input, test);
						} else {
							result = "";
							break;
						}
					}
					if (!test.equals("")) {
						end = true;
					}
				}
			}
		}
		if (end == false) {
			System.out.println("\n--There is no palindrome--");
		} else {
			System.out.println("\nThere is a palindrome: ");
			System.out.println(test);
		}
	}

	// Used for the count value used in scan
	public static int max(int num1, int num2) {
		if (num1 > num2) {
			return num1;
		} else
			return num2;
	}

	// Checks if the results from the scan method are valid palindromes
	public static String palindromecheck(String pali, String[] input, String old) {
		String result = old;
		// Discards results that are only one character
		if (pali.length() == 1 || pali.length() == 0) {
			System.out.print("");
			// Discards results that are longer than the original String
		} else if (pali.length() > input.length) {
			System.out.print("");
			// Returns the longest valid palindrome
		} else if (pali.equals(ConvertString(pali, pali.length() - 1)) && pali.length() > old.length()) {
			result = pali;
		}
		return result;
	}
}