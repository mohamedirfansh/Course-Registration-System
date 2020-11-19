package boundaries;
import entities.Student;
import entities.Staff;
import entities.Users;
import entities.Course;
import entities.School;
import entities.Hash;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DatabaseControl {

	private SerializeDB sdb; // SerializeDB object that does the reading and writing of binary objects to files
	private List temp; // temp List to store all the objects read in from the binary files
	private char domain; // either 'u' or 's'

	// Constructor for DatabaseControl and assign the domain
	// u for students and s for staff
	DatabaseControl(char domain) {
		this.domain = domain;
	}

	// User object retrieval based on userID
	public Users getUserData(String userID) {
		sdb = new SerializeDB();

		if (domain == 'u') { // Check the domain, whether students or staff
			temp = (ArrayList)sdb.readSerializedObject("student.dat");
		}
		else {
			temp = (ArrayList)sdb.readSerializedObject("staff.dat");
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
		sdb = new SerializeDB();

		if (domain == 'u') {
			temp = (ArrayList)sdb.readSerializedObject("student.dat");
		}
		else {
			temp = (ArrayList)sdb.readSerializedObject("staff.dat");
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
		sdb.writeSerializedObject("student.dat", temp);
	}


	// User object update
	public void updateUserData(String userID, Users updatedUser) {
		sdb = new SerializeDB();

		if (domain == 'u') {
			temp = (ArrayList)sdb.readSerializedObject("student.dat");
		}
		else {
			temp = (ArrayList)sdb.readSerializedObject("staff.dat");
		}
		
		// Search through list of User objects
		// if found, replace with new object
		// write to binary file and return
		for (int i = 0; i < temp.size(); i++) {
			Users u = (Users)temp.get(i);
			if (u.getUserID().equals(userID)) {
				System.out.println("Updating user " + u.getUserID() + " ...");
				temp.set(i, updatedUser);
				sdb.writeSerializedObject("student.dat", temp);	
				return;
			}
		}

		System.out.println("user " + userID + " not found!");
	}

	// Course object retrieval
	public Course getCourseData(String courseCode) {
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("course.dat");
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
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("course.dat");

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
		sdb.writeSerializedObject("course.dat", temp);
	}

	// Update Course object
	public void updateCourseData(String courseCode, Course updatedCourse) {
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("course.dat");

		// Search and update if found
		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (c.getCourseCode().equals(courseCode)) {
				System.out.println("Updating course " + courseCode + " ...");
				temp.set(i, updatedCourse);
				sdb.writeSerializedObject("course.dat", temp);	
				return;
			}
		}

		System.out.println("course " + courseCode + " not found");
	}

	// Retrieve School object
	public School getSchoolData(String testInitials) {
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("school.dat");
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
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("school.dat");

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
		sdb.writeSerializedObject("school.dat", temp);
	}

	// Update School object
	public void updateSchoolData(String schoolInitials, School updatedSchool) {
		sdb = new SerializeDB();
		
		temp = (ArrayList)sdb.readSerializedObject("school.dat");

		// Search for object to be updated
		// update and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolInitials().equals(schoolInitials)) {
				System.out.println("Updating School " + schoolInitials + " ...");
				temp.set(i, updatedSchool);
				sdb.writeSerializedObject("school.dat", temp);	
				return;
			}
		}

		System.out.println("school " + schoolInitials + " not found");
	}
}


		

