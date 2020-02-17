/*
 * Min Young Kim
 * 04/01/18
 * Program that uses loops and recursion to calculate the nth term in a geometric sequence
 */

import java.util.Scanner;

public class GeometricSequence_MinYoung {

	public static void main(String[] args) {
		Scanner scan1 = new Scanner(System.in);
		System.out.println("Enter a value for the first term: ");
		int t1 = scan1.nextInt();
		System.out.println("Enter a value for the common ratio: ");
		int r = scan1.nextInt();
		System.out.println("Enter a value for the term number: ");
		int term = scan1.nextInt();
		System.out.println("\nLoops:");
		System.out.println("Term " + term + " is " + geoLoop(t1, r, term));
		System.out.println("\nRecursion:");
		System.out.println("Term " + term + " is " + geoRecur(t1, r, term));
	}

	// Calculates the exponents of numbers
	public static int exponentloop(int number, int exponent) {
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
	
	// Calculates the exponents of numbers
	public static int exponentRecur(int number, int exponent) {
		// Base case
		if (exponent == 1) {
			return number;
		} else {
			return number * exponentRecur(number, exponent - 1);
		}
	}

	// Finds the value of the nth term using loops
	public static int geoLoop(int t1, int r, int term) {
		int multiply = exponentloop(r, (term - 1));
		int result = t1 * multiply;
		return result;
	}

	// Finds the value of the nth term using recursion
	public static int geoRecur(int t1, int r, int term) {
		int multiply = exponentRecur(r, term - 1);
		int result = t1 * multiply;
		return result;
	}
}