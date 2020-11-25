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

/**
 * The DatabaseControl class defines methods that facilitate easy access to the user and school records in terms
 * of courses, classes, enrollments, and so on..
 *
 * A class that holds an instance of database control can use it to retrive, write and update the files stored on the
 * database.
 *
 * The database in this class is built using binary files.
 *
 * Class Attributes:
 * -> temp: List, which is used to temporarily store the objects read in from the database.
 * -> domain: char, which specifies the access level according to the user type. In this case we have 'u' for
 * 	undergraduate student access, and 's' for staff access.
 * -> tempPw: HashMap<String, String>,
 */
public class DatabaseControl {

	
	private List temp; // temp List to store all the objects read in from the binary files
	private char domain; // either 'u' or 's'
	private HashMap<String, String> tempPW;

	/**
	 * The below attributes define the pathnames to acess the database files. This pathname could differ according
	 * to the OS.
	 */
	/*static final String STUDENT = System.getProperty("user.dir") + "/src/data/student.dat";
	static final String STAFF = System.getProperty("user.dir") + "/src/data/staff.dat";
	static final String COURSE = System.getProperty("user.dir") + "/src/data/course.dat";
	static final String SCHOOL = System.getProperty("user.dir") + "/src/data/school.dat";
	static final String STUDENTPASSWORD = System.getProperty("user.dir") + "/src/data/studentPassword.dat";
	static final String STAFFPASSWORD = System.getProperty("user.dir") + "/src/data/staffPassword.dat";
	*/
	
	static final String STUDENT = System.getProperty("user.dir") + "/../data/student.ser";
	static final String STAFF = System.getProperty("user.dir") + "/../data/staff.dat";
	static final String COURSE = System.getProperty("user.dir") + "/../data/course.dat";
	static final String SCHOOL = System.getProperty("user.dir") + "/../data/school.dat";
	static final String STUDENTPASSWORD = System.getProperty("user.dir") + "/../data/studentPassword.dat";
	static final String STAFFPASSWORD = System.getProperty("user.dir") + "/../data/staffPassword.dat";


	/**
	 * getStudentPassword() is used to read the student account password from the database, and can be used to
	 * verify the student trying to access the database.
	 *
	 * @param userID, which is the userID of the student trying to access the database.
	 * @return The password of the user. Returns null if the user does not exist in the database.
	 */
	public String getStudentPassword(String userID) {
		tempPW = (HashMap)SerializeDB.readSerializedMapObject(STUDENTPASSWORD);
		for (String key: tempPW.keySet()) {
			if (key.equals(userID)) {
				return tempPW.get(key);
			}
		}
		return null;
	}

	/**
	 * getStaffPassword() is used to read the staff account password from the database, and can be used to
	 * verify the staff member trying to access the database.
	 *
	 * @param userID, which is the userID of the staff member trying to access the database.
	 * @return The password of the user. Returns null if the user does not exist in the database.
	 */
	public String getStaffPassword(String userID) {

		tempPW = (HashMap)SerializeDB.readSerializedMapObject(STAFFPASSWORD);
		
		// Search through list of Staff objects
		// if found, replace with new object
		// write to binary file and return
		for (String key: tempPW.keySet()) {
			if (key.equals(userID)) {
				return tempPW.get(key);
			}
		}

		return null;
	}

	/**
	 * addStudentPassword() is used to store the password for a specified user.
	 *
	 * @param userID, the userID for the corresponding password.
	 * @param newPassword, the password being attached to the userID, which verifies the user.
	 * @return boolean, true if the password was associated with the user and stored in the database successfully.
	 * 			Otherwise, it returns false.
	 * @throws NoSuchAlgorithmException This exception is thrown when a particular cryptographic algorithm is
	 *  requested but is not available in the environment.
	 */
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

