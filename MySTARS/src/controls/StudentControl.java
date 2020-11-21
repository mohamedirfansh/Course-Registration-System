package controls;

import java.util.Scanner;
import java.util.ArrayList;
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

	private Student currentStudent;
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
	public void addCourse(String studentID) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		String course;
		String index;
		
		course = StudentUIMsg.addCourseMsg(1);
		index = StudentUIMsg.addCourseMsg(2);
		
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
		currentIndex.registerStudent(currentStudent);
		StudentUIMsg.successfullyEnrolledMsg();

		// Update back to the database
		dbControl.updateCourseData(course, currentCourse);
	}
	
	/**
	 * Method to drop the course for a student.
	 * Called by the student object.
	 */
	public void dropCourse() {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		String course;
		String index;
		
		course = StudentUIMsg.dropCourseMsg(1);
		index = StudentUIMsg.dropCourseMsg(2);
		
		Course currentCourse = dbControl.getCourseData(course);
		Index currentIndex = currentCourse.findIndex(index);
		
		// Check if the current index really has this student enrolled in
		ArrayList<Student> listOfStudents = currentIndex.getEnrolled();
		for (Student student : listOfStudents) {
			if (student.getUserID() == currentStudent.getUserID()) {
				StudentUIMsg.notInIndexMsg();
				return;
			}
		}
		
		// If all's good, remove the student
		currentIndex.deregisterStudent(currentStudent);
		
		// Update back to the database
		dbControl.updateCourseData(course, currentCourse);
	}
	
	public static void checkVacancy() {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		String course;
		course = StudentUIMsg.checkVacancyMsg();
		
		Course currentCourse = dbControl.getCourseData(course);
		ArrayList<Index> listOfIndex = currentCourse.getCourseIndex();
		
		System.out.println("Index\tVacancy");
		for (Index i : listOfIndex) {
			System.out.printf("%s\t%d", i.getIndexCode(), i.getVacancy());
		}
	}
	
	public void changeIndex() {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		String course;
		String prevIndex;
		String newIndex;
		ArrayList<String> indexData;
		
		indexData = StudentUIMsg.changeIndexMsg();
		course = indexData.get(0);
		prevIndex = indexData.get(1);
		newIndex = indexData.get(2);
		
		Course currentCourse = dbControl.getCourseData(course);
		Index currentOldIndex = currentCourse.findIndex(prevIndex);
		Index currentNewIndex = currentCourse.findIndex(newIndex);
		
		currentOldIndex.deregisterStudent(currentStudent);
		currentNewIndex.registerStudent(currentStudent);
		
	}
	
	public static void swapIndex() {
		//TODO
	}
	
	//TODO
	public void isClashBetIndex(Index newIndex) {
		DatabaseControl dbControl = new DatabaseControl('u');
		
		Student temp = dbControl.getStudentData(currentStudent.getUserID());
		ArrayList<Course> listOfCourses = temp.getRegisteredCourses();
		
		for (Course c : listOfCourses) {
			if (c.getCourseIndex())
		}
	}
}
