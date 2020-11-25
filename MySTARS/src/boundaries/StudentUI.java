package boundaries;

import java.util.ArrayList;
import java.util.Scanner;

import controls.DatabaseControl;
import controls.StudentControl;
import entities.Course;
import entities.Index;
import entities.School;
import entities.Student;

/**
 * Provides an interface UI and the corresponding methods for a student to access and modify the database
 * with student level access.
 */
public class StudentUI {
	private static boolean loggedIn = true;
	
	public static void StudentUIMain(Student currentStudent) {
		Scanner scn = new Scanner(System.in);
		
		if (!(allowedPeriod(currentStudent))) {
			return;
		}
		
		System.out.println("Welcome back " + currentStudent.getName() + "!");
		while (loggedIn) {
            // Menu
			System.out.println("");
			System.out.println("Please select an option:");
            System.out.println("1) Add course");
            System.out.println("2) Drop course");
            System.out.println("3) View Registered Courses");
            System.out.println("4) Check Vacancies Available ");
            System.out.println("5) Change Index Number of Course ");
            System.out.println("6) Swap Index Number with Another Student");
            System.out.println("7) View all Available Courses");
            System.out.println("8) Logout of MYStars");
 
            System.out.println("");
            System.out.println("Option: ");
            char option = scn.next().charAt(0);
            
            switch(option) {
            	// Option for adding a course
            	case '1':
            		viewAllCourses();
            		System.out.println("Enter the course code you would like to enroll in: ");
            		String courseAdd = scn.next();
            		System.out.println("Enter course index to add: ");
            		String indexAdd = scn.next();
            		StudentControl.addCourse(currentStudent, courseAdd, indexAdd);
            		break;
            		
            	// Option for dropping a course
            	case '2':
            		viewAllRegisteredCourses(currentStudent);
            		System.out.println("Enter the course code you would like to drop: ");
            		String courseDrop = scn.next();
            		System.out.println("Enter course index to drop: ");
            		String indexDrop = scn.next();
            		StudentControl.dropCourse(currentStudent, courseDrop, indexDrop);
            		break;
            		
            	// Option to check registered courses
            	case '3':
            		viewAllRegisteredCourses(currentStudent);
            		break;
            		
            	// Check vacancies available
            	case '4':
            		viewAllCourses();
            		System.out.println("Enter the course code you would like to check vacancy for: ");
            		String courseVacancy = scn.next();
            		StudentControl.checkVacancy(courseVacancy);
            		break;
            		
            	// Change index
            	case '5':
            		viewAllRegisteredCourses(currentStudent);
            		System.out.println("Enter course code you want to change: ");
            		String courseForIndex = scn.next();
            		System.out.println("Enter new index: ");
            		String newIndex = scn.next();
            		StudentControl.changeIndex(currentStudent, courseForIndex, newIndex);
            		break;
            	
            	// Swap index with another student
            	case '6':
            		viewAllRegisteredCourses(currentStudent);
            		System.out.println("Enter course code you want to change: ");
            		String courseWithFriend = scn.next();
            		System.out.println("Enter your current index: ");
            		String currIndex = scn.next();
            		System.out.println("Enter your friend's username: ");
            		String friendName = scn.next();
            		System.out.println("Enter your friend's password: ");
            		String friendPassword = scn.next();
            		System.out.println("Enter your friend's index: ");
            		String friendIndex = scn.next();
            		StudentControl.swapIndex(currentStudent, courseWithFriend, currIndex, friendName, friendPassword, friendIndex);
            		break;
            		
                // Option to view all available courses
                case '7':
                	viewAllCourses();
                	break;
                		
            	// Option to logout
            	case '8':
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

	/**
	 * Checks if the student can currently access the STARS system.
	 * @param currentStudent, the student trying to access the system.
	 * @return false, if the student tried to access the system before or after the access period.
	 * 		true, if the student tries to access the system during the access period.
	 */
	public static boolean allowedPeriod(Student currentStudent) {
		DatabaseControl dbControl = new DatabaseControl();
		School studentSchool = dbControl.getSchoolData(currentStudent.getSchoolID());
		
		if (!(studentSchool.getAccessPeriod().isValidLoginPeriod())) {
			System.out.println("You are not allowed to access MYStars now. Come back when your access period starts!");
			return false;
		}
		
		return true;
	}

	/**
	 * Display function to view all the available courses.
	 */
	public static void viewAllCourses() {
		DatabaseControl dbControl = new DatabaseControl();
		ArrayList<Course> allCourses = dbControl.getAllCourseData();
		
		System.out.println("Here are all the avaiable courses:");
		System.out.println("Code\tName\t\tIndex");
		for (Course c : allCourses) {
			for (Index i : c.getCourseIndex()) {
				System.out.printf("%s | %s | %s\n", c.getCourseCode(), c.getCourseName(), i.getIndexCode());
			}
		}
	}

	/**
	 * Display function to view all the courses that a student is registered to.
	 * @param currentStudent, the student for which the registered courses are to be displayed.
	 */
	public static void viewAllRegisteredCourses(Student currentStudent) {
		System.out.println("Here are your registered courses: ");
		StudentControl.viewRegisteredCourses(currentStudent);
		System.out.println("");
	}
}
