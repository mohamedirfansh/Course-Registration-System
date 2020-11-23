package controls;
import java.io.Serializable;
import java.security.NoSuchAlgorithmException;
import java.util.HashMap;

import entities.Hash;

public class Password implements Serializable {

	private static HashMap<String, String> allPW = new HashMap<>();

	public static void addNewPassword(String userID, String inputPassword) throws NoSuchAlgorithmException {
		String hashedPassword = Hash.encode(inputPassword);
		allPW.put(userID, hashedPassword);
	}

	public static String getHash(String userID) {
		return allPW.get(userID);
	}
	
	public static void updatePW() {
		try {
			SerializeDB sdb = new SerializeDB();

			// write to serialized file - update/insert/delete
			sdb.writeSerializedObject("src\\data\\password.dat", allPW);
		}

		catch (Exception e) {
			System.out.println("Exception >> " + e.getMessage());
		}
	}
}
