package controls;

import java.security.NoSuchAlgorithmException;

import entities.User;

public interface AuthenticationControl {
	public boolean login(User user, String inputID, String inputPassword) throws NoSuchAlgorithmException;
	public void changePW(User user, String passwordChange) throws NoSuchAlgorithmException;
}
