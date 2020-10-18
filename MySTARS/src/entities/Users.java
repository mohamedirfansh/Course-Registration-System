package entities;

import java.util.*;

public abstract class Users {
	Scanner input = new Scanner(System.in);			// global scanner
	
	private String name;							// changes can be made if required, surname changes if married etc
	private final String userID;					// no changes should be made, no mutator methods provided
	private String userPW;							// changes can be made through mutator methods
	private final String gender;					// no changes should be made, no mutator methods provided
	private String nationality;						// changes can be made if required
	private String schoolID;						// can switch school if required
	private boolean accountLocked = false;			// account not locked, only locked after 3 failed attempts; cannot change by user
	private final int MAXTRIES = 3;
	
	protected boolean adminPrivileages;				// instantiated in child classes (Staff & Student)
	
	public String getUserID() { return this.userID; }
	public String getGender() { return this.gender; }
	
	
	
	
	public Users(String name, String userID, String userPW, String gender, String nationality, String schoolID) {
		this.name = name;
		this.userID = userID;
		this.userPW = userPW;
		this.gender = gender;
		this.nationality = nationality;
		this.schoolID = schoolID;
	}
	
	public boolean login() {
		System.out.print("Please enter your User ID: ");
		String u_id = input.next();
		System.out.print("Please enter your User password: ");
		String u_pw = input.next();
		
		int counter = 1;
		boolean loginSuccessful = this.userID.equals(u_id) && this.userPW.equals(u_pw); 		// account lock after 3 failed attempts;
		while (!loginSuccessful && counter < MAXTRIES) {
			System.out.printf("Login Failed! %d more attempts left!\n", MAXTRIES - counter);
			System.out.print("Please enter your User ID: ");
			u_id = input.next();
			System.out.print("Please enter your User password: ");
			u_pw = input.next();
			counter ++;
			loginSuccessful = this.userID.equals(u_id) && this.userPW.equals(u_pw);
		}
		
		if (!loginSuccessful) {
			this.accountLocked = true;
			System.out.println("Account had been locked after 3 failed attempts");
		}
		
		return loginSuccessful;
	}
	
	public void changePW() {
		String newPW;
		do 
		{
			System.out.print("Please enter a password that is different from the current password: ");
			newPW = input.next();
		} while (newPW.equals(this.userPW));			// prevents user from setting password 
		this.userPW = newPW;
	}
	
	public abstract void unlockAccount();
}
