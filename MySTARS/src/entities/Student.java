package entities;

import java.io.BufferedReader;
import java.io.EOFException;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;

public class Student extends User implements Serializable{
	private ArrayList<String> registeredCourses = new ArrayList<>();
	private int academicUnits = 0;
	
	public Student(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
	
	public void updateRegisteredCourses(ArrayList<String> coursesDetails) { this.registeredCourses = coursesDetails; }
	public ArrayList<String> retrieveRegisteredCourses() { return this.registeredCourses; }
	
	public void updateAcademicUnits(int numberOfAU) { this.academicUnits = numberOfAU; }
	public int retrieveNumberOfAUs() { return this.academicUnits; }
}
