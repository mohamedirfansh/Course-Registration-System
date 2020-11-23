package controls;
import entities.Student;
import entities.Staff;
import entities.User;
import entities.Course;
import entities.School;
import entities.Hash;
import java.util.ArrayList;
import java.security.NoSuchAlgorithmException;
import java.util.List;

public class DatabaseControlTest {

	// for testing
	public static void main(String[] args) throws NoSuchAlgorithmException {
		try {

			DatabaseControl db = new DatabaseControl();
			boolean success;

			// Testing retrieval of particular Student object
			String testStudentID = "Adeline1999";
			Student testStudent = db.getStudentData(testStudentID);
			System.out.println();

			// Testing retrieval of particular Staff object
			String testStaffID = "Miao123";
			Staff testStaff = db.getStaffData(testStaffID);
			System.out.println();

			// Testing addition of new Student object
			Student newStudent = new Student("John", "John1997", "Male", "Malaysian", 11, "U084287J");
			success = db.addStudentData(newStudent);
			if (success == true) {
				System.out.println("Added new Student with name " + newStudent.getName());
			}
			else {
				System.out.println("Student with userID " + newStudent.getUserID() + " already inside database!");
			}
			System.out.println();

			// Testing addition of new Staff object
			Staff newStaff = new Staff("Miao Chunyan", "Miao123", "Female", "Chinese", 11, "S239723M");
			success = db.addStaffData(newStaff);
			if (success == true) {
				System.out.println("Added new Staff with name " + newStaff.getName());
			}
			else {
				System.out.println("Staff with userID " + newStaff.getUserID() + " already inside database!");
			}
			System.out.println();
			
			// Testing update of Student object
			Student updateStudent = new Student("John", "John1997", "Male", "Malaysian", 11, "U084287J");
			success = db.updateStudentData(updateStudent.getUserID(), updateStudent);
			if (success == true) {
				System.out.println("Student with userID " + updateStudent.getUserID() + " updated");
			}
			else {
				System.out.println("Student with userID " + updateStudent.getUserID() + " not found!");
			}

			System.out.println();
			
			// Testing update of Staff Object
			Staff updateStaff = new Staff("Miao Chunyan", "Miao123", "Female", "Chinese", 11, "S239723M");
			success = db.updateStaffData(updateStaff.getUserID(), updateStaff);
			if (success == true) {
				System.out.println("Staff with userID " + updateStaff.getUserID() + " updated");
			}
			else {
				System.out.println("Staff with userID " + updateStaff.getUserID() + " not found!");
			}
			System.out.println();
			
			// Testing retrieval of particular Course object
			String testCode = "CZ1007";
			Course testCourse = db.getCourseData(testCode);
			System.out.println();
			
			// Testing addition of new Course Object
			Course newCourse = new Course("Data Structures", "CZ1007", "School of Computer Science and Engineering", 3);
			success = db.addCourseData(newCourse);
			if (success == true) {
				System.out.println("Added new course with code " + newCourse.getCourseCode());
			}
			else {
				System.out.println("Course " + newCourse.getCourseCode() + " already inside database");
			}

			System.out.println();
			
			// Testing update of Course Object
			Course updateCourse = new Course("Business Finance", "BU8201", "Nanyang Business School", 3);
			success = db.updateCourseData(updateCourse.getCourseCode(), updateCourse);
			if (success == true) {
				System.out.println("Updated course " + updateCourse.getCourseCode());
			}
			else {
				System.out.println("course " + updateCourse.getCourseCode() + " not found");
			}

			System.out.println();

			// Testing retrieval of particular School Object
			int testID = 11;
			School testSchool = db.getSchoolData(testID);
			System.out.println();
			
			// Testing addition of new School Object
			School newSchool = new School("School of Social Sciences", "SSS");
			newSchool.setSchoolID(31);
			success = db.addSchoolData(newSchool);
			if (success == true) {
				System.out.println("Added new School with ID " + newSchool.getSchoolID());
			}
			else {
				System.out.println("School with ID " + newSchool.getSchoolID() + " already inside database");
			}

			System.out.println();
			
			// Testing update of School Object
			School updateSchool = new School("School of Social Sciences", "SSS");
			updateSchool.setSchoolID(31);
			success = db.updateSchoolData(updateSchool.getSchoolID(), updateSchool);
			if (success == true) {
				System.out.println("Updated School with ID " + updateSchool.getSchoolID());
			}
			else {
				System.out.println("school with ID " + updateSchool.getSchoolID() + " not found");
			}
			System.out.println();
		}
		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}


		

