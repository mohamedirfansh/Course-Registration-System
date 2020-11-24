package entities;

import java.io.Serializable;

/**
 * User is a top-level class that defines a user on a general level. It contains attributes and methods
 * that are part of common functionality to all types of users, such as admin, staff, student, etc. The main use of this class
 * is to extend from it and specialize it to different types of users with the help of inheritance.
 *
 * Class Attributes:
 * -> name : String, which is the  name of user. This field does not change.
 * -> gender : String, which is the gender of the user.
 * -> nationality : String, which is the nationality of the user.
 * -> schoolID: int, which is the department that the user resides in.
 * -> identificationKey : String, which is a unique numeric identifier given to all users.
 * -> userID : String, which is the unique username given to all the users and complements the identificationKey.
 * 			It faciliates an easier use of an identifier for general purpose requirements, such as logging into the company domain.
 * -> serialVersionUID : long, Needed to verify that serialization and deserialization is done correctly
 */
public class User implements Serializable{
	private String name;
	private String nationality;
	private int schoolID;
	private final String identificationKey;
	private final String userID;
	private final String gender;

	private static final long serialVersionUID = 2L;

	/**
	 * Constructor for the user class, which is used to initialize the fields in the specified user instance.
	 *
	 * @param name, which is the  name of user
	 * @param userID, which is the unique username given to all the users and complements the identificationKey.
	 * @param gender, which is the gender of the user.
	 * @param nationality, which is the nationality of the user.
	 * @param schoolID, which is the department that the user resides in.
	 * @param identificationKey, String, which is a unique numeric identifier given to all users.
	 */
	public User(String name, String userID, String gender,
			String nationality, int schoolID,
			String identificationKey){
		this.name = name;
		this.userID = userID;
		this.gender = gender;
		this.nationality = nationality;
		this.schoolID = schoolID;
		this.identificationKey = identificationKey;
	}


	/**
	 * Getters for all the class attributes.
	 * @return specified attribute defined for the getter.
	 */
	public String getName() { return this.name; }
	public String getGender() { return this.gender; }
	public String getNationality() { return this.nationality; }
	public int getSchoolID() { return this.schoolID; }
	public void updateSchoolID(int schoolID) { this.schoolID = schoolID; }
	public String getUserID() { return this.userID;	}
	public String getIDKey() { return this.identificationKey; }
}