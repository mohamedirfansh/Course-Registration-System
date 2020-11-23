package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class Student extends User{
	/*
	 * Attribute: HashMap<String, String> registeredCourses
	 * -> Key: CourseID, Value: index of added course, which determines the timetable slot
	 *
	 * Attribute: academicUnits
	 * the integer value which represents the current academic load of the student
	 */

	private HashMap<String, String> registeredCourses = new HashMap<>();
	private int academicUnits = 0;
	public static final long serialVersionUID = 2L;

	public Student(String name, String userID,
			String gender, String nationality, int schoolID,
			String identificationKey) throws NoSuchAlgorithmException {

		super(name, userID, gender, nationality, schoolID, identificationKey);
	}

	/*
	 * updateRegisteredCourses:
	 * @param coursesDetails: updated HashMap containing all the details of courses of the student, whether
	 *  					  is dropping or adding of new courses for the student
	 *
	 * retrieveRegisteredCourses:
	 * @return: all the registered courses of the student
	 *
	 * updateAcademicUnits:
	 * @param: new value of academic units after changes had been made
	 *
	 * retrieveNumberOfAUs:
	 * @return: integer value of the total number of academic units the student had registered for
	 */

	public void setRegisteredCourses(HashMap<String, String> coursesDetails) { this.registeredCourses = coursesDetails; }
	public HashMap<String, String> getRegisteredCourses() { return this.registeredCourses; }

	public void setAcademicUnits(int numberOfAU) { this.academicUnits = numberOfAU; }
	public int getNumberOfAUs() { return this.academicUnits; }
}
