package boundaries;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import controls.DatabaseControl;
import entities.Hash;
import entities.Staff;
import entities.Student;
import entities.*;

/**
 * The custom system class provides a UI to the user that can be used to access the student or staff accounts.
 * After selecting the account type accordingly, and verifying their identity with a password, the respective
 * methods are displayed depending on their level of access.
 */
public class customSystem {
	
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner input = new Scanner(System.in);
		DatabaseControl allDetails = new DatabaseControl();
		
		System.out.println("Please select a domain:");
		System.out.println("1) Staff");
		System.out.println("2) Student");
		System.out.print("> ");
		
		char choice = input.next().charAt(0);
		mainloop:
		while (true) {
			switch(choice) {
			case '1':
				System.out.println("Staff domain selected...");
				break mainloop;
			case '2':
				System.out.println("Student domain selected...");
				break mainloop;
			default:
				System.out.println("No selection made with input!");
				choice = input.next().charAt(0);
			}
		}
		
		boolean loginedSuccess = false;
		Staff staff = null; Student student = null;
		String hashed, storedID;
		while (!loginedSuccess) {
			System.out.println();
			System.out.print("Enter your username: ");
			String input_uid = input.next();
			String input_pw = PasswordField.readPassword();
			//String input_pw = input.next().strip();
			String hashedInput = Hash.encode(input_pw);
			
			if (choice == '2') {
				student = allDetails.getStudentData(input_uid);
				if (student != null) {
					storedID = student.getUserID();
					hashed = allDetails.getStudentPassword(storedID);
				} else {
					continue;
				}
			} else {
				staff = allDetails.getStaffData(input_uid);
				if (staff != null) {
					storedID = staff.getUserID();
					hashed = allDetails.getStaffPassword(storedID);
				} else {
					continue;
				}
			}
			
			
			loginedSuccess = hashed.equals(hashedInput) && storedID.equals(input_uid);
		}
		System.out.println("Initializing the interface...");
		switch(choice) {
		case '1':
			StaffUI.staffUIInit(staff);
			break;
		case '2':
			StudentUI.StudentUIMain(student);
			break;
		}
	}
}
