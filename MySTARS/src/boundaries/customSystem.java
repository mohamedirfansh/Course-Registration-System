package boundaries;

import java.security.NoSuchAlgorithmException;
import java.util.Scanner;
import controls.AuthenticationControl;
import controls.AuthenticationController;
import controls.DatabaseControl;
import entities.PasswordField;
import entities.Staff;
import entities.Student;
import entities.User;

public class customSystem {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		Scanner input = new Scanner(System.in);
		AuthenticationControl loginMgr = new AuthenticationController();
		DatabaseControl allDetails = new DatabaseControl();
		
		System.out.println("Please select a domain:");
		System.out.println("1) Staff");
		System.out.println("2) Student");
		System.out.print("> ");
		
		int choice = input.nextInt();
		mainloop:
		while (true) {
			switch(choice) {
			case 1:
				System.out.println("Staff domain selected...");
				break mainloop;
			case 2:
				System.out.println("Student domain selected...");
				break mainloop;
			default:
				System.out.println("No selection made with input!");
				choice = input.nextInt();
			}
		}
		
		boolean loginedSuccess = false;
		Staff staff = null; Student student = null;
		while (!loginedSuccess) {
			System.out.println();
			System.out.print("Enter your username: ");
			String input_uid = input.next();
//			String input_pw = PasswordField.readPassword();
			String input_pw = input.next();
			if (choice == 2) {
				student = allDetails.getStudentData(input_uid);
				loginedSuccess = loginMgr.login(student, input_uid, input_pw);
			} else {
				staff = allDetails.getStaffData(input_uid);
				loginedSuccess = loginMgr.login(staff, input_uid, input_pw);
			}
		}
		System.out.println("Initializing the interface...");
		switch(choice) {
		case 1:
			StudentUI.StudentUIMain(student);
		case 2:
			StaffUI.staffUIInit(staff);
		}
	}
}
