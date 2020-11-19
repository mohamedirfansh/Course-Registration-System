package boundaries;
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

			DatabaseControl dbcu = new DatabaseControl('u');
			DatabaseControl dbcs = new DatabaseControl('s');

			// Testing retrieval of particular Student object
			String testStudentID = "Adeline1999";
			Users testStudent = dbcu.getUserData(testStudentID);
			System.out.println();

			// Testing retrieval of particular Staff object
			String testStaffID = "Miao123";
			Users testStaff = dbcs.getUserData(testStaffID);
			System.out.println();

			// Testing addition of new Student object
			Users newStudent = new Student("John", "John1997", Hash.encode("JohnPassword"), "Male", "Malaysian", "SCSE", "U084287J");
			dbcu.addUserData(newStudent);
			System.out.println();

			// Testing addition of new Staff object
			Users newStaff = new Staff("Miao Chunyan", "Miao123", Hash.encode("MiaoPassword"), "Female", "Chinese", "SCSE", "S239723M");
			dbcs.addUserData(newStaff);
			System.out.println();
			
			// Testing update of Student object
			Users updateStudent = new Student("John", "John1997", Hash.encode("JohnPassword"), "Male", "Malaysian", "SCSE", "U084287J");
			dbcu.updateUserData(updateStudent.getUserID(), updateStudent);
			System.out.println();
			
			// Testing update of Staff Object
			Users updateStaff = new Staff("Miao Chunyan", "Miao123", Hash.encode("MiaoPassword"), "Female", "Chinese", "SCSE", "S239723M");
			dbcs.updateUserData(updateStaff.getUserID(), updateStaff);
			System.out.println();
			
			// Testing retrieval of particular Course object
			String testCode = "CZ1007";
			Course testCourse = dbcs.getCourseData(testCode);
			System.out.println();
			
			// Testing addition of new Course Object
			Course newCourse = new Course("Data Structures", "CZ1007", "School of Computer Science and Engineering", 3);
			dbcs.addCourseData(newCourse);
			System.out.println();
			
			// Testing update of Course Object
			Course updateCourse = new Course("Business Finance", "BU8201", "Nanyang Business School", 3);
			dbcs.updateCourseData(updateCourse.getCourseCode(), updateCourse);
			System.out.println();

			// Testing retrieval of particular School Object
			String testInitials = "SCSE";
			School testSchool = dbcs.getSchoolData(testInitials);
			System.out.println();
			
			// Testing addition of new School Object
			School newSchool = new School("School of Social Sciences", "SSS");
			dbcs.addSchoolData(newSchool);
			System.out.println();
			
			// Testing update of School Object
			School updateSchool = new School("School of Social Sciences", "SSS");
			dbcs.updateSchoolData(updateSchool.getSchoolInitials(), updateSchool);
			System.out.println();
		}
		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}


		

