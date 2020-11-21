package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

public class PasswordField {

	/*
	 * Masks echoing of console input, replacing echoed input with asterisks
	 * Asterisks for display purposes only, input by user still being read in
	 * @ return : returns the raw input String by the user, and not the asterisks
	 * Therefore, returned password still human readable
	 */

	public static String readPassword () {
		EraserThread et = new EraserThread("Enter password: ");
		Thread mask = new Thread(et);
		mask.start();
		BufferedReader in = new BufferedReader(new InputStreamReader(System.in));
		String password = "";
		try {
			password = in.readLine();
		} catch (IOException ioe) {
			ioe.printStackTrace();
		}
		et.stopMasking();
		return password;
	}
}
