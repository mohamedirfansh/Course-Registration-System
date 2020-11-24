package data;

import java.security.NoSuchAlgorithmException;

import controls.Password;

public class createUserData {
	public static void main(String[] args) throws NoSuchAlgorithmException {
		createStudentData.createStudent();
		createStaffData.createStaff();
	}
}
