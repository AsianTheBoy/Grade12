
/*
 * Min Young Kim
 * 04/01/18
 * Program that uses loops to calculate exponents, quotients and remainders
 */

import java.util.Scanner;

public class Calc_Loops_MinYoung {

	public static void main(String[] args) {
		Scanner scan1 = new Scanner(System.in);
		// Exponent
		System.out.println("Write the base number");
		int number = scan1.nextInt();
		System.out.println("Write the exponent");
		int exponent = scan1.nextInt();
		System.out.println("Answer: " + exponent(number, exponent));
		// Quotient and remainder
		System.out.println("\nWrite the numerator");
		int num = scan1.nextInt();
		System.out.println("Write the denominator");
		int den = scan1.nextInt();
		int quo = quotient(num, den);
		System.out.println("\nQuotient: " + quo + "\nRemainder: " + remainder(num, den, quo));
	}

	public static int exponent(int number, int exponent) {
		int basenum = number;
		if (exponent == 0) {
			number = 1;
		} else {
			for (int i = 1; i < exponent; i++) {
				number = number * basenum;
			}
		}
		return number;
	}

	public static int quotient(int num, int den) {
		int ans = num;
		int result = 0;
		while (true) {
			ans = ans - den;
			if (ans >= 0) {
				result++;
			} else
				break;
		}
		return result;
	}

	public static int remainder(int num, int den, int quo) {
		int result = 0;
		result = num - (den * quo);
		return result;
	}
}
