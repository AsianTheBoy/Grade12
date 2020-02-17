
/*
 * Min Young Kim and Ramon Bautista
 * 18/10/17
 * Code that acts as a point of sale. Takes in the user's order and prints their receipt
 */

import java.util.Scanner;

public class pointOfSale {

	public static void main(String[] args) {

		Scanner scan1 = new Scanner(System.in);
		System.out.println("Welcome to Enrico's Filipino Bambino!");
		System.out.println("Please enter the password");
		// Assigns the user's password input to "pass"
		String pass = scan1.nextLine();
		// Calls the method checker with the parameter pass
		checker(pass);
		System.out.println("How many meals will you be eating today?");
		// Assigns the number of meals to "meals"
		int meals = scan1.nextInt();
		// Creates a string array with the values of the menu
		String[] menuitems = { "Lumpia", "Sinigang", "Lechon", "Pork Adobo", "Cassava Cake" };
		// Calls the array menu
		menu();
		// Creates the int array order that is as long as the number of meals ordered
		int[] order = new int[meals];
		System.out.println("What would you like to order?");
		// Assigns the user's orders to the array order
		for (int i = 0; i < meals; i++) {
			System.out.println("*You have " + (meals - i) + " order(s) remaining*");
			int input = scan1.nextInt();
			// Makes sure that user's input is between 1 and 5
			if (input > 5 || input < 1) {
				System.out.println("Please enter a number between 1 and 5");
				i--;
				continue;
			}
			order[i] = input;
		}

		System.out.println("\nPlease select your Dining options below:");
		System.out.println("1. Dine In");
		System.out.println("2. Take-out");
		System.out.println("3. Delivery");
		// Assigns the user's dining option input to "seating"
		int seating = scan1.nextInt();
		// Assigns the return value of "counter" to "orderlist"
		double[] orderlist = counter(order, meals);
		/*
		 * Calls the method receipt with the parameters "orderlist", "menuitems" and
		 * "seating"
		 */
		receipt(orderlist, menuitems, seating);
		scan1.close();
	}

	// Method that checks the user's password input to see if it is correct
	public static void checker(String password) {
		int i = 3;
		while (i > 0) {
			if (password.equals("ICS4U1")) {
				System.out.println("\nYou have entered the correct password!\n");
				break;
				// If the user fails 3 times, quits the code
			} else if (i == 1) {
				System.out.println("You have been locked out");
				System.exit(0);
				/*
				 * If the user inputs an incorrect password, tells them how many attempts they
				 * have remaining and loops again.
				 */
			} else if (password != "ICS4U1") {
				i--;
				System.out.println("\nThe password was incorrect");
				System.out.println("You have " + i + " attempts remaining");
				System.out.println("\nPlease enter the password");
				Scanner scan1 = new Scanner(System.in);
				password = scan1.nextLine();
			}
		}
	}

	// Method that outputs the menu that uses formatting code to align them into
	// columns
	public static void menu() {
		System.out.println("\nThis is our menu");
		System.out.printf("%-16s%-24s\n", "1: Lumpia", "$8.00");
		System.out.printf("%-16s%-24s\n", "2: Sinigang", "$8.00");
		System.out.printf("%-16s%-24s\n", "3: Lechon", "$8.00");
		System.out.printf("%-16s%-24s\n", "4: Pork Adobo", "$8.00");
		System.out.printf("%-16s%-24s\n", "5: Cassava Cake", "$8.00\n");
	}

	// Method that counts how many of each meal was ordered and stores it in an
	// array
	public static double[] counter(int[] array, int num) {
		double[] orderlist = new double[5];
		for (int i = 0; i < num; i++) {
			if (array[i] == 1) {
				orderlist[0]++;
			} else if (array[i] == 2) {
				orderlist[1]++;
			} else if (array[i] == 3) {
				orderlist[2]++;
			} else if (array[i] == 4) {
				orderlist[3]++;
			} else if (array[i] == 5) {
				orderlist[4]++;
			}
		}
		return orderlist;
	}

	// Method that prints out the receipt
	public static void receipt(double[] array, String[] menu, int seating) {

		System.out.println("\n---------RECEIPT---------\n");
		double price = 0.00;
		// Prints out the user's orders
		for (int i = 0; i < array.length; i++) {
			price += (array[i] * 8.00);
			if (array[i] != 0) {
				System.out.printf("%-6s%-14s%-1s%-1s\n", array[i], menu[i], "$", (array[i] * 8.00));
			}
		}

		System.out.println("\n-------------------------");
		// Prints the subtotal
		System.out.printf("\nSUBTOTAL: $%.2f %n", price);
		// Depending on the dining option determines the discount, tax, extra cost and
		// total
		if (seating == 1) {
			System.out.println("DISCOUNT: $0.00");
			System.out.printf("TAX: $%.2f %n", (price * 0.13));
			System.out.println("EXTRA CHARGES: $0.00");
			price *= 1.13;
			System.out.printf("TOTAL: $%.2f %n", price);
		} else if (seating == 2) {
			System.out.printf("DISCOUNT: $%.2f %n", price * 0.1);
			price = price - (price * 0.10);
			System.out.printf("TAX: $%.2f %n", (price * 0.13));
			System.out.println("EXTRA CHARGES: $0.00");
			price *= 1.13;
			System.out.printf("TOTAL: $%.2f %n", price);
		} else if (seating == 3) {
			System.out.println("DISCOUNT: $0.00");
			System.out.printf("TAX: $%.2f %n", (price * 0.13));
			price *= 1.13;
			if (price < 25) {
				price += 3.5;
				System.out.println("EXTRA CHARGES: $3.50");
				System.out.printf("TOTAL: $%.2f %n", price);
			} else {
				System.out.println("EXTRA CHARGES: $0.00");
				System.out.printf("TOTAL: $%.2f %n", price);
			}
		}
	}
}

/* CHALLENGES:
 * One main challenge we faced writing this code was having trouble sending our individual 
 * parts of the code to each other so we can combine it and edit it together. Another trouble we 
 * had was figuring out how to calculate the tax, discount and total costs because each dining option had different discounts
 * and different order in adding the taxes. In addition to this aligning the receipt into columns was difficult because 
 * we had to search up how to format the code. Finally, counting how many of each meal the user had ordered was difficult
 * since the user could enter each meal in a different order. Since we used the integer data type 
 * we aren’t able to take in orders of over 32,767 meals because that is the limit of the integer data type. Finally, if the 
 * user does not input a numerical value for the number of meals and dining options our code will return 
 * an error because we only take in integer inputs.
 */