	/**
	 * getStudentData() traverses the user Database to find the instance of a particular student using the userID.
	 *
	 * @param userID, which is the ID of the student instance we are trying to retrieve.
	 * @return the requested user data. If the corresonding user is not found, a null record is returned.
	 */
	public Student getStudentData(String userID) {

		temp = SerializeDB.readSerializedObject(STUDENT);
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(userID)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * getStudentData() traverses the user Database to find the instance of a particular student using the identification key.
	 *
	 * @param identificationKey, which is the ID of the student instance we are trying to retrieve.
	 * @return the requested user data. If the corresonding user is not found, a null record is returned.
	 */
	public Student getStudentDataID(String identificationKey) {

		temp = (ArrayList) SerializeDB.readSerializedObject(STUDENT);

		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student) temp.get(i);
			if (u.getIDKey().equals(identificationKey)) {
				return u;
			}
		}
		return null;
	}


	/**
	 * getStaffData() traverses the user Database to find the instance of a particular staff member.
	 *
	 * @param userID, which is the ID of the staff instance we are trying to retrieve.
	 * @return the requested user data. If the corresonding staff member is not found, a null record is returned.
	 */
	public Staff getStaffData(String userID) {

		temp = (ArrayList) SerializeDB.readSerializedObject(STAFF);

		for (int i = 0; i < temp.size(); i++) {
			Staff u = (Staff)temp.get(i);
			if (u.getUserID().equals(userID)) {
				return u;
			}
		}
		return null;
	}

	/**
	 * addStudentData() is used to add a new student object to the database, given that this student does not already
	 * exist in the database.
	 * @param newUser : Student, which is the new student instance being added to the database.
	 * @return boolean, true if the student is successfully added to the database.
	 * 					false, if the student was not added to the database, or if the student already exists in
	 * 				the database.
	 */
	public boolean addStudentData(Student newUser) {

		System.out.println("In DBControl addStudentData");
		SerializeDB sdb = new SerializeDB();
		temp = (ArrayList)sdb.readSerializedObject("/home/jjkoh/Desktop/y2s1/cz2002/OODP-Project/MySTARS/src/data/student.ser");
		System.out.println("size of object array" + temp.size());
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(newUser.getUserID())) {
				return false;
			}
		}

		// add to list the new User object
		temp.add(newUser);

		// write to binary file
		SerializeDB.writeSerializedObject("/home/jjkoh/Desktop/y2s1/cz2002/OODP-Project/MySTARS/src/data/student.ser",temp);

		return true;
	}

	/**
	 * addStaffData() is used to add a new staff object to the database, given that this student does not already
	 * exist in the database.
	 * @param newUser : Staff, which is the new staff instance being added to the database.
	 * @return boolean, true if the staff member is successfully added to the database.
	 * 					false, if the staff member was not added to the database, or if the staff member already exists in
	 * 				the database.
	 */
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


	/**
	 * updateStudentData() is used to update the record of an existing student after modifications are made to it.
	 * It traverses through the database to find the position of the student in it, and overwrites the instance with the
	 * new instance of the same student.
	 *
	 * @param userID, the userID of the student to identify the record.
	 * @param updatedUser, the new student object, which will overwrite the old student object.
	 * @return true, if the student details were succesfully overwritte.
	 * 		false, if the student does not exist in the database or if the overwrite operation was unsuccessful.
	 */
	public boolean updateStudentData(String userID, Student updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject("/home/jjkoh/Desktop/y2s1/cz2002/OODP-Project/MySTARS/src/data/student.ser");
		for (int i = 0; i < temp.size(); i++) {
			Student u = (Student)temp.get(i);
			if (u.getUserID().equals(userID)) {
				temp.set(i, updatedUser);
				SerializeDB.writeSerializedObject("/home/jjkoh/Desktop/y2s1/cz2002/OODP-Project/MySTARS/src/data/student.ser", temp);
				return true;
			}
		}

		return false;
	}

	/**
	 * updateStaffData() is used to update the record of an existing student after modifications are made to it.
	 * It traverses through the database to find the position of the student in it, and overwrites the instance with the
	 * new instance of the same student.
	 *
	 * @param userID, the userID of the student to identify the record.
	 * @param updatedUser, the new student object, which will overwrite the old student object.
	 * @return true, if the student details were succesfully overwritte.
	 * 		false, if the student does not exist in the database or if the overwrite operation was unsuccessful.
	 */
	public boolean updateStaffData(String userID, User updatedUser) {

		temp = (ArrayList)SerializeDB.readSerializedObject(STAFF);
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

	/**
	 * getCourseData() traverses the course Database to find the instance of a particular course,
	 * which contains the corresponding indices, and lessons for the same.
	 *
	 * @param courseCode, which is the unique code of the course instance.
	 * @return the requested course data. If the corresonding course is not found, a null record is returned.
	 */
	public Course getCourseData(String courseCode) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(COURSE);

		for (int i = 0; i < temp.size(); i++) {
			Course c = (Course)temp.get(i);
			if (c.getCourseCode().equals(courseCode)) {
				return c;
			}
		}

		return null;
	}

	/**
	 * getCourseData() is used to retrieve all the courses in the database.
	 * @return all the courses present in the database.
	 */
	public ArrayList<Course> getAllCourseData() {
		return (ArrayList)SerializeDB.readSerializedObject(COURSE);
	}

	/**
	 * addCourseData() is used to add a new course object to the database, given that this course object does not already
	 * exist in the database.
	 * @param newCourse : Course, which is the new course instance being added to the database.
	 * @return boolean, true if the course is successfully added to the database.
	 * 					false, if the course was not added to the database, or if the course already exists in
	 * 				the database.
	 */
	public boolean addCourseData(Course newCourse) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(COURSE);

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

	/**
	 * updateCourseData() is used to update the record of an existing course after modifications are made to it.
	 * It traverses through the database to find the position of the course object in it, and overwrites the instance with the
	 * new instance of the same course.
	 *
	 * @param courseCode, which is the unique identifier of the course object.
	 * @param updatedCourse, the new course object, which will overwrite the old course object.
	 * @return true, if the course details were successfully overwritten.
	 * 		false, if the course does not exist in the database or if the overwrite operation was unsuccessful.
	 */
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

	/**
	 * getSchoolData() traverses the school Database to find the instance of a particular school,
	 * which contains the corresponding courses within the school.
	 *
	 * @param schoolID, which is the unique code of the school instance.
	 * @return the requested course data. If the corresponding school is not found, a null record is returned.
	 */
	public School getSchoolData(int schoolID) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);

		// Search and return if found
		for (int i = 0; i < temp.size(); i++) {
			School s = (School)temp.get(i);
			if (s.getSchoolID() == schoolID) {
				return s;
			}
		}
		return null;
	}

	/**
	 * addSchoolData() is used to add a new school object to the database, given that this school object does not already
	 * exist in the database.
	 * @param newSchool : school, which is the new course instance being added to the database.
	 * @return boolean, true if the course is successfully added to the database.
	 * 					false, if the course was not added to the database, or if the course already exists in
	 * 				the database.
	 */
	public boolean addSchoolData(School newSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);
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

	/**
	 * updateSchoolData() is used to update the record of an existing school after modifications are made to it.
	 * It traverses through the database to find the position of the school object in it, and overwrites the instance with the
	 * new instance of the same school.
	 *
	 * @param schoolID, which is the unique identifier of the school or department.
	 * @param updatedSchool : School, the new school object, which will overwrite the old school object.
	 * @return true, if the school details were successfully overwritten.
	 * 		false, if the school does not exist in the database or if the overwrite operation was unsuccessful.
	 */
	public boolean updateSchoolData(int schoolID, School updatedSchool) {
		
		temp = (ArrayList)SerializeDB.readSerializedObject(SCHOOL);
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


		

