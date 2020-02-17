
/*
 * Min Young Kim	
 * 02/10/17	 
 * Returns the minimum gap in an array
 */

public class minGap {

	public static void main(String[] args) {

		// Created an array called array with integer values
		int array[] = { 1, 3, 6, 7, 12 };
		// Calls the method minimumGap with the parameter array
		// Outputs the minimum gap within array
		System.out.println(minimumGap(array));

	}

	//Creates the method minimumGap that takes an integer array
	public static int minimumGap(int[] array) {

		// Sets the initial minimum gap value to the first gap
		int gapMin = array[1] - array[0];
		//Scans through the array to determine the minimum gap
		for (int i = 1; i < array.length - 1; i++) {
			int gap = array[i + 1] - array[i];
			if (gap < gapMin) {
				gapMin = gap;
			}
		}
		return gapMin;

	}

}