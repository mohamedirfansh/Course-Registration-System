package controls;
import entities.Student;
import entities.Staff;
import entities.Users;
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

			// Testing retrieval of particular Student object
			String testStudentID = "Adeline1999";
			Student testStudent = db.getStudentData(testStudentID);
			System.out.println();

			// Testing retrieval of particular Staff object
			String testStaffID = "Miao123";
			Staff testStaff = db.getStaffData(testStaffID);
			System.out.println();

			// Testing addition of new Student object
			Student newStudent = new Student("John", "John1997", Hash.encode("JohnPassword"), "Male", "Malaysian", "SCSE", "U084287J");
			db.addStudentData(newStudent);
			System.out.println();

			// Testing addition of new Staff object
			Staff newStaff = new Staff("Miao Chunyan", "Miao123", Hash.encode("MiaoPassword"), "Female", "Chinese", "SCSE", "S239723M");
			db.addStaffData(newStaff);
			System.out.println();
			
			// Testing update of Student object
			Student updateStudent = new Student("John", "John1997", Hash.encode("JohnPassword"), "Male", "Malaysian", "SCSE", "U084287J");
			db.updateStudentData(updateStudent.getUserID(), updateStudent);
			System.out.println();
			
			// Testing update of Staff Object
			Staff updateStaff = new Staff("Miao Chunyan", "Miao123", Hash.encode("MiaoPassword"), "Female", "Chinese", "SCSE", "S239723M");
			db.updateStaffData(updateStaff.getUserID(), updateStaff);
			System.out.println();
			
			// Testing retrieval of particular Course object
			String testCode = "CZ1007";
			Course testCourse = db.getCourseData(testCode);
			System.out.println();
			
			// Testing addition of new Course Object
			Course newCourse = new Course("Data Structures", "CZ1007", "School of Computer Science and Engineering", 3);
			db.addCourseData(newCourse);
			System.out.println();
			
			// Testing update of Course Object
			Course updateCourse = new Course("Business Finance", "BU8201", "Nanyang Business School", 3);
			db.updateCourseData(updateCourse.getCourseCode(), updateCourse);
			System.out.println();

			// Testing retrieval of particular School Object
			int testID = 11;
			School testSchool = db.getSchoolData(testID);
			System.out.println();
			
			// Testing addition of new School Object
			School newSchool = new School("School of Social Sciences", "SSS");
			newSchool.setSchoolID(31);
			db.addSchoolData(newSchool);
			System.out.println();
			
			// Testing update of School Object
			School updateSchool = new School("School of Social Sciences", "SSS");
			updateSchool.setSchoolID(31);
			db.updateSchoolData(updateSchool.getSchoolID(), updateSchool);
			System.out.println();

			/* // Testing of StaffControl
			DatabaseControl dbStudent = new DatabaseControl();

			String testStudentID = "Tom1998";
			Student testStudent = dbStudent.getStudentData(testStudentID);

			StudentControl sc = new StudentControl(testStudent);
			testStudent.addCourse("CZ2001" */



			
		}
		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}


		

