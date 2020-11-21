package boundaries;

import java.util.Scanner;

import controls.StudentControl;
import entities.Student;

public class StudentUI {
	private static boolean loggedIn = true;
	
	public static void StudentUIMain(Student currentStudent) {
		Scanner scn = new Scanner(System.in);
		
		System.out.println("Welcome!");
		while (loggedIn) {
            // Menu
			System.out.println("Please select an option:");
            System.out.println("1) Add course");
            System.out.println("2) Drop course");
            System.out.println("3) View Registered Courses");
            System.out.println("4) Check Vacancies Available ");
            System.out.println("5) Change Index Number of Course ");
            System.out.println("6) Swap Index Number with Another Student");
            System.out.println("7) Logout of MYStars");
 
            System.out.println(" ");
            System.out.println("Option: ");
            int option = scn.nextInt();
            
            switch(option) {
            	// Option for adding a course
            	case 1:
            		System.out.println("Enter the course code you would like to enroll in: ");
            		String courseAdd = scn.nextLine();
            		System.out.println("Enter course index to add: ");
            		String indexAdd = scn.nextLine();
            		StudentControl.addCourse(courseAdd, indexAdd);
            		break;
            		
            	// Option for dropping a course
            	case 2:
            		System.out.println("Enter the course code you would like to drop: ");
            		String courseDrop = scn.nextLine();
            		System.out.println("Enter course index to drop: ");
            		String indexDrop = scn.nextLine();
            		StudentControl.dropCourse(courseDrop, indexDrop);
            		break;
            		
            	// Option to check registered courses
            	case 3:
            		break;
            		
            	case 4:
            		System.out.println("Enter the course code you would like to check vacancy for: ");
            		String courseVacancy = scn.nextLine();
            		StudentControl.checkVacancy(courseVacancy);
            		break;
            		
            	// Change index
            	case 5:
            		System.out.println("Enter course code you want to change: ");
            		String course = scn.nextLine();
            		System.out.println("Enter old index: ");
            		String prevIndex = scn.nextLine();
            		System.out.println("Enter new index: ");
            		String newIndex = scn.nextLine();
            		StudentControl.changeIndex(course, prevIndex, newIndex);
            		break;
            		
            	case 6:
            		//swap index
            		break;
            	
            	// Option to logout
            	case 7:
                    System.out.println("Thanks for using myStars. See you again!");
                    loggedIn = false;
                    break;

                // Default option when an invalid or number out of range is entered
            	default:
            		System.out.println("No such option! Please enter a valid option.");
            		break;
            }
		}
	}
}
