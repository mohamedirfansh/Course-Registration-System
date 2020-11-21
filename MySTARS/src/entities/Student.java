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

public class Student extends Users implements Serializable{
	private static HashMap<String, Student> listOfStudents = new HashMap<>();
	private ArrayList<Course> registeredCourses = new ArrayList<>();
	private int academicUnits = 0;
	public static final long serialVersionUID = 2L;
	
	public static void populateHashmap(String currentDir) throws IOException {
		FileInputStream inFile = null;
		ObjectInputStream input = null;
		Student tempStudent;
		
		try {
			inFile = new FileInputStream(currentDir);
			input = new ObjectInputStream(inFile);
			while (true) {
				tempStudent = (Student) input.readObject();
				//System.out.println(tempStudent.getUserID() + ":    " + tempStudent.getName());
				if (!listOfStudents.containsKey(tempStudent.getUserID())) {
					listOfStudents.put(tempStudent.getUserID(), tempStudent);
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

	public Student(String name, String userID, String userPW,
			String gender, String nationality, String schoolID,
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);

		// I need a method to write new student/staff to the binary file
		if (!listOfStudents.containsKey(userID)) {
			listOfStudents.put(userID, this);
		} else
			System.out.println("Error - Student already exists in system!");
	}
	
	public static int getNumAUforParticularStudent(String userID) { return listOfStudents.get(userID).academicUnits; }
	public static ArrayList<Course> getRegisteredCoursesForParticularStud(String userID) { 
		return listOfStudents.get(userID).registeredCourses; 
	}
	
	public static HashMap<String, Student> getStudentList() { return listOfStudents; }
	private static void addAU(String userID, int AU) { listOfStudents.get(userID).academicUnits += AU;}
	public static void addCourseToParticularStud(String userID, Course course) { 
		listOfStudents.get(userID).registeredCourses.add(course); 

		addAU(userID, course.getAu());
	}
	public static void removeCourseForParticularStud(String userID, Course course) {
		listOfStudents.get(userID).registeredCourses.remove(course);
		addAU(userID, course.getAu()*-1);
	}
}


