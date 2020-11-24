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
	//private ArrayList<Student> allStudents;
	  private ArrayList<String> allStudents;
	/**
	 * List of all Staff part of the particular school
	 */
	//private ArrayList<Staff> allStaff;
	private ArrayList<String> allStaff;
	/**
	 * List of courses that are available for this school
	 */
	//private ArrayList<Course> allCourses;
	private ArrayList<String> allCourses;
	

	/**
	 * Stores an AccessPeriod object which allows us to read, write & validate a School's access period.
	 */
	private AccessPeriod accessPeriod; 

	/**
	 * Needed to verify that serialization and de-serialization is done correctly
	 */
	private static final long serialVersionUID = 2L;
	
	
	/**
	 * School constructor to create school object with the school name and initals
	 * @param schoolName, which is the name of the school
	 * @param schoolInitials, which is the abbreviation of the school
	 */
	public School(String schoolName, String schoolInitials){
		this.schoolName = schoolName;
		this.schoolInitials = schoolInitials;
	}
	
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
	public ArrayList<String> getAllStudents(){
		return allStudents;
	}
	
	/**
	 * Getter method to return the school's list of staff
	 * @return allStaff
	 */
	public ArrayList<String> getAllStaff(){
		return allStaff;
	}

	/**
	 * Getter method to return the school's list of courses offered
	 * @return allCourses
	 */
	public ArrayList<String> getAllCourses(){
		return allCourses;
	}

	/**
	 * Receives the access period for the school
	 */
	public AccessPeriod getAccessPeriod() {
		String startDate = this.accessPeriod.getStartDate();
		String endDate = this.accessPeriod.getEndDate();
		return accessPeriod;
	}
	
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
	public void setAllStudents(ArrayList<String> allStudents){
		this.allStudents = allStudents;
	}

	/**
	 * Setter method to set school's staffs
	 * @param allStaff
	 */
	public void setAllStaff(ArrayList<String> allStaff){
		this.allStaff = allStaff;
	}

	/**
	 * Setter method to set school's courses offered
	 * @param allCourses
	 */
	public void setAllCourses(ArrayList<String> allCourses){
		this.allCourses = allCourses;
	}
	
	/**
	 * Tries to set the access period for the school and returns 
	 * a boolean depending on its success.
	 * @param startDate, which is the start date for access
	 * @param endDate, which is the end ddate for access
	 * @return isValid, whether the access period is valid or not. False if the access period is invalid,
	 * and true otherwise.
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
