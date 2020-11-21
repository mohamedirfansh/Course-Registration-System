package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class User implements Serializable{

	/*
	 * Attributes of User that will be inherited by Staff and Student classes
	 * name-> name of User of MySTARS system (constant)
	 * gender-> for recording purposes (constant)
	 * nationality-> for recording purposes (constant)
	 * schoolID-> the school which the user is currently tagged to, can be changed if required
	 * userID-> Something like NTULearn id, will not change; Presume the same for staff id
	 * hashedPassword-> only storing password strings that are not human readable for security purposes
	 * identificationKey-> something like matriculation number, wont change throughout education; presume
	 * 						the same for staff number
	 */

	private String name;
	private String nationality;
	private String schoolID;
	private final String identificationKey;
	private String hashedPassword;
	private final String userID;
	private final String gender;

	public User(String name, String userID, String userPW,
			String gender, String nationality, String schoolID,
			String identificationKey) throws NoSuchAlgorithmException {
		this.name = name;
		this.userID = userID;
		this.hashedPassword = Hash.encode(userPW);			// hashes the password to enhance privacy and security
		this.gender = gender;
		this.nationality = nationality;
		this.schoolID = schoolID;
		this.identificationKey = identificationKey;
	}

	public String getName() { return this.name; }
	public String getGender() { return this.gender; }
	public String getNationality() { return this.nationality; }
	public String getSchoolID() { return this.schoolID; }
	public void updateSchoolID(String schoolID) { this.schoolID = schoolID; }
	public String getUserID() { return this.userID;	}
	public String getUserPW() { return this.hashedPassword; }
	public void updatePW(String hashedPassword) {this.hashedPassword = hashedPassword; }
	public String getIDKey() { return this.identificationKey; }
}
