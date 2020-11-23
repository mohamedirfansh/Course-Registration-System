package controls;

import java.security.NoSuchAlgorithmException;

import entities.Staff;
import entities.Student;
import entities.User;

public interface AuthenticationControl {
	public boolean login(Staff user, String inputID, String inputPassword) throws NoSuchAlgorithmException;
	public boolean login(Student user, String inputID, String inputPassword) throws NoSuchAlgorithmException;
	public void changePW(User user, String passwordChange) throws NoSuchAlgorithmException;
}
