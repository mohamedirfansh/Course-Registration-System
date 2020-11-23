package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class Staff extends Users{
	
	/**
	 * 2 new methods:
	 * 1) createCourse:
	 * @param courseName: name of new course
	 * @param courseCode: example CX2002
	 * @param schoolName: name of school providing this course
	 * @param au: number of academic units for the new course
	 * @return: returns the new course to the caller
	 *
	 * 2) addStudentRecord:
	 * @param (all): all required information to create a new student object.
	 * @return: returns the new student to the caller
	 */
	public Staff(String name, String userID, String userPW,
			String gender, String nationality, int schoolID,
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}

	public Course createCourse(String courseName, String courseCode, String schoolName, int au) {
		return new Course(courseName, courseCode, schoolName, au);
	}

	public Student addStudentRecord(String name, String userID, String userPW,
			String gender, String nationality, int schoolID,
			String identificationKey) throws NoSuchAlgorithmException {

		return new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
}
