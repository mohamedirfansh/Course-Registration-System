package data;
import entities.Student;
import entities.Hash;
import entities.Student;
import controls.SerializeDB;
import controls.Password;
import java.util.ArrayList;
import java.util.List;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

public class createStudentData {

	public static void createStudent() throws NoSuchAlgorithmException {
		ArrayList<Student> studentData = new ArrayList();

		// SCSE students
		Student student1 = new Student("Tom", "Tom1998", "Male", "Singaporean", 11, "U190123A");
		Password.addNewPassword("Tom1998", "TomPassword");

		student1.setAcademicUnits(6);
		HashMap<String,String> stud1Courses = new HashMap<String, String>();
		stud1Courses.put("CZ1007", "CZ1007SS1");
		stud1Courses.put("CZ2001", "CZ2001SS1");
		student1.setRegisteredCourses(stud1Courses);

		Student student2 = new Student("Dick", "Dick1998", "Male", "Singaporean", 11, "U234234D");
		Password.addNewPassword("Dick1998", "DickPassword");

		student2.setAcademicUnits(6);
		HashMap<String,String> stud2Courses = new HashMap<String, String>();
		stud2Courses.put("CZ1007", "CZ1007SS1");
		stud2Courses.put("CZ2001", "CZ2001SS1");	
		student2.setRegisteredCourses(stud2Courses);

		Student student3 = new Student("Harry", "Harry1998", "Male", "Singaporean", 11, "U234760H");
		Password.addNewPassword("Harry1998", "HarryPassword");

		student3.setAcademicUnits(6);
		HashMap<String,String> stud3Courses = new HashMap<String, String>();
		stud3Courses.put("CZ1007", "CZ1007SS2");
		stud3Courses.put("CZ2001", "CZ2001SS1");
		student3.setRegisteredCourses(stud3Courses);
		

		Student student4 = new Student("John", "John1997", "Male", "Malaysian", 11, "U084287J");
		Password.addNewPassword("John1997", "JohnPassword");
		
		student4.setAcademicUnits(6);
		HashMap<String,String> stud4Courses = new HashMap<String, String>();
		stud4Courses.put("CZ1007", "CZ1007SS2");
		stud4Courses.put("CZ2001", "CZ2001SS1");
		student4.setRegisteredCourses(stud4Courses);
		
		 
		Student student5 = new Student("Peter", "Peter1997", "Male", "Chinese", 11, "U092367P");
		Password.addNewPassword("Peter19977", "PeterPassword");

		student5.setAcademicUnits(6);
		HashMap<String,String> stud5Courses = new HashMap<String, String>();
		stud5Courses.put("CZ1007", "CZ1007SS2");
		stud5Courses.put("CZ2001", "CZ2001SS1");
		student5.setRegisteredCourses(stud5Courses);


		// NBS students
		Student student6 = new Student("Alexander", "Alexander1997", "Male", "Indian", 21, "U092134A");
		Password.addNewPassword("Alexander1997", "AlexanderPassword");
		
		student6.setAcademicUnits(6);
		HashMap<String,String> stud6Courses = new HashMap<String, String>();
		stud6Courses.put("BU8201", "BU8201BU1");
		stud6Courses.put("CZ2001", "CZ2001SS1");
		student6.setRegisteredCourses(stud6Courses);

		Student student7 = new Student("Jane", "Jane1999", "Female", "Malaysian", 21, "U093154J");
		Password.addNewPassword("Jane1999", "JanePassword");
		
		student7.setAcademicUnits(6);
		HashMap<String,String> stud7Courses = new HashMap<String, String>();
		stud7Courses.put("BU8201", "BU8201BU1");
		stud7Courses.put("CZ2001", "CZ2001SS1");
		student7.setRegisteredCourses(stud7Courses);

		Student student8 = new Student("Elizabeth", "Elizabeth1999", "Female", "Indonesian", 21, "U973243E");
		Password.addNewPassword("Elizabeth1999", "ElizabethPassword");
		
		student8.setAcademicUnits(6);
		HashMap<String,String> stud8Courses = new HashMap<String, String>();
		stud8Courses.put("BU8201", "BU8201BU2");
		stud8Courses.put("CZ2001", "CZ2001SS1");
		student8.setRegisteredCourses(stud8Courses);

		Student student9 = new Student("Catherine", "Catherine1999", "Female", "Vietnamnese", 21, "U998342C");
		Password.addNewPassword("Catherine1999", "CatherinePassword");
		
		student9.setAcademicUnits(6);
		HashMap<String,String> stud9Courses = new HashMap<String, String>();
		stud9Courses.put("BU8201", "BU8201BU2");
		stud9Courses.put("CZ2001", "CZ2001SS1");
		student9.setRegisteredCourses(stud9Courses);

		Student student10 = new Student("Adeline", "Adeline1999", "Female", "Malaysian", 21, "U198752A");
		Password.addNewPassword("Adeline1999", "AdelinePassword");

		student10.setAcademicUnits(6);
		HashMap<String,String> stud10Courses = new HashMap<String, String>();
		stud10Courses.put("BU8201", "BU8201BU2");
		stud10Courses.put("CZ2001", "CZ2001SS1");
		student10.setRegisteredCourses(stud10Courses);

		// sss students

		Student student11 = new Student("George", "George1999", "Male", "Indonesian", 31, "U912734G");
		Password.addNewPassword("George1999", "GeorgePassword");
		
		student11.setAcademicUnits(3);
		HashMap<String,String> stud11Courses = new HashMap<String, String>();
		stud11Courses.put("HP1000", "HP1000HP1");
		student11.setRegisteredCourses(stud11Courses);

		Student student12 = new Student("Paul", "Paul1999",  "Male", "Singaporean", 31, "U193274P");
		Password.addNewPassword("Paul1999", "PaulPassword");
		
		student12.setAcademicUnits(3);
		HashMap<String,String> stud12Courses = new HashMap<String, String>();
		stud12Courses.put("HP1000", "HP1000HP1");
		student12.setRegisteredCourses(stud12Courses);

		Student student13 = new Student("Jack", "Jack1998", "Male", "Malaysian", 31, "U298733J");
		Password.addNewPassword("Jack1998", "JackPassword");
		
		student13.setAcademicUnits(3);
		HashMap<String,String> stud13Courses = new HashMap<String, String>();
		stud13Courses.put("HP1000", "HP1000HP2");
		student13.setRegisteredCourses(stud13Courses);

		Student student14 = new Student("Jacqueline", "Jacqueline1999", "Female", "Singaporean", 31, "U129248J");
		Password.addNewPassword("Jacqueline1999", "JacquelinePassword");
		
		student14.setAcademicUnits(3);
		HashMap<String,String> stud14Courses = new HashMap<String, String>();
		stud14Courses.put("HP1000", "HP1000HP2");
		student14.setRegisteredCourses(stud14Courses);

		
		Student student15 = new Student("Kenny", "Kenny1999",  "Male", "Singaporean", 31, "U193844K");
		Password.addNewPassword("Kenny1999", "KennyPassword");

		student15.setAcademicUnits(3);
		HashMap<String,String> stud15Courses = new HashMap<String, String>();
		stud15Courses.put("HP1000", "HP1000HP2");
		student15.setRegisteredCourses(stud15Courses);

		

		studentData.add(student1);
		studentData.add(student2);
		studentData.add(student3);
		studentData.add(student4);
		studentData.add(student5);
		studentData.add(student6);
		studentData.add(student7);
		studentData.add(student8);
		studentData.add(student9);
		studentData.add(student10);
		studentData.add(student11);
		studentData.add(student12);
		studentData.add(student13);
		studentData.add(student14);
		studentData.add(student15);


		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("student.dat", studentData);
			sdb.writeSerializedObject("studentPassword.dat", Password.getHashMap());
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}

	public static void main(String[] args) throws NoSuchAlgorithmException {
		createStudentData create = new createStudentData();
		create.createStudent();
	}
}

