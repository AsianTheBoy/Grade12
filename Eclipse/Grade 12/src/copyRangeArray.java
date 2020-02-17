/*
 * Min Young Kim
 * 04/10/17
 * Code that takes as parameters two arrays a1 and a2, two starting indexes 
 * i1 and i2, and a length l, and copies the first l elements of a1 starting at
 * index i1 into array a2 starting at index i2
 */

public class copyRangeArray {

	public static void main(String[] args) {

		// Creates the int array a1
		int[] a1 = { 10, 20, 30, 40, 50, 60 };
		// Creates the int array a2
		int[] a2 = { 91, 92, 93, 94, 95, 96 };
		// Calls the method copyRange with the parameters a1, a2, 0, 2 and 3
		copyRange(a1, a2, 0, 2, 3);

	}

	// Creates the method copyRange
	public static void copyRange(int[] array1, int[] array2, int i1, int i2, int l) {

		// Replaces the values of a2 with the values of a1 according to the index
		// parameters
		for (int i = 0; i < l; i++) {
			array2[i2 + i] = array1[i1 + i];
		}
		// Prints out the array a2
		System.out.print("a2 = { ");
		for (int i : array2) {
			System.out.print(i + " ");
		}
		System.out.print("}");
	}
}
