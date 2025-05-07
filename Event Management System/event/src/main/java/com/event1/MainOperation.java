package com.event1;

import java.util.Scanner;
import static com.event1.AllOperations.*;

public class MainOperation {

	// Scanner instance to read user input from console
	static Scanner sc = new Scanner(System.in);

	// Method to display main operations menu and handle user's choice
	public static void mainOps() {
		// Infinite loop for the main menu, the user can continuously interact
		while (true) {
			// Displaying the main menu
			System.out.println("\n=======================================");
            System.out.println("      🎪 EVENT MANAGEMENT SYSTEM       ");
            System.out.println("\n=======================================");
            System.out.println(" 1. Event Operations                   ");
            System.out.println(" 2. Venue Operations                   ");
            System.out.println(" 3. Organizer Operations               ");
            System.out.println(" 4. Vendor Operations                  ");
            System.out.println(" 5. Participant Operations             ");
            System.out.println(" 6. Exit                               ");
            System.out.println("=======================================");
            System.out.print("👉 Enter your choice (1-6): ");


			// Reading user input (choice for operation)
			int input = sc.nextInt();

			// Switch case based on the user's input choice
			switch (input) {
			// Event Operations option
			case 1:
				eventOperations(); // Calls method to perform event-related operations
				System.out.println("======================================="); // Divider
				break;

				// Venue Operations option
			case 2:
				venueOperations(); // Calls method to perform venue-related operations
				System.out.println("======================================="); // Divider
				break;

				// Organizer Operations option
			case 3:
				organizerOperations(); // Calls method to perform organizer-related operations
				System.out.println("======================================="); // Divider
				break;

				// Vendor Operations option
			case 4:
				vendorOperations(); // Calls method to perform vendor-related operations
				System.out.println("======================================="); // Divider
				break;

				// Participant Operations option
			case 5:
				participantOperations(); // Calls method to perform participant-related operations
				System.out.println("======================================="); // Divider
				break;

				// Quit option to exit the program
			case 6:
				System.out.println("Exiting the system. Goodbye!"); // Farewell message
				System.exit(0); // Exits the program
				break;

				// Default case for invalid input
			default:
				System.out.println("❌ Wrong input! Try again."); // Error message for invalid input
			}
		}
	}

	// Main method to start the program
	public static void main(String[] args) {
		mainOps(); // Calls mainOps method to start the operation menu
	}
}