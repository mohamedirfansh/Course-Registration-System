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
	
	/**
	 * Prints welcome message onto screen once student logs in.
	 * Displays student's name in the message.
	 * @param studentName
	 */
	public static void welcomeMsg(String studentName) {
		System.out.println("Welcome to MySTARS " + studentName);
	}
	
	/**
	 * Prints message to ask user to enter the course they want to enroll in.
	 * Carries out a check to see if the input is valid, if invalid, asks the user
	 * to enter valid input again.
	 * @return course
	 */
	public static String addCourseMsg() {
		System.out.println("Enter the course code you would like to enroll in: ");
		while (true) {
			try {
				String course = scn.nextLine();
				return course;
			} catch (InputMismatchException e) {
				System.out.println("Please enter a valid index: ");
			}
		}
	}
	
	/**
	 * Prints message to ask user to enter the index they want to register to.
	 * Carries out a check to see if the input is valid, if invalid, asks the user
	 * to enter valid input again.
	 * @return index
	 */
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
	
	/**
	 * Prints message that the course requested does not exist.
	 */
	public static void courseDoesNotExistMsg() {
		System.out.println("Sorry, the entered course does not exists.");
		System.out.println("__________________________________________");
	}
	
	public static void alreadyEnrolledIndexMsg() {
		System.out.println("You are already enrolled in this index.");
	}
}
