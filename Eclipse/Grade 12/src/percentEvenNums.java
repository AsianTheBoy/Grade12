/*
 * Min Young Kim
 * 04/10/17
 * Finds the percentage of even numbers within an array
 */
public class percentEvenNums {

	public static void main(String[] args) {

		// Creates an integer array called nums
		int[] nums = { 6, 2, 9, 11, 3 };
		// Outputs the return value of the method percentEven with the parameter nums
		System.out.println(percentEven(nums) + "% of the numbers are even.");

	}

	// Method that finds the percentage of even numbers in an array
	public static double percentEven(int[] array) {

		double numEven = 0;

		// Finds the number of even numbers in the array
		for (int i = 0; i < array.length; i++) {
			if (array[i] % 2 == 0) {
				numEven++;
			}
		}
		// Calculates the percentage of even numbers
		double percent = numEven / array.length * 100;
		// Returns the value of percent
		return percent;
	}

}
