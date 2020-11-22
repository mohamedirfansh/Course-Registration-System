package controls;

import java.util.Scanner;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import boundaries.StudentUIMsg;
import entities.Course;
import entities.Index;
import entities.Student;

/**
 * This is a control class for the Student object.
 * It contains the methods (main logic) that a 
 * student entity is supposed to be able to do in the system.
 */
public class StudentControl {

	private static Student currentStudent;
	Scanner scn = new Scanner(System.in);
	
	// Constructor
	public StudentControl(Student currentStudent) {
		this.currentStudent = currentStudent;
	}
	
	/**
	 * Method that contains logic to add course for a
	 * student. Called by the student object.
	 * @param studentID
	 */
	public static void addCourse(String course, String index) {
		DatabaseControl dbControl = new DatabaseControl();
		
		Course currentCourse = dbControl.getCourseData(course);
		Index currentIndex = currentCourse.findIndex(index);
		
		// Check to see if there actually is a course with that index, if there is not
		// then display the courseDoesNotExistMsg() and exit the method.
		if (currentIndex == null) {
			StudentUIMsg.courseDoesNotExistMsg();
			return;
		}
		
		// Check not needed as it is already done in the index class
//		try {
//			// Check if the current index already has this student enrolled in
//			ArrayList<Student> listOfStudents = currentIndex.getEnrolled();
//			for (Student student : listOfStudents) {
//				if (student.getUserID() == studentID) {
//					StudentUIMsg.alreadyEnrolledIndexMsg();
//					return;
//				}
//			}
//		}
		
		// TODO: check for clash
		currentIndex.registerStudent(currentStudent.getUserID());
		StudentUIMsg.successfullyEnrolledMsg();

		// Update back to the database
		dbControl.updateCourseData(course, currentCourse);
	}
	
	/**
	 * Method to drop the course for a student.
	 * Called by the student object.
	 */
	public static void dropCourse(String course, String index) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Course currentCourse = dbControl.getCourseData(course);
		Index currentIndex = currentCourse.findIndex(index);
		
		// Check if the current index really has this student enrolled in
		ArrayList<String> listOfStudents = currentIndex.getEnrolled();
		for (String student : listOfStudents) {
			if (student == currentStudent.getUserID()) {
				StudentUIMsg.notInIndexMsg();
				return;
			}
		}
		
		// If all's good, remove the student
		currentIndex.deregisterStudent(currentStudent.getUserID());
		
		// Update back to the database
		dbControl.updateCourseData(course, currentCourse);
	}
	
	public static void checkVacancy(String course) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Course currentCourse = dbControl.getCourseData(course);
		ArrayList<Index> listOfIndex = currentCourse.getCourseIndex();
		
		System.out.println("Index\tVacancy");
		for (Index i : listOfIndex) {
			System.out.printf("%s\t%d\n", i.getIndexCode(), i.getVacancy());
		}
	}
	
	public static void changeIndex(String course, String prevIndex, String newIndex) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Course currentCourse = dbControl.getCourseData(course);
		Index currentOldIndex = currentCourse.findIndex(prevIndex);
		Index currentNewIndex = currentCourse.findIndex(newIndex);
		
		currentOldIndex.deregisterStudent(currentStudent.getUserID());
		currentNewIndex.registerStudent(currentStudent.getUserID());
		
		dbControl.updateCourseData(course, currentCourse);
		dbControl.updateStudentData(currentStudent.getUserID(), currentStudent);
		
	}
	
	public static void viewRegisteredCourses() {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Student currentStud = dbControl.getStudentData(currentStudent.getUserID());
		HashMap<String, String> listOfRegisteredCourses = currentStud.retrieveRegisteredCourses();
		
		System.out.println("Course\tIndex");
		for (Map.Entry<String, String> mapElement : listOfRegisteredCourses.entrySet()) {
			String key = (String)mapElement.getKey();
			String value = (String)mapElement.getValue();
			
			System.out.printf("%s\t%s\n", key, value);
		}
	}
	
	public static void swapIndex(String friendID) {
		//TODO
	}
	
	//TODO
	public void isClashBetIndex(Index newIndex) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Student temp = dbControl.getStudentData(currentStudent.getUserID());
		List<String> listOfIndices = new ArrayList<String>(temp.retrieveRegisteredCourses().values());
		
		for (int i=0; i<listOfIndices.size(); i++) {
			if (listOfIndices.get(i))
		}
	}
}
