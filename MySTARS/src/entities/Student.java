package entities;

import java.security.NoSuchAlgorithmException;

public class Student extends Users{
	public Student(String name, String userID, String userPW,
			String gender, String nationality, String schoolID, 
			String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, userPW, gender, nationality, schoolID, identificationKey);
	}
	
	// intermediary testing
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Object staff1 = new Student("Snek", "Snek19232", "HarrowPolice", "Snek", "Martian", "SCSE", "385912");
		setAdminPrivileges(staff1);
	}
}
