
/*
 * Min Young Kim
 * 04/01/18
 * Program that uses recursion to find a palindrome within a longer word
 */

import java.util.Scanner;

public class Palindrome_MinYoung_Old {

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
		boolean test = false;
		boolean end = false;
		// Checks if the input is two identical characters (e.g "aa")
		if (input.length == 2 && input[0].equals(input[1])) {
			System.out.println("It contains a paldindrome:");
			System.out.println(input[0] + input[1]);
		} else {
			// Scans through each value of each array
			for (int i = 0; i < input.length - 2; i++) {
				for (int z = 0; z < inputRev.length - 2; z++) {
					// Checks where the two arrays are equal
					if (input[i].equals(inputRev[z])) {
						// Count value to prevent out of bounds exception
						int count = max(i, z);
						// Checks if the subsequent values are equal in both arrays
						for (int f = 0; f < inputRev.length - count; f++) {
							if (input[i + f].equals(inputRev[z + f])) {
								result += inputRev[z + f];
								test = palindromecheck(result, input);
							} else {
								result = "";
								break;
							}
						}
						if (test == true) {
							end = true;
							break;
						}
						/*
						 * if (test == true) { end = true; i = input.length - 3; break; }
						 */
						// i++;
						// break;
					}
				}
			}
			if (end == false) {
				System.out.println("There is no palindrome");
			}
		}
	}

	// Used for the count value above
	public static int max(int num1, int num2) {
		if (num1 > num2) {
			return num1;
		} else
			return num2;
	}

	// Checks if the results from the above set of for loops are valid palindromes
	public static boolean palindromecheck(String pali, String[] input) {
		boolean result = false;
		// Discards results that are only one character
		if (pali.length() == 1 || pali.length() == 0) {
			System.out.print("");
			// Discards results that are longer than the original String
		} else if (pali.length() > input.length) {
			System.out.print("");
			// Outputs results that are valid palindromes
		} else if (pali.equals(ConvertString(pali, pali.length() - 1))) {
			System.out.println("It contains a paldindrome:");
			System.out.println(pali);
			result = true;
		}
		return result;
	}
}
