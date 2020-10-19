package entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

// simplified class involved in password hashing

public class Hash {
	public static String encode(String stringPass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] encodedhash = digest.digest(stringPass.getBytes(StandardCharsets.UTF_8));
		String shaHex = bytesToHex(encodedhash);
		return shaHex;
	}
	
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
