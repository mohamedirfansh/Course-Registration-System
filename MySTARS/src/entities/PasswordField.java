package entities;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;

/**
 * PasswordField is an entity that is used when entering sensitive information on the console.
 * It defines a specific field that makes use of input masking with the help of the functions defined in eraserThread.
 */
public class PasswordField {
	/**
	 * Masks echoing of console input, replacing echoed input with asterisks
	 * Asterisks is for display purposes only, input by user still being read in.
	 *
	 * @return the raw input String by the user, and not the asterisks. This string is in human readable format.
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
