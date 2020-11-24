package entities;

import java.io.Serializable;
import java.security.NoSuchAlgorithmException;

public class Staff extends User{
	
	public static final long serialVersionUID = 2L; // Change to 2L back later
	
	public Staff(String name, String userID, String gender, 
			String nationality, int schoolID,String identificationKey) throws NoSuchAlgorithmException {
		super(name, userID, gender, nationality, schoolID, identificationKey);
	}

}
