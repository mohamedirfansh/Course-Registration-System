package entities;

import java.io.Serializable;

/**
 * Staff is a specific implementation of a user. It extends from the user class and makes use of its general methods
 * and attributes, as well as adds on to it with its own specialized attributes and methods that are unique to a
 * staff member.
 */
public class Staff extends User{
	public static final long serialVersionUID = 2L; // Change to 2L back later

	/**
	 * Constructor for the user Staff, which is used to initialize the fields in the specified staff instance
	 * by invoking the base class constructor.
	 *
	 * @param name, which is the  name of staff member
	 * @param userID, which is the unique username given to all the users and complements the identificationKey.
	 * @param gender, which is the gender of the staff member.
	 * @param nationality, which is the nationality of the staff member.
	 * @param schoolID, which is the department that the staff member resides in.
	 * @param identificationKey, String, which is a unique numeric identifier given to all staff members.
	 */
	public Staff(String name, String userID, String gender, 
			String nationality, int schoolID,String identificationKey){
		super(name, userID, gender, nationality, schoolID, identificationKey);
	}

}
