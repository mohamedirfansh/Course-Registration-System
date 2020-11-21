package entities;

import java.util.ArrayList;
import java.io.Serializable;

/**
 * School class unique attributes for a particular school.
 * It contains a list of Students, list of Staff, list of Courses
 * since these information are unique to each school.
 *
 */
public class School implements Serializable {
	/**
	 * The full name of the school
	 */
	private String schoolName;
	/**
	 * Initials of the school like SCSE for "School of Computer Science and Engineering"
	 */
	private String schoolInitials;
	/**
	 * A unique ID for each school
	 */
	private int schoolID;
	/**
	 * List of all Students part of the particular school
	 */
	private ArrayList<Student> allStudents;
	/**
	 * List of all Staff part of the particular school
	 */
	private ArrayList<Staff> allStaff;
	/**
	 * List of courses that are available for this school
	 */
	private ArrayList<Course> allCourses;
	public static final long serialVersionUID = 2L;

	/**
	 * Stores an AccessPeriod object which allows us to read, write & validate a School's access period.
	 */
	private AccessPeriod accessPeriod; 
	
	
	/**
	 * School constructor to create school object with the school name and initals
	 * @param schoolName
	 * @param schoolInitials
	 */
	public School(String schoolName, String schoolInitials){
		this.schoolName = schoolName;
		this.schoolInitials = schoolInitials;
	}

	// All getter methods
	
	/**
	 * Getter method to return the school's initials
	 * @return schoolInitials
	 */
	public String getSchoolInitials(){
		return schoolInitials;
	}

	/**
	 * Getter method to return the school's name
	 * @return schoolName
	 */
	public String getSchoolName(){
		return schoolName;
	}
	
	/**
	 * Getter method to return the school's ID
	 * @return schoolID
	 */
	public int getSchoolID(){
		return schoolID;
	}

	/**
	 * Getter method to return the school's list of students
	 * @return allStudents
	 */
	public ArrayList<Student> getAllStudents(){
		return allStudents;
	}
	
	/**
	 * Getter method to return the school's list of staff
	 * @return allStaff
	 */
	public ArrayList<Staff> getAllStaff(){
		return allStaff;
	}

	/**
	 * Getter method to return the school's list of courses offered
	 * @return allCourses
	 */
	public ArrayList<Course> getAllCourses(){
		return allCourses;
	}
	
	/**
	 * Receives the access period for the school
	 */
	public void getAccessPeriod() { // Should we print here? or at the boundary class and return just start and end dates.
		String startDate = this.accessPeriod.getStartDate();
		String endDate = this.accessPeriod.getEndDate();
		System.out.println(this.schoolName + "'s access period starts on " + startDate + " and ends on " + endDate);
	}

	// All setter methods
	
	/**
	 * Setter method to set school initials
	 * @param schoolInitials
	 */
	public void setSchoolInitials(String schoolInitials){
		this.schoolInitials = schoolInitials;
	}

	/**
	 * Setter method to set school name
	 * @param schoolName
	 */
	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}

	/**
	 * Setter method to set school ID
	 * @param schoolID
	 */
	public void setSchoolID(int schoolID){
		this.schoolID = schoolID;
	}

	/**
	 * Setter method to set school's students
	 * @param allStudents
	 */
	public void setAllStudents(ArrayList<Student> allStudents){
		this.allStudents = allStudents;
	}

	/**
	 * Setter method to set school's staffs
	 * @param allStaff
	 */
	public void setAllStaff(ArrayList<Staff> allStaff){
		this.allStaff = allStaff;
	}

	/**
	 * Setter method to set school's courses offered
	 * @param allCourses
	 */
	public void setAllCourses(ArrayList<Course> allCourses){
		this.allCourses = allCourses;
	}
	
	/**
	 * Tries to set the access period for the school and returns 
	 * a boolean depending on its success.
	 * @param startDate
	 * @param endDate
	 * @return isValid
	 */
	public boolean setAccessPeriod(String startDate, String endDate) {
		AccessPeriod updatedAccessPeriod = new AccessPeriod(startDate, endDate);
		boolean isValid = updatedAccessPeriod.isValidPeriod();
		if (isValid) {
			this.accessPeriod = updatedAccessPeriod;
		} 
		return isValid;
	}

}