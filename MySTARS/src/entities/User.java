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
	 * serialVersionUID -> Needed to verify that serialization and deserialization is done correctly 
	 */

	private String name;
	private String nationality;
	private int schoolID;
	private final String identificationKey;
	private final String userID;
	private final String gender;

	private static final long serialVersionUID = 2L;

	public User(String name, String userID, String gender,
			String nationality, int schoolID,
			String identificationKey) throws NoSuchAlgorithmException {
		this.name = name;
		this.userID = userID;
		this.gender = gender;
		this.nationality = nationality;
		this.schoolID = schoolID;
		this.identificationKey = identificationKey;
	}

	public String getName() { return this.name; }
	public String getGender() { return this.gender; }
	public String getNationality() { return this.nationality; }
	public int getSchoolID() { return this.schoolID; }
	public void updateSchoolID(int schoolID) { this.schoolID = schoolID; }
	public String getUserID() { return this.userID;	}
	public String getIDKey() { return this.identificationKey; }
}

// Old data from Users.java

//package entities;
//
///*
// * Take note... this class can no longer be called directly, but needs to be run from the command line
// */
//
//
//import java.io.BufferedReader;
//import java.io.Console;
//import java.io.IOException;
//import java.io.InputStreamReader;
//import java.io.Serializable;
//import java.security.NoSuchAlgorithmException;
//import java.util.*;
//
//public class Users implements Serializable{	
//	
//	private String name;							// changes can be made if required, surname changes if married etc
//	private String nationality;						// changes can be made if required
//	private int schoolID;						// can switch school if required
//	
//	// personal information that need to be hidden from direct changes; cannot provide getter methods due to privacy reasons
//	private final String identificationKey;			// Your matriculation/staff id is used to prove that the account belongs to you (matric num no change at all)
//	private String hashedPassword;					// changes can be made through mutator method (changePW()), no getter because password should be remembered
//	
//	// personal information that can be released with identification key
//	private final String userID;					// no changes should be made, no mutator methods provided; can have getter on pretext you provide identification key
//	private final String gender;					// no changes should be made, no mutator methods provided
//	
//	public static final long serialVersionUID = 2L;
//	
//	public Users(String name, String userID, String userPW,
//			String gender, String nationality, int schoolID, 
//			String identificationKey) throws NoSuchAlgorithmException {
//		this.name = name;
//		this.userID = userID;
//		this.hashedPassword = Hash.encode(userPW);			// hashes the password to enhance privacy and security
//		this.gender = gender;
//		this.nationality = nationality;
//		this.schoolID = schoolID;
//		this.identificationKey = identificationKey;
//	}	
//	
//	public String getName() { return this.name; }
//	public String getGender() { return this.gender; }
//	public String getNationality() { return this.nationality; }
//	public int getSchoolID() { return this.schoolID; }
//	public String getUserID() { return this.userID;	}
//	public String getUserPW() { return this.hashedPassword; }
//	public String getIDKey() { return this.identificationKey; }
//	public void setName(String name){this.name = name;}
//
//}
//
