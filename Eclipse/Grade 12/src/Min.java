
/*
 * Min Young Kim 
 * 28/09/17
 * Code that determines the minimum value between 2 random ints and and inputs them as the
 * arguments for the Random method.
 */

import java.util.Random;

public class Min {

	public static void main(String[] args) {

		Random r = new Random();
		// Creates 2 random numbers
		int ranNum1 = r.nextInt(20);
		int ranNum2 = r.nextInt(20);
		// Uses calls minInt with arguments ranNum1 and ranNum2
		int argument = minInt(ranNum1, ranNum2);
		// Prints out the random number
		System.out.println(argument);

	}

	// Method that returns the minimum value between 2 values
	public static int minInt(int num, int num2) {
		int minNum = num;
		if (num2 < num) {
			minNum = num2;
		} else {
			minNum = num;
		}
		return minNum;
	}

}
