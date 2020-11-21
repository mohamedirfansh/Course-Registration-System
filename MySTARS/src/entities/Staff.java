package entities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Staff extends Users implements Serializable{
	private static HashMap<String, Staff> listOfStaff = new HashMap<>();
	
	// don't have to instantiate to call this function, can map all stored Staff objects like
	// staffId -> Staff object
	
	public static void populateHashmap(String currentDir) throws IOException {
		FileInputStream inFile = null;
		ObjectInputStream input = null;
		Staff tempStaff;
		
		try {
			inFile = new FileInputStream(currentDir);
			input = new ObjectInputStream(inFile);
			while (true) {
				tempStaff = (Staff) input.readObject();
				System.out.println(tempStaff.getUserID() + ":    " + tempStaff.getName());
				if (!listOfStaff.containsKey(tempStaff.getUserID())) {
					listOfStaff.put(tempStaff.getUserID(), tempStaff);
				}
			}
		} catch (EOFException E) {
			System.out.println("Finish reading file...");
		} catch (Exception E){
			E.printStackTrace();
		} finally {
			inFile.close();
			input.close();
		}
	}
	
	
	public Staff(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
		if (!listOfStaff.containsKey(userID))
			listOfStaff.put(userID, this);
		else
			System.out.println("Error - Staff already exists in system!");
	}
	
	public static HashMap<String, Staff> getStaffList() { return listOfStaff; }
	
	public Course createCourse(String courseName, String courseCode, String schoolName, int au) {
		return new Course(courseName, courseCode, schoolName, au);
	}
	
	public Student addStudentRecord(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		HashMap<String, Student> checking = Student.getStudentList();
		if (checking.containsKey(userID)) {
			System.out.println("UserID is not unique! There is an existing record with the same ID!");
			return null;
		}
		return new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
}