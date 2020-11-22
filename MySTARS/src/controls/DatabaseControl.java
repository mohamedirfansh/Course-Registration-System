package controls;

import entities.Student;
import entities.Staff;
import entities.Users;
import entities.Course;
import entities.School;
import entities.Hash;
import controls.SerializeDB;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DatabaseControl {

	
	private List temp; // temp List to store all the objects read in from the binary files
	private char domain; // either 'u' or 's'

	// define filename constants
	static final String STUDENT = "student.dat";
	static final String STAFF = "staff.dat";
	static final String COURSE = "course.dat";
	static final String SCHOOL = "school.dat";

	// Constructor for DatabaseControl and assign the domain
	// u for students and s for staff
	/* DatabaseControl(char domain) {
		this.domain = domain;
	} */

	// Student object retrieval based on userID
	public Student getStudentData(String userID) {

		temp = (ArrayList) SerializeDB.readSerializedObject(STUDENT);

		Student empty = null;
		
		// Search through the list of Student objects
		// return object if found
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Student " + userID + " found!");
				return u;
			}
		}

		// return null object if not found
		System.out.println("Student " + userID + " not found!");
		return empty;
	}

	public Staff getStaffData(String userID) {

		temp = (ArrayList) SerializeDB.readSerializedObject(STAFF);

		Staff empty = null;
		
		// Search through the list of Staff objects
		// return object if found
		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Staff " + userID + " found!");
				return u;
			}
		}

		// return null object if not found
		System.out.println("Staff " + userID + " not found!");
		return empty;
	}

	// Student object addition
	public void addStudentData(Student newUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);

		// Search through the list of User objects
		// return if object to be added is already inside database
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				System.out.println("Student " + newUser.getUserID() + " already inside database!");
				return;
			}
		}

		// add to list the new User object
		temp.add(newUser);
		System.out.println("Added new Student with name " + newUser.getName());

		// write to binary file
		SerializeDB.writeSerializedObject(STUDENT, temp);
	}

	// Staff object addition
	public void addStaffData(Staff newUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);

		// Search through the list of Staff objects
		// return if object to be added is already inside database
		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				System.out.println("Staff " + newUser.getUserID() + " already inside database!");
				return;
			}
		}

		// add to list the new Staff object
		temp.add(newUser);
		System.out.println("Added new Staff with name " + newUser.getName());

		// write to binary file
		SerializeDB.writeSerializedObject(STAFF, temp);
	}


	// Student object update
	public void updateStudentData(String userID, Student updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);
		
		// Search through list of Student objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Updating Student " + u.getUserID() + " ...");
				temp.set(i, updatedUser);
				SerializeDB.writeSerializedObject(STUDENT, temp);
				return;
			}
		}

		System.out.println("Student " + userID + " not found!");
	}

	// Staff object update
	public void updateStaffData(String userID, Users updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);
		
		// Search through list of Staff objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Updating Staff " + u.getUserID() + " ...");
				temp.set(i, updatedUser);
				SerializeDB.writeSerializedObject(STAFF, temp);
				return;
			}
		}

		System.out.println("Staff " + userID + " not found!");
	}

	// Course object retrieval
	public Course getCourseData(String courseCode) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject("course.dat");
		Course empty = null;

		// Search through list of Course objects
		// return object if found
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (c.getCourseCode().equals(courseCode)) {
				System.out.println("Course " + courseCode + " found!");
				return c;
			}
		}

		// return empty object if not found
		System.out.println("Course " + courseCode + " not found!");
		return empty;
	}

	// add new Course object
	public void addCourseData(Course newCourse) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject("course.dat");

		// Search through list of Course objects
		// return if new object already inside database
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (newCourse.getCourseCode().equals(c.getCourseCode())) {
				System.out.println("Course " + newCourse.getCourseCode() + " already inside database");
				return;
			}
		}

		// add new Course object to list
		temp.add(newCourse);
		System.out.println("Added new course with code " + newCourse.getCourseCode());

		// Write to binary file
		SerializeDB.writeSerializedObject("course.dat", temp);
	}

	// Update Course object
	public void updateCourseData(String courseCode, Course updatedCourse) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject("course.dat");

		// Search and update if found
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (c.getCourseCode().equals(courseCode)) {
				System.out.println("Updating course " + courseCode + " ...");
				temp.set(i, updatedCourse);
				SerializeDB.writeSerializedObject("course.dat", temp);	
				return;
			}
		}

		System.out.println("course " + courseCode + " not found");
	}

	// Retrieve School object
	public School getSchoolData(int schoolID) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);
		School empty = null;

		// Search and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolID() == schoolID) {
				System.out.println("School with ID " + schoolID + " found!");
				return s;
			}
		}

		// return empty if not found
		System.out.println("School with ID " + schoolID + " not found!");
		return empty;
	}

	// Add new School object
	public void addSchoolData(School newSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search if new object to be added already exists in the database
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (newSchool.getSchoolID() == s.getSchoolID()) {
				System.out.println("School with ID " + newSchool.getSchoolID() + " already inside database");
				return;
			}
		}

		// Add new object to the list
		temp.add(newSchool);
		System.out.println("Added new School with ID " + newSchool.getSchoolID());

		// Write to binary file
		SerializeDB.writeSerializedObject(SCHOOL, temp);
	}

	// Update School object
	public void updateSchoolData(int schoolID, School updatedSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search for object to be updated
		// update and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolID() == schoolID) {
				System.out.println("Updating School with ID " + schoolID + " ...");
				temp.set(i, updatedSchool);
				SerializeDB.writeSerializedObject(SCHOOL, temp);	
				return;
			}
		}

		System.out.println("school with ID " + schoolID + " not found");
	}
}


		

