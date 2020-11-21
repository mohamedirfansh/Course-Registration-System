package controls;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import entities.Hash;
import entities.PasswordField;
import entities.User;

public class AuthenticationController implements AuthenticationControl{
	private Scanner input = new Scanner(System.in);
	
	public boolean login(User user, String inputID, String inputPassword) throws NoSuchAlgorithmException {
		String user_id = user.getUserID();
		String hashedPassword = user.getUserPW();
		String hashedInput = Hash.encode(inputPassword);
		
		boolean loginSuccessful = user_id.equals(inputID) && hashedPassword.equals(hashedInput); 		
		while (!loginSuccessful) {
			System.out.print("Please enter your User ID: ");
			inputID = input.nextLine();
			inputPassword = PasswordField.readPassword();
			hashedInput = Hash.encode(inputPassword);
			loginSuccessful = user_id.equals(inputID) && hashedPassword.equals(hashedInput);
		}
		
		if (loginSuccessful) 
			System.out.println("Login successful!");
		
		return loginSuccessful;
	}
	
	public void changePW(User user, String passwordChange) throws NoSuchAlgorithmException {
		String hashedPassword = Hash.encode(passwordChange);
		user.updatePW(hashedPassword);
	}
}
