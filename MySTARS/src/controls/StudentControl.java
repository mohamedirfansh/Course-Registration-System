package controls;

import java.util.Scanner;

import boundaries.StudentUI;
import entities.Index;
import entities.Student;

/**
 * This is a control class for the Student object.
 * It contains the methods (main logic) that a 
 * student entity is supposed to be able to do in the system.
 */
public class StudentControl {
	// Temp for now...
	private Student currentStudent;
	Scanner scn = new Scanner(System.in);
	
	public StudentControl() {
		
	}
	
	/**
	 * Method that contains logic to add course for a
	 * student. Called by the student object.
	 * @param studentID
	 */
	public void addCourse(String studentID) {
		String index;
		index = StudentUI.addCourseIndexMsg();
		Index currentIndex = DatabaseControl.getCourseData(index);
		
		if (currentIndex == null) {
			StudentUI.courseDoesNotExistMsg();
			return;
		}
		
		/*
		Course courseObj = new Course();
		courseObj = DBController.getCourseByCourseCode(courseIndexObj.getCourseCode());
		try {
			//Get the list of index for the course and check whether the student is registered in any of it
			ArrayList<String[]> listOfIndex = new ArrayList<String[]>();
			listOfIndex = DBController.getIndexListByCourseCode(courseIndexObj.getCourseCode());
			for (int i = 0; i < listOfIndex.size(); i++) {
				ArrayList<String[]> listOfStudent = new ArrayList<String[]>();
				listOfStudent = DBController.getStudentListByIndex(listOfIndex.get(i)[0]);
				for (int j = 0; j < listOfStudent.size(); j++) {
					if ((listOfStudent.get(j)[0].equals(accountID))) {
						System.out.println(
								"Error. You are already registered for the course.\n" + "Returning to main menu...");
						System.out.println(
								"------------------------------------------------------------------------------------------------------------------------------");
						return;
					}
				}
			}
			//check time clash for new course
			boolean isclash = false;
			ArrayList<String[]> studentCourseList = DBController.getCourseListByStudentID(accountID);
			if (studentCourseList != null) {
				if (studentCourseList.size() != 0) {
					for (int i = 0; i < studentCourseList.size(); i++) {
						String tempindex = studentCourseList.get(i)[0];
						isclash = isTimeClashBetweenIndexes(tempindex, courseIndex);
						if (isclash) {
							System.out.println("You can't add this course index because of day/time clash. \n"
									+ "Returning to main menu...");
							System.out.println(
									"------------------------------------------------------------------------------------------------------------------------------");
							return;
						}
					}
				}

			}
			System.out.println("Select appropriate course type for " + courseIndex);
			System.out.println(
					"1. Core\n" + "2. Prescribed\n" + "3. Unrestricted\n" + "4. Cancel and return to main menu");
			int choice;
			do {
				choice = sc.nextInt();
				switch (choice) {
				case 1:
					courseType = "Core";
					break;
				case 2:
					courseType = "Prescribed";
					break;
				case 3:
					courseType = "Unrestricted";
					break;
				case 4:
					return;
				default:
					System.out.println("Please enter a valid choice");
					break;
				}
			} while (choice < 0 || choice > 4);
			//Check if the course is full
			if (courseIndexObj.getVacancy().equals("0")) {
				System.out.println("Class is full, added to waiting list.\n" + "Returning to main menu...");
				System.out.println(
						"------------------------------------------------------------------------------------------------------------------------------");
				DBController.addToWL(accountID, courseIndex, courseType);
				return;
			}

			String dataLine = accountID + "," + courseIndex + "," + courseType + "," + "NEW";
			String[] dataLineArray = dataLine.split(",");
			DBController.addOneline(wCourseRegFile, dataLineArray);
			DBController.editVacByIndex(courseIndex, '-');

			System.out.println("Registered successfully!");

			System.out.println("Course Name: " + courseObj.getCourseName());
			System.out.println("Course Type: " + courseType);
			System.out.println("Index number: " + courseIndex);
			/*print added Course Index Details. */
			ArrayList<Lesson> indexdetails = DBController.getIndexDetailsByIndex(courseIndex);
			if (indexdetails != null) {
				if (indexdetails.size() != 0) {
					System.out.println("+--------------------------------------------------+");
					System.out.format("|%-11s|%-4s|%-10s|%-7s|%-8s|%-5s|%n", "Type", "Day", "Time", "Venue", "Week",
							"Group");
					System.out.println("+--------------------------------------------------+");
					for (int i = 0; i < indexdetails.size(); i++) {
						System.out.format("|%-11s|%-4s|%-4s-%-5s|%-7s|%-8s|%-5s|%n", indexdetails.get(i).getClassType(),
								indexdetails.get(i).getDay(), indexdetails.get(i).getStarttime(),
								indexdetails.get(i).getEndtime(), indexdetails.get(i).getVenue(),
								indexdetails.get(i).getWeek(), indexdetails.get(i).getGroup());
					}
					System.out.println("+--------------------------------------------------+");
				}
			}
			System.out.println(
					"------------------------------------------------------------------------------------------------------------------------------");

		} catch (Exception e) {
			e.printStackTrace();
		}
		 */
		
	}
	
	public static void dropCourse() {
		pass;
	}
	
	public static void checkVacancy() {
		pass;
	}
	
	public static void changeIndex() {
		pass;
	}
	
	public static void swapIndex() {
		pass;
	}
	
	public static void isClashBetIndex() {
		pass;
	}
}
