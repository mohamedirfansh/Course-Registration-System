package controls;

import entities.Student;
import entities.User;
import entities.Staff;
import entities.Course;
import entities.School;
import entities.Hash;
import controls.SerializeDB;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.util.List;
import java.util.HashMap;
import java.io.File;
import java.nio.file.Paths;
import java.nio.file.Path;

public class DatabaseControl {

	
	private List temp; // temp List to store all the objects read in from the binary files
	private char domain; // either 'u' or 's'
	private HashMap<String, String> tempPW;

	// define filename constants
	static final String STUDENT = System.getProperty("user.dir") + "/src/data/student.dat";
	static final String STAFF = System.getProperty("user.dir") + "/src/data/staff.dat";
	static final String COURSE = System.getProperty("user.dir") + "/src/data/course.dat";
	static final String SCHOOL = System.getProperty("user.dir") + "/src/data/school.dat";
	static final String STUDENTPASSWORD = System.getProperty("user.dir") + "/src/data/studentPassword.dat";
	static final String STAFFPASSWORD = System.getProperty("user.dir") + "/src/data/staffPassword.dat";

	// No special Constructor for DatabaseControl needed 

	public boolean addStudentPassword(String userID, String newPassword) throws NoSuchAlgorithmException {

                tempPW = (HashMap<String, String>)SerializeDB.readSerializedMapObject(STUDENTPASSWORD);

                String newHashedPassword = Hash.encode(newPassword);

                // add to the HashMap the new User ID-Password pair
                tempPW.put(userID, newHashedPassword);

                // write to binary file
                SerializeDB.writeSerializedObject(STUDENT, temp);

                System.out.println("in DatabaseControl.addStudentPassword, added password successfully");

				return true;
	}

	public String getStudentPassword(String userID) {

		tempPW = (HashMap)SerializeDB.readSerializedMapObject(STUDENTPASSWORD);
		
		// Search through list of Student objects
		// if found, replace with new object
		// write to binary file and return
		for (String key: tempPW.keySet()) {
			if (key.equals(userID)) {
				System.out.println(key);
				return tempPW.get(key);
			}
		}

		return null;
	}

	public String getStaffPassword(String userID) {

		tempPW = (HashMap)SerializeDB.readSerializedMapObject(STAFFPASSWORD);
		
		// Search through list of Student objects
		// if found, replace with new object
		// write to binary file and return
		for (String key: tempPW.keySet()) {
			if (key.equals(userID)) {
				System.out.println(key);
				return tempPW.get(key);
			}
		}

		return null;
	}
	
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
	
	public Student getStudentDataID(String identificationKey) {

		temp = (ArrayList) SerializeDB.readSerializedObject(STUDENT);

		Student empty = null;

		// Search through the list of Student objects
		// return object if found
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student) temp.get(i);
			if (u.getIDKey().equals(identificationKey)) {
				System.out.println("Student " + identificationKey + " found!");
				return u;
			}
		}

		// return null object if not found
		System.out.println("Student " + identificationKey + " not found!");
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
	public boolean addStudentData(Student newUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);

		// Search through the list of User objects
		// return if object to be added is already inside database
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				return false;
			}
		}

		// add to list the new User object
		temp.add(newUser);

		// write to binary file
		SerializeDB.writeSerializedObject(STUDENT, temp);

		return true;
	}

	// Staff object addition
	public boolean addStaffData(Staff newUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);

		// Search through the list of Staff objects
		// return if object to be added is already inside database
		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				return false;
			}
		}

		// add to list the new Staff object
		temp.add(newUser);

		// write to binary file
		SerializeDB.writeSerializedObject(STAFF, temp);
		
		return true;
	}


	// Student object update
	public boolean updateStudentData(String userID, Student updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STUDENT);
		
		// Search through list of Student objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(userID)) {
				temp.set(i, updatedUser);
				SerializeDB.writeSerializedObject(STUDENT, temp);
				return true;
			}
		}

		return false;
	}

	// Staff object update
	public boolean updateStaffData(String userID, User updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);
		
		// Search through list of Staff objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(userID)) {
				temp.set(i, updatedUser);
				SerializeDB.writeSerializedObject(STAFF, temp);
				return true;
			}
		}

		return false;
	}

	// Course object retrieval
	public Course getCourseData(String courseCode) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(COURSE);
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
	public boolean addCourseData(Course newCourse) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(COURSE);

		// Search through list of Course objects
		// return if new object already inside database
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (newCourse.getCourseCode().equals(c.getCourseCode())) {
				return false;
			}
		}

		// add new Course object to list
		temp.add(newCourse);

		// Write to binary file
		SerializeDB.writeSerializedObject(COURSE, temp);

		return true;
	}

	// Update Course object
	public boolean updateCourseData(String courseCode, Course updatedCourse) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(COURSE);

		// Search and update if found
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (c.getCourseCode().equals(courseCode)) {
				temp.set(i, updatedCourse);
				SerializeDB.writeSerializedObject(COURSE, temp);	
				return true;
			}
		}

		return false;
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
	public boolean addSchoolData(School newSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search if new object to be added already exists in the database
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (newSchool.getSchoolID() == s.getSchoolID()) {
				return false;
			}
		}

		// Add new object to the list
		temp.add(newSchool);

		// Write to binary file
		SerializeDB.writeSerializedObject(SCHOOL, temp);

		return true;
	}

	// Update School object
	public boolean updateSchoolData(int schoolID, School updatedSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search for object to be updated
		// update and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolID() == schoolID) {
				temp.set(i, updatedSchool);
				SerializeDB.writeSerializedObject(SCHOOL, temp);	
				return true;
			}
		}

		return false;
	}
}


		

