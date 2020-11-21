package entities;

import java.util.ArrayList;

public class School {
	/* Create some attributes unique to School class.
	It will also contain a list of Students, list of Staff, list of Courses
	since these information are unique to each school.
	*/

	private String schoolName;		// The fullname of the school
	private String schoolInitials;	// Initials of the school like SCSE for "School of Computer Science and Engineering"
	private int schoolID;			// A unique ID for each school
	private ArrayList<Student> allStudents;	// List of all Students part of the particular school
	private ArrayList<Staff> allStaff;		// List of all Staff part of the particular school
	private ArrayList<Course> allCourses;	// List of courses that are available for this school
	private AccessPeriod accessPeriod; // Stores an AccessPeriod object which allows us to read, write & validate a School's access period.

	// School constructor to create school object with the school name and initals
	public School(String schoolName, String schoolInitials){
		this.schoolName = schoolName;
		this.schoolInitials = schoolInitials;
	}

	// All getter methods
	public String getSchoolInitials(){
		return schoolInitials;
	}

	public String getSchoolName(){
		return schoolName;
	}

	public int getSchoolID(){
		return schoolID;
	}

	public ArrayList<Student> getAllStudents(){
		return allStudents;
	}

	public ArrayList<Staff> getAllStaff(){
		return allStaff;
	}

	public ArrayList<Course> getAllCourses(){
		return allCourses;
	}

	public void getAccessPeriod() { // Should we print here? or at the boundary class and return just start and end dates.
		String startDate = this.accessPeriod.getStartDate();
		String endDate = this.accessPeriod.getEndDate();
		System.out.println(this.schoolName + "'s access period starts on " + startDate + " and ends on " + endDate);
	}

	// All setter methods
	public void setSchoolInitials(String schoolInitials){
		this.schoolInitials = schoolInitials;
	}

	public void setSchoolName(String schoolName){
		this.schoolName = schoolName;
	}

	public void setSchoolID(int schoolID){
		this.schoolID = schoolID;
	}

	public void setAllStudents(ArrayList<Student> allStudents){
		this.allStudents = allStudents;
	}

	public void setAllStaff(ArrayList<Staff> allStaff){
		this.allStaff = allStaff;
	}

	public void setAllCourses(ArrayList<Course> allCourses){
		this.allCourses = allCourses;
	}

	public boolean setAccessPeriod(String startDate, String endDate) {
		AccessPeriod updatedAccessPeriod = new AccessPeriod(startDate, endDate);
		boolean isValid = updatedAccessPeriod.isValidPeriod();
		if (isValid) {
			this.accessPeriod = updatedAccessPeriod;
		} 
		return isValid;
	}

}