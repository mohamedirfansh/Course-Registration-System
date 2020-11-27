package entities;

import java.util.HashMap;


/**
 * Student is a specific implementation of a user. It extends from the user class and makes use of its general methods
 * and attributes, as well as adds on to it with its own specialized attributes and methods that are unique to a student.
 *
 * Class Attributes:
 * -> registeredCourses : HashMap<String, String>, which is a list of courseID's (key), and the corresponding index
 * 				in the course (value) to which the student is enrolled into.
 * -> waitListedCourses : HashMap<String, String>, which is a list of courseID's (key), and the corresponding index
 *  				in the course (value) to which the student is waitlisted into.
 *
 * -> academicUnits : int, which is the sum total of all the credits for the courses the student is currently registered in.
 */
public class Student extends User{
	private HashMap<String, String> registeredCourses = new HashMap<>(20);
	private HashMap<String, String> waitListedCourses = new HashMap<>(20);
	private int academicUnits = 0;


	/**
	 * Student constructor to initialize the student object. It takes in the below specified parameters and calls the base
	 * class constructor.
	 *
	 * @param name, the name of the student
	 * @param userID, the unique userID of the student
	 * @param gender, the gender of the student.
	 * @param nationality, the nationality of the student
	 * @param schoolID, the department in which the student resides.
	 * @param identificationKey, the unique identifier that every student is given.
	 */
	public Student(String name, String userID,
			String gender, String nationality, int schoolID,
			String identificationKey){

		super(name, userID, gender, nationality, schoolID, identificationKey);
	}


	/**
	 * Setters and getters for the unique student attrubutes.
	 */
	public void setRegisteredCourses(HashMap<String, String> coursesDetails) { this.registeredCourses = coursesDetails; }
	public HashMap<String, String> getRegisteredCourses() { return this.registeredCourses; }

	public void setWaitListedCourses(HashMap<String, String>  waitListedCourses){ this.waitListedCourses = waitListedCourses; }
	public HashMap<String, String> getWaitListedCourses(){ return this.waitListedCourses; }

	public void setAcademicUnits(int numberOfAU) { this.academicUnits = numberOfAU; }
	public int getNumberOfAUs() { return this.academicUnits; }
}
