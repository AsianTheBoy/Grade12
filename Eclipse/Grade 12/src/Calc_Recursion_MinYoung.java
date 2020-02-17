
/*
 * Min Young Kim
 * 04/01/18
 * Program that uses recursion to calculate exponents, quotients and remainders
 */

import java.util.Scanner;

public class Calc_Recursion_MinYoung {

	public static void main(String[] args) {
		Scanner scan1 = new Scanner(System.in);
		// Exponent
		System.out.println("Write the base number");
		int number = scan1.nextInt();
		System.out.println("Write the exponent");
		int exponent = scan1.nextInt();
		if (exponent == 0) {
			System.out.println("Answer: 1");
		} else {
			System.out.println(exponent(number, exponent - 1));
		}
		// Quotient and remainder
		System.out.println("\nWrite the numerator");
		int num = scan1.nextInt();
		System.out.println("Write the denominator");
		int den = scan1.nextInt();
		int quo = quotient(num, den);
		System.out.println("\nQuotient: " + quo + "\nRemainder: " + remainder(num, den, quo));
	}

	public static int exponent(int number, int exponent) {
		// Base case
		if (exponent == 0) {
			return number;
		} else {
			return number * exponent(number, exponent - 1);
		}
	}

	public static int quotient(int num, int den) {
		int result = 0;
		// Base case
		if (num < den) {
			return result;
		}
		result++;
		return quotient(num - den, den) + result;
	}

	public static int remainder(int num, int den, int quo) {
		int result = 0;
		result = num - (den * quo);
		return result;
	}

}
