package entities;

import java.security.NoSuchAlgorithmException;
import java.util.*;

public class Users {
	Scanner input = new Scanner(System.in);			// global scanner
	
	// getter and mutator methods are allowed for the following attribute(s)
	private String name;							// changes can be made if required, surname changes if married etc
	private final String gender;					// no changes should be made, no mutator methods provided
	private String nationality;						// changes can be made if required
	private String schoolID;						// can switch school if required
	private boolean adminPrivileges = false;		// Only Staff has adminPrivileages
	
	// personal information that need to be hidden from direct changes; cannot provide getter methods due to privacy reasons
	private final String identificationKey;			// Your matriculation/staff id is used to prove that the account belongs to you (matric num no change at all)
	private List<String> pastPasswords;				// prevents re-using of passwords
	private String hashedPassword;					// changes can be made through mutator method (changePW()), no getter because password should be remembered
	
	// personal information that can be released with identification key
	private final String userID;					// no changes should be made, no mutator methods provided; can have getter on pretext you provide identification key
	
	// account intrusion protection 
	private boolean accountLocked = false;			// false means account not locked; locked after 3 failed attempts; change password to unlock it
	private final int MAXTRIES = 3;
	
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
		
		this.pastPasswords = new ArrayList<String>();
		this.pastPasswords.add(this.hashedPassword);
	}
	
	public boolean login() throws NoSuchAlgorithmException {
		System.out.print("Please enter your User ID: ");
		String u_id = input.nextLine();
		System.out.print("Please enter your User password: ");
		String u_pw = input.nextLine();
		String convert2Hashed = Hash.encode(u_pw);
		
		int counter = 1;
		boolean loginSuccessful = this.userID.equals(u_id) && this.hashedPassword.equals(convert2Hashed); 		// account lock after 3 failed attempts;
		while (!loginSuccessful && counter < MAXTRIES) {
			System.out.printf("Login Failed! %d more attempts left!\n", MAXTRIES - counter);
			System.out.print("Please enter your User ID: ");
			u_id = input.nextLine();
			System.out.print("Please enter your User password: ");
			u_pw = input.nextLine();
			convert2Hashed = Hash.encode(u_pw);
			counter ++;
			loginSuccessful = this.userID.equals(u_id) && this.hashedPassword.equals(convert2Hashed);
		}
		
		if (!loginSuccessful) {
			this.accountLocked = true;
			System.out.println("Account had been locked after 3 failed attempts");
		}
		
		return loginSuccessful;
	}
	
	public boolean changePW() throws NoSuchAlgorithmException {
		System.out.println("Please enter your Matriculation or Staff ID: ");
		String key = input.nextLine();
		if (!key.equals(this.identificationKey)) {												// EG: you must key in your matric number when changing password
			System.out.println("Identification Key Error - Key not found!");
			return false;
		}
		
		String newPW, newHashed;
		do 
		{
			System.out.print("Please enter a new password: ");
			newPW = input.nextLine();
			newHashed = Hash.encode(newPW);
			
		} while (newHashed.equals(this.hashedPassword) && !this.pastPasswords.contains(newHashed));				// prevents user from setting same or reused password 
		this.hashedPassword = newHashed;
		System.out.println("Password had been updated successfully!");
		return true;
	}
	
	public void unlockAccount() throws NoSuchAlgorithmException {
		if (!this.accountLocked) {
			System.out.println("Account is not locked!");
			return;
		}
		boolean changePass = changePW();
		if (changePass) {
			System.out.println("Unlocking of account is successful!");
			this.accountLocked = false;
		}
	}
	
	// getter and mutator methods for those allowed attributes
	public String getName() { return this.name; }
	public void changeName() {
		System.out.println("Enter updated particulars: ");
		String newName = input.nextLine();
		this.name = newName;
		System.out.println("Updated name in system: " + this.name);
	}
	
	public String getGender() { return this.gender; }
	
	public String getNationality() { return this.nationality; }
	public void changeNationality() { 
		System.out.println("Enter updated information: ");
		String newNationality = input.nextLine();
		this.nationality = newNationality;
	}
	
	public String getSchoolID() { return this.schoolID; }
	public void changeSchool() {
		System.out.println("Enter new school ID: ");
		String newSchoolID = input.nextLine();
		this.schoolID = newSchoolID;
	}
	
	public static void setAdminPrivileges(Object person) {					// static function -> allows the admin rights to be updated
		if (person instanceof Staff) {
			Users temp = (Users) person;
			if (temp.adminPrivileges) 
				System.out.println("Account already has administrative privileges");
			else {
				temp.adminPrivileges = true;
				System.out.println("Administrator privileages added to account!");
			}
		} else
			System.out.println("Account does not fulfil requirements to get administrative privileges");
	}
	
	public String getUserID() {
		System.out.println("Please enter matriculation or staff id for identification purposes: ");
		String key = input.nextLine();
		if (key.equals(this.identificationKey)) {
			System.out.println("User ID: " + this.userID);
			return this.userID;
		}
		System.out.println("Information provided is not correct!");
		return "Error";
	}	
	
	// intermediary testing
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Users user1 = new Users("Snek", "Snek19232", "HarrowPolice", "Snek", "Martian", "SCSE", "18002780022");
		System.out.printf("%s %s %s %s %s %s %s\n", user1.name, user1.userID, user1.hashedPassword, user1.gender, user1.nationality, user1.schoolID, user1.identificationKey);
		user1.login();
		user1.getUserID();
		user1.changePW();
		user1.unlockAccount();
		System.out.printf("%s %s %s %s %s %s %s\n", user1.name, user1.userID, user1.hashedPassword, user1.gender, user1.nationality, user1.schoolID, user1.identificationKey);
	}
}