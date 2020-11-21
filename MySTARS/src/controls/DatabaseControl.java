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
	DatabaseControl(char domain) {
		this.domain = domain;
	}

	// User object retrieval based on userID
	public Users getUserData(String userID) {

		if (domain == 'u') { // Check the domain, whether students or staff
			temp = (ArrayList) SerializeDB.readSerializedObject(STUDENT);
		}
		else {
			temp = (ArrayList) SerializeDB.readSerializedObject(STAFF);
		}

		Users empty = null;
		
		// Search through the list of User objects
		// return object if found
		for (int i = 0; i < temp.size(); i++) {
			Users u = (Users)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("user " + userID + " found!");
				return u;
			}
		}

		// return null object if not found
		System.out.println("user " + userID + " not found!");
		return empty;
	}

	// User object addition
	public void addUserData(Users newUser) {

		if (domain == 'u') {
			temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);
		}
		else {
			temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);
		}

		// Search through the list of User objects
		// return if object to be added is already inside database
		for (int i = 0; i < temp.size(); i++) {
			Users u = (Users)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				System.out.println("user " + newUser.getUserID() + " already inside database!");
				return;
			}
		}

		// add to list the new User object
		temp.add(newUser);
		System.out.println("Added new user with name " + newUser.getName());

		// write to binary file
		if (domain == 'u') {
			SerializeDB.writeSerializedObject(STUDENT, temp);
		}
		else {
			SerializeDB.writeSerializedObject(STAFF, temp);
		}
		
	}


	// User object update
	public void updateUserData(String userID, Users updatedUser) {

		if (domain == 'u') {
			temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);
		}
		else {
			temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);
		}
		
		// Search through list of User objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Users u = (Users)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Updating user " + u.getUserID() + " ...");
				temp.set(i, updatedUser);
				if (domain == 'u') {
					SerializeDB.writeSerializedObject(STUDENT, temp);
				}
				else {
					SerializeDB.writeSerializedObject(STAFF, temp);
				}
				return;
			}
		}

		System.out.println("user " + userID + " not found!");
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
	public School getSchoolData(String testInitials) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);
		School empty = null;

		// Search and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolInitials().equals(testInitials)) {
				System.out.println("School " + testInitials + " found!");
				return s;
			}
		}

		// return empty if not found
		System.out.println("School " + testInitials + " not found!");
		return empty;
	}

	// Add new School object
	public void addSchoolData(School newSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search if new object to be added already exists in the database
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (newSchool.getSchoolInitials().equals(s.getSchoolInitials())) {
				System.out.println("School " + newSchool.getSchoolInitials() + " already inside database");
				return;
			}
		}

		// Add new object to the list
		temp.add(newSchool);
		System.out.println("Added new School with initials " + newSchool.getSchoolInitials());

		// Write to binary file
		SerializeDB.writeSerializedObject(SCHOOL, temp);
	}

	// Update School object
	public void updateSchoolData(String schoolInitials, School updatedSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search for object to be updated
		// update and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolInitials().equals(schoolInitials)) {
				System.out.println("Updating School " + schoolInitials + " ...");
				temp.set(i, updatedSchool);
				SerializeDB.writeSerializedObject(SCHOOL, temp);	
				return;
			}
		}

		System.out.println("school " + schoolInitials + " not found");
	}
}


		

