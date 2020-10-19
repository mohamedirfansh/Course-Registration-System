package entities;

public class School {
	/* Create some attributes unique to School class.
	It will also contain a list of Students, list of Staff, list of Courses
	since these information are unique to each school.
	*/

	private String schoolName;		// The fullname of the school
	private String schoolInitials;	// Initials of the school like SCSE for "School of Computer Science and Engineering"
	private int schoolID;			// A unique ID for each school
	private Student[] allStudents;	// List of all Students part of the particular school
	private Staff[] allStaff;		// List of all Staff part of the particular school
	private Course[] allCourses;	// List of courses that are available for this school

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

	public Student[] getAllStudents(){
		return allStudents;
	}

	public Staff[] getAllStaff(){
		return allStaff;
	}

	public Course[] getAllCourses(){
		return allCourses;
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

	public void setAllStudents(Student[] allStudents){
		this.allStudents = allStudents;
	}

	public void setAllStaff(Staff[] allStaff){
		this.allStaff = allStaff;
	}

	public void setAllCourses(Course[] allCourses){
		this.allCourses = allCourses;
	}

}
