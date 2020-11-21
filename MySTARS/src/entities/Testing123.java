package entities;

import java.io.ByteArrayOutputStream;
import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectOutputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.ArrayList;

@SuppressWarnings("serial")
public class Testing123 implements Serializable {
	public static void writeToStudFile (ArrayList<Object> temp) {
		String currentDir = "C:\\Users\\Jin Hwee\\Desktop\\tryout.dat";
		try {
			FileOutputStream fileout = new FileOutputStream(currentDir);
			ObjectOutputStream objectout = new ObjectOutputStream(fileout);
			for (Object o: temp)
				objectout.writeObject(o);
			objectout.close();
			System.out.println("Successfully written");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void writeToStaffFile (ArrayList<Object> temp) {
		String currentDir = "C:\\Users\\Jin Hwee\\Desktop\\tryout2.dat";
		try {
			FileOutputStream fileout = new FileOutputStream(currentDir);
			ObjectOutputStream objectout = new ObjectOutputStream(fileout);
			for (Object o: temp)
				objectout.writeObject(o);
			objectout.close();
			System.out.println("Successfully written");
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
	
	public static void main(String[] args) throws NoSuchAlgorithmException, IOException {		
		Object student1 = new Student("RandomDude1", "RandomDood", "Password!", "Male", "Singaporean"
				, "SCSE", "U1234");
		Object student2 = new Student("RandomGuy1", "RandomGuy", "Password!", "Male", "Singaporean"
				, "SCSE", "U2345");
		Object student3 = new Student("RandomGurl1", "RandomGurl", "Password!", "Female", "Chinese"
				, "SCSE", "U3456");
		ArrayList<Object> temp = new ArrayList<>();
		temp.add(student1);
		temp.add(student2);
		temp.add(student3);
		
		writeToStudFile(temp);
		
		Student.populateHashmap("C:\\Users\\Jin Hwee\\Desktop\\tryout.dat");
		System.out.println("Done");
		System.out.println("Trying out to add AU and courses to 2 students");
		Course HCI = new Course("Human Computer Interaction", "CX2004", "SCSE", 3);
		Course OODP = new Course("Object-Oriented Programming", "CX2002", "SCSE", 3);
		
		Student.addCourseToParticularStud("RandomDood", HCI);
		Student.addCourseToParticularStud("RandomGuy", OODP);
		Student.addCourseToParticularStud("RandomDood", OODP);
		
		HashMap<String, Student> tester = Student.getStudentList();
		for (String key: tester.keySet()) {
			String userID = tester.get(key).getUserID();
			System.out.println("User ID: " + userID + "\tNumber of AU: " + Student.getNumAUforParticularStudent(userID) + 
					"\tRegistered Courses: " + Student.getRegisteredCoursesForParticularStud(userID).toString());
		}
		System.out.println();
		Student.removeCourseForParticularStud("RandomDood", HCI);
		tester = Student.getStudentList();
		for (String key: tester.keySet()) {
			String userID = tester.get(key).getUserID();
			System.out.println("User ID: " + userID + "\tNumber of AU: " + Student.getNumAUforParticularStudent(userID) + 
					"\tRegistered Courses: " + Student.getRegisteredCoursesForParticularStud(userID).toString());
		}
		
		
		
		Object staff1 = new Staff("Staff", "Staff1", "Password!", "Male", "Singaporean"
				, "SCSE", "U1234");
		Object staff2 = new Staff("Staffy", "Staff2", "Password!", "Male", "Singaporean"
				, "SCSE", "U2345");
		Object staff3 = new Staff("DeadStaff", "Staff3", "Password!", "Female", "Chinese"
				, "SCSE", "U3456");
		ArrayList<Object> stafflist = new ArrayList<>();
		stafflist.add(staff1);
		stafflist.add(staff2);
		stafflist.add(staff3);
		
		writeToStaffFile(stafflist);
		
		Staff tryingOut = (Staff) staff1;
		Student newRecord = tryingOut.addStudentRecord("Jin Hwee", "Jinny", "Jinny123", "Male", "Singaporean", "SCSE", "U1999999");
		if (newRecord instanceof Student) {
			System.out.println("Created a new student");
		}
		
	}
}
