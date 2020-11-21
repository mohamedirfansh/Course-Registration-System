package entities;

/*
 * Take note... this class can no longer be called directly, but needs to be run from the command line
 */


import java.io.BufferedReader;
import java.io.Console;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Users implements Serializable{	
	
	private String name;							// changes can be made if required, surname changes if married etc
	private String nationality;						// changes can be made if required
	private String schoolID;						// can switch school if required
	
	// personal information that need to be hidden from direct changes; cannot provide getter methods due to privacy reasons
	private final String identificationKey;			// Your matriculation/staff id is used to prove that the account belongs to you (matric num no change at all)
	private String hashedPassword;					// changes can be made through mutator method (changePW()), no getter because password should be remembered
	
	// personal information that can be released with identification key
	private final String userID;					// no changes should be made, no mutator methods provided; can have getter on pretext you provide identification key
	private final String gender;					// no changes should be made, no mutator methods provided
	
	public Users(String name, String userID, String userPW,
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
	public String getUserID() { return this.userID;	}
	public String getUserPW() { return this.hashedPassword; }
	public String getIDKey() { return this.identificationKey; }
	public void setName(String name){this.name = name;}

}
