package boundaries;

import java.util.Scanner;

import controls.DatabaseControl;
import controls.StudentControl;
import entities.School;
import entities.Student;

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
            		String courseAdd = scn.next();
            		System.out.println("Enter course index to add: ");
            		String indexAdd = scn.next();
            		StudentControl.addCourse(currentStudent, courseAdd, indexAdd);
            		break;
            		
            	// Option for dropping a course
            	case 2:
            		System.out.println("Enter the course code you would like to drop: ");
            		String courseDrop = scn.next();
            		System.out.println("Enter course index to drop: ");
            		String indexDrop = scn.next();
            		StudentControl.dropCourse(currentStudent, courseDrop, indexDrop);
            		break;
            		
            	// Option to check registered courses
            	case 3:
            		System.out.println("Here are your registered courses: ");
            		StudentControl.viewRegisteredCourses(currentStudent);
            		break;
            		
            	// Check vacancies available
            	case 4:
            		System.out.println("Enter the course code you would like to check vacancy for: ");
            		String courseVacancy = scn.next();
            		StudentControl.checkVacancy(courseVacancy);
            		break;
            		
            	// Change index
            	case 5:
            		System.out.println("Enter course code you want to change: ");
            		String courseForIndex = scn.next();
            		System.out.println("Enter old index: ");
            		String prevIndex = scn.next();
            		System.out.println("Enter new index: ");
            		String newIndex = scn.next();
            		StudentControl.changeIndex(currentStudent, courseForIndex, prevIndex, newIndex);
            		break;
            	
            	// Swap index with another student
            	case 6:
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
	
	public static boolean allowedPeriod(Student currentStudent) {
		DatabaseControl dbControl = new DatabaseControl();
		School studentSchool = dbControl.getSchoolData(currentStudent.getSchoolID());
		
		if (!(studentSchool.getAccessPeriod().isValidPeriod())) {
			System.out.println("You are not allowed to access MYStars now. Come back when your access period starts!");
			return false;
		}
		
		return true;
	}
}
