
/*
 * Min Young Kim 
 * 28/09/17
 * Code that determines the maximum value between 2 random ints and and inputs them as the
 * arguments for the Random method.
 */

import java.util.Scanner;

public class Max {

	public static void main(String[] args) {

		// Creates a Scanner called scan1
		Scanner scan1 = new Scanner(System.in);
		// Asks the user to input a number and stores that value
		System.out.println("Please input a number");
		int num1 = scan1.nextInt();
		// Asks the user to input a number and stores that value
		System.out.println("Please input a second number");
		int num2 = scan1.nextInt();
		// Calls maxInt with the parameters num1 and num2
		int maxNum = maxInt(num1, num2);
		// Outputs the maximum number between the 2 inputed values
		System.out.println(maxNum);
		// Closes the scanner
		scan1.close();
	}

	// Method that returns the maximum value between 2 values
	public static int maxInt(int num, int num2) {
		int maxNum = num;
		if (num2 < num) {
			maxNum = num2;
		}
		return maxNum;
	}

}
