package boundaries;

import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Boundary class responsible for displaying items
 * that are specific to the student. The User Interface
 * for the student interacting with the system.
 */
public class StudentUI {
	private static Scanner scn = new Scanner(System.in);
	public static void welcomeMsg(String studentName) {
		System.out.println("Welcome to MySTARS " + studentName);
	}
	
	public static String addCourseIndexMsg() {
		System.out.println("Enter course index to add: ");
		while (true) {
			try {
				String index = scn.nextLine();
				return index;
			} catch(InputMismatchException e){
				System.out.println("Please enter a valid index: ");
			}
		}
	}
	
	public static void courseDoesNotExistMsg() {
		System.out.println("Sorry, the entered course does not exists.");
		System.out.println("__________________________________________");
	}
}
