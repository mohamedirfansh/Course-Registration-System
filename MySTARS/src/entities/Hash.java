package entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// simplified class involved in password hashing

public class Hash {

	/*
	 * Will be called after getting the input from the user, through PasswordField class
	 * @ param human readable password
	 * @ return encoded password that cannot be read by humans
	 */

	public static String encode(String stringPass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] encodedhash = digest.digest(stringPass.getBytes(StandardCharsets.UTF_8));
		String shaHex = bytesToHex(encodedhash);
		return shaHex;
	}

	/*
	 * @ param byte array from method "encode"
	 * @ return encoded password
	 */

	private static String bytesToHex (byte[] hash) {
		StringBuilder hexString = new StringBuilder(2 * hash.length);
		for (int i = 0; i < hash.length; i++) {
			String hex = Integer.toHexString(0xff & hash[i]);
			if (hex.length() == 1) {
				hexString.append('0');
			}
			hexString.append(hex);
		}
		return hexString.toString();
	}
}
