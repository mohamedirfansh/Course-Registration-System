package boundaries;

import java.util.ArrayList;
import java.util.InputMismatchException;
import java.util.Scanner;

/**
 * Boundary class responsible for displaying items
 * that are specific to the student. The User Interface
 * for the student interacting with the system.
 */
public class StudentUIMsg {
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
	 * Prints message to ask user to enter the course/index they want to enroll in.
	 * Carries out a check to see if the input is valid, if invalid, asks the user
	 * to enter valid input again.
	 * @param an integer, 1 for courseMsg, 2 for indexMsg
	 * @return course
	 */
	public static String addCourseMsg(int n) {
		if (n <= 1) {
			System.out.println("Enter the course code you would like to enroll in: ");
			while (true) {
				try {
					String course = scn.nextLine();
					return course;
				} catch (InputMismatchException e) {
					System.out.println("Please enter a course code: ");
				}
			}
		}
		else {
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
	}
	
	
	/**
	 * Prints message that the course requested does not exist.
	 */
	public static void courseDoesNotExistMsg() {
		System.out.println("Sorry, the entered course does not exists.");
		System.out.println("__________________________________________");
	}
	
	/**
	 * Prints message that the student is already enrolled in the index.
	 */
	public static void alreadyEnrolledIndexMsg() {
		System.out.println("You are already enrolled in this index.");
	}
	
	/**
	 * Prints message that the student has successfully enrolled in the course.
	 */
	public static void successfullyEnrolledMsg() {
		System.out.println("Enrollment into course and index Successful!");
	}
	
	/**
	 * Prints message to ask user to enter the course/index they want to drop.
	 * Carries out a check to see if the input is valid, if invalid, asks the user
	 * to enter valid input again.
	 * @param an integer, 1 for courseMsg, 2 for indexMsg
	 * @return course
	 */
	public static String dropCourseMsg(int n) {
		if (n <= 1) {
			System.out.println("Enter the course code you would like to drop: ");
			while (true) {
				try {
					String course = scn.nextLine();
					return course;
				} catch (InputMismatchException e){
					System.out.println("Please enter a valid course: ");
				}
			}
		} else {
			System.out.println("Enter course index to drop: ");
			while (true) {
				try {
					String index = scn.nextLine();
					return index;
				} catch(InputMismatchException e){
					System.out.println("Please enter a valid index: ");
				}
			}
		}
	}
	
	public static void notInIndexMsg() {
		System.out.println("You are not enrolled in that index. Please try again.");
	}
	
	public static String checkVacancyMsg() {
		System.out.println("Enter the course code you would like to check vacancy for: ");
		while (true) {
			try {
				String course = scn.nextLine();
				return course;
			} catch (InputMismatchException e){
				System.out.println("Please enter a valid course: ");
			}
		} 
	}
	
	public static ArrayList<String> changeIndexMsg() {
		ArrayList<String> s = new ArrayList<String>();
		
		System.out.println("Enter course code you want to change: ");
		String course = scn.nextLine();
		s.add(course);
		
		System.out.println("Enter old index: ");
		String prevIndex = scn.nextLine();
		s.add(prevIndex);
		
		System.out.println("Enter new index: ");
		String newIndex = scn.nextLine();
		s.add(newIndex);
		
		return s;
	}
}
