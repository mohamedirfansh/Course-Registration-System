package controls;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import entities.Hash;

/**
 * Password provides a class for the storage of students or staff passwords in their hashed format.
 * The passwords are stored in the format of a HashMap where the UserID is mapped to their respective hashed passwords.
 * The Password object is then serialized and stored as binary files. When there is a need to access the passwords for
 * verification purposes, the Password object is deserialized and with the provided userID, we are able to look up the
 * the hashed password
 *
 * Class Attributes:
 * -> serialVersionUID : long, this is used for verification by the JVM when serializing and deserializing the Password object
 * -> allPW : HashMap<String, String>, this is used for the storage of all the userID and their passwords. Thus, we would have
 *    a single Password object to store students passwords and another Password object to store staff passwords.
 */

public class Password implements Serializable {

	public static final long serialVersionUID = 2L;
	private static HashMap<String, String> allPW = new HashMap<>();

	/**
	 * getHash() is the getter method used to get the desired password using the userID
	 *
	 * @param userID, is the userID of the user whom password you are trying to get
	 * @return The hashed password of the userID supplied in the parameters
	 */
	public static String getHash(String userID) {
		return allPW.get(userID);
	}

	/**
	 * getHashMap() is the getter method used to return the entire HashMap which stores all the userIDs and their passwords
	 *
	 * @return The entire HashMap containing all the passwords. 
	 */
	public static HashMap<String, String> getHashMap() {
		return allPW;
	}
}
