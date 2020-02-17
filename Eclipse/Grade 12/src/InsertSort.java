/*
 * Min Young Kim
 * 3/21/18
 * Code that uses insertion sorting to sort an array of integers into order
 */

public class InsertSort {

	public static void main(String[] args) {

		System.out.print("Insertion Sort\n");
		int[] array = { 4, 2, 3, 1, 9, 7, 10, 11, 24, 15, 6 };
		InsSort(array);
		print(array);

	}

	public static void InsSort(int[] array1) {

		//Cycles through each element in the array
		for (int i = 1; i < array1.length; i++) {
			int counter = i;
			int compare = i - 1;
			//Checks if the current element is less than the previous element
			//and swaps them if they are
			while (array1[counter] < array1[compare]) {
				swap(array1, counter, compare);
				if (compare > 0) {
					counter--;
					compare--;
				} else {
					break;
				}
			}
		}
	}

	// Method that swaps the indexes of two values in an array
	public static void swap(int[] array, int i, int j) {
		int temp = array[j];
		array[j] = array[i];
		array[i] = temp;
	}

	// Outputs the arrays
	public static void print(int[] a) {

		for (int i = 0; i < a.length; i++) {
			System.out.print(a[i] + " ");
		}
		System.out.print("\n");
	}
}
