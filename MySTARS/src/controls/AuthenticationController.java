package controls;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;

import entities.Hash;
import entities.PasswordField;
import entities.User;

public class AuthenticationController implements AuthenticationControl{
	private Scanner input = new Scanner(System.in);

	/*
	 * @param user: the object for which the input user name is the key
	 * @param inputID: input user name which needs to be checked for validity
	 * @param inputPassword: raw human readable password that needs to be hashed
	 *
	 * login will run a forever while loop to check the validity of the account details entered
	 */

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

	/*
	 * @param user: user object which requires changes to the password attribute
	 * @param passwordChange: raw human readable password string that will be hashed
	 */

	public void changePW(User user, String passwordChange) throws NoSuchAlgorithmException {
		String hashedPassword = Hash.encode(passwordChange);
		user.updatePW(hashedPassword);
	}
}
