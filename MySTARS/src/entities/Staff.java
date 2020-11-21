package entities;

import java.io.EOFException;
import java.io.File;
import java.io.FileInputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Staff extends User implements Serializable{
	// don't have to instantiate to call this function, can map all stored Staff objects like
	// staffId -> Staff object
	
	public Staff(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
		
	public Course createCourse(String courseName, String courseCode, String schoolName, int au) {
		return new Course(courseName, courseCode, schoolName, au);
	}
	
	public Student addStudentRecord(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		
		return new Student(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
}