
/*
 * Min Young Kim and Ramon Bautista
 * 11/09/17
 * Code that takes in a English sentence and converts it to morse code
 */

import java.util.Scanner;

public class MorseCodeProgramNew {

	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		System.out.println("Please enter a sentence in all UPPERCASE letters, with no punctuations and special symbols.");
		// Converts the string input to a all uppercase, char string
		String input = scan1.nextLine().toUpperCase();
		// Creates the array that holds the morse code
		String newCharacter = ("");
		// Scans through the English sentence to convert all the letters to morse code.
		scan1.close();
		for (int i = 0; i < input.length(); i++) {
			switch (input.charAt(i)) {
			case ('A'):
				newCharacter = ".- ";
				break;
			case ('B'):
				newCharacter = "-... ";
				break;
			case ('C'):
				newCharacter = "-.-. ";
				break;
			case ('D'):
				newCharacter = "-.. ";
				break;
			case ('E'):
				newCharacter = ". ";
				break;
			case ('F'):
				newCharacter = "..-. ";
				break;
			case ('G'):
				newCharacter = "--. ";
				break;
			case ('H'):
				newCharacter = ".... ";
				break;
			case ('I'):
				newCharacter = ".. ";
				break;
			case ('J'):
				newCharacter = ".--- ";
				break;
			case ('K'):
				newCharacter = "-.- ";
				break;
			case ('L'):
				newCharacter = ".-.. ";
				break;
			case ('M'):
				newCharacter = "-- ";
				break;
			case ('N'):
				newCharacter = "-. ";
				break;
			case ('O'):
				newCharacter = "--- ";
				break;
			case ('P'):
				newCharacter = ".--. ";
				break;
			case ('Q'):
				newCharacter = "--.- ";
				break;
			case ('R'):
				newCharacter = ".-. ";
				break;
			case ('S'):
				newCharacter = "... ";
				break;
			case ('T'):
				newCharacter = "- ";
				break;
			case ('U'):
				newCharacter = "..- ";
				break;
			case ('V'):
				newCharacter = "...- ";
				break;
			case ('W'):
				newCharacter = ".-- ";
				break;
			case ('X'):
				newCharacter = "-..- ";
				break;
			case ('Y'):
				newCharacter = "-.-- ";
				break;
			case ('Z'):
				newCharacter = "--.. ";
				break;
			case (' '):
				newCharacter = "  ";
				break;
			default:
				continue;
			}
			System.out.print(newCharacter);
		}
	}
}
