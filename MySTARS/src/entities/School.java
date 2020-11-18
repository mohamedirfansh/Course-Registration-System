package entities;

/**
 * School class unique attributes for a particular school.
 * It contains a list of Students, list of Staff, list of Courses
 * since these information are unique to each school.
 *
 */
public class School {
	/**
	 * The fullname of the school
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
	private Student[] allStudents;
	/**
	 * List of all Staff part of the particular school
	 */
	private Staff[] allStaff;
	/**
	 * List of courses that are available for this school
	 */
	private Course[] allCourses;

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
	 * @return schoolInitials: String
	 */
	public String getSchoolInitials(){
		return schoolInitials;
	}

	/**
	 * Getter method to return the school's name
	 * @return schoolName: String
	 */
	public String getSchoolName(){
		return schoolName;
	}
	
	/**
	 * Getter method to return the school's ID
	 * @return schoolID: int
	 */
	public int getSchoolID(){
		return schoolID;
	}

	/**
	 * Getter method to return the school's list of students
	 * @return allStudents: Student[]
	 */
	public Student[] getAllStudents(){
		return allStudents;
	}
	
	/**
	 * Getter method to return the school's list of staff
	 * @return allStaff: Staff[]
	 */
	public Staff[] getAllStaff(){
		return allStaff;
	}

	/**
	 * Getter method to return the school's list of courses offered
	 * @return allCourses: Course[]
	 */
	public Course[] getAllCourses(){
		return allCourses;
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
	public void setAllStudents(Student[] allStudents){
		this.allStudents = allStudents;
	}

	/**
	 * Setter method to set school's staffs
	 * @param allStaff
	 */
	public void setAllStaff(Staff[] allStaff){
		this.allStaff = allStaff;
	}

	/**
	 * Setter method to set school's courses offered
	 * @param allCourses
	 */
	public void setAllCourses(Course[] allCourses){
		this.allCourses = allCourses;
	}

}
