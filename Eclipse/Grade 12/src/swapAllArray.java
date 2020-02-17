/*
 * Min Young Kim	
 * 04/10/17
 * Swaps the contents of two integer arrays
 */

public class swapAllArray {

	public static void main(String[] args) {

		// Creates an int array called array1
		int[] array1 = { 1, 2, 3, 4, 5, 6 };
		// Creates an int array called array2
		int[] array2 = { 10, 20, 30, 40, 50, 60 };
		// Calls the method swapAll with the parameters array1 and array2
		swapAll(array1, array2);
	}

	// Creates the method swapAll that swaps the contents of 2 int arrays
	public static void swapAll(int[] a1, int[] a2) {
		// Creates a temporary array to hold the value of array 1
		int[] temp;
		// Makes array1 equal to the temporary array
		temp = a1;
		// Makes array1 equal to array2
		a1 = a2;
		// Makes array2 equal to array1
		a2 = temp;
		// Prints out array 1
		System.out.print("array1: {");
		for (int i : a1) {
			System.out.print(i + " ");
		}
		System.out.print("}");

		// Prints out array2
		System.out.print("\narray2: {");
		for (int i : a2) {
			System.out.print(i + " ");
		}
		System.out.print("}");
	}
}
