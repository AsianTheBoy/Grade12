
/* 
 * Min Young Kim and Ramon Bautista
 * 11/09/17
 * Code that takes in a morse code sentence and converts it to an English sentence
 */

import java.util.Scanner;

public class ReverseMorseCode {

	public static void main(String[] args) {
		System.out.println("Please input a morse code sentence with spaces in between(and use a \"/\" as a space)");
		System.out.println("Eg: .... .. / -... ..- -.. -.. -.-- (HI BUDDY)");
		Scanner scan1 = new Scanner(System.in);
		// Converts the user's input to a char array
		char[] input = scan1.nextLine().toCharArray();
		int numLetters = 1;
		// Finds the number of letters in the sentence
		for (int i = 0; i < input.length; i++) {
			if (input[i] == ' ') {
				numLetters++;
			}
		}
		// Counter decides which index to begin/resume the search for letters
		int counter = 0;
		// Scans through the char array to find the letters of the sentence
		for (int i = 0; i < numLetters; i++) {
			// Calls the method characterFinder with the arguments input and counter and
			// assigns the return value to code
			String code = characterFinder(input, counter);
			// Sets counter equal to the length of the previous segment of morse code + 1
			counter += code.length() + 1;
			// Calls the method decodeLetter with the argument code and prints the return
			// value
			System.out.print(decodeLetter(code));
		}
		scan1.close();
	}

	// Method that finds the dots and dashes that make up one letter
	public static String characterFinder(char[] f, int x) {
		String result = "";
		// Checks the morse code segment until it finds a space
		for (int i = x; i < f.length; i++) {
			switch (f[i]) {
			case ('.'):
				result += ".";
				break;
			case ('-'):
				result += "-";
				break;
			case (' '):
				i = f.length;
				break;
			// The "/" acts as a space
			case ('/'):
				result += " ";
			default:
				i = f.length;
				break;
			}
		}
		return result;
	}

	// Method that converts the dots and dashes to letters
	public static char decodeLetter(String x) {
		char result = ' ';
		switch (x) {
		case (".-"):
			result = 'A';
			break;
		case ("-..."):
			result = 'B';
			break;
		case ("-.-."):
			result = 'C';
			break;
		case ("-.."):
			result = 'D';
			break;
		case ("."):
			result = 'E';
			break;
		case ("..-."):
			result = 'F';
			break;
		case ("--."):
			result = 'G';
			break;
		case ("...."):
			result = 'H';
			break;
		case (".."):
			result = 'I';
			break;
		case (".---"):
			result = 'J';
			break;
		case ("-.-"):
			result = 'K';
			break;
		case (".-.."):
			result = 'L';
			break;
		case ("--"):
			result = 'M';
			break;
		case ("-."):
			result = 'N';
			break;
		case ("---"):
			result = 'O';
			break;
		case (".--."):
			result = 'P';
			break;
		case ("--.-"):
			result = 'Q';
			break;
		case (".-."):
			result = 'R';
			break;
		case ("..."):
			result = 'S';
			break;
		case ("-"):
			result = 'T';
			break;
		case ("..-"):
			result = 'U';
			break;
		case ("...-"):
			result = 'V';
			break;
		case (".--"):
			result = 'W';
			break;
		case ("-..-"):
			result = 'X';
			break;
		case ("-.--"):
			result = 'Y';
			break;
		case ("--.."):
			result = 'Z';
			break;
		}
		return result;
	}
}