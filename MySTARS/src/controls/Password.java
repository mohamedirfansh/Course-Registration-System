package controls;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import entities.Hash;

public class Password implements Serializable {

	public static final long serialVersionUID = 2L;
	private static HashMap<String, String> allPW = new HashMap<>();

	public static void addNewPassword(String userID, String inputPassword) throws NoSuchAlgorithmException {
		String hashedPassword = Hash.encode(inputPassword);
		allPW.put(userID, hashedPassword);
	}

	public static void updatePassword(String userID, String updatedPassword) throws NoSuchAlgorithmException {
		String newHashedPassword = Hash.encode(updatedPassword);
		for (String key : allPW.keySet()) {
			if (key.equals(userID)) {
				allPW.replace(key, newHashedPassword);
			}
		}
	}

	public static String getHash(String userID) {
		return allPW.get(userID);
	}

	public static HashMap<String, String> getHashMap() {
		return allPW;
	}

	/**
	 * writeToFile() writes all the updated passwords to the binary file.
	 */
	public static void writeToFile() {
		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("password.dat", allPW);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
