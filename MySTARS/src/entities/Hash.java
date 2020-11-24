package entities;

import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;

/**
 * Hash is used to encode the user password, such that it is stored in a format only understood by the machine.
 */
public class Hash {

	/**
	 * encode() encodes a given string, in most cases a password, and returns its corresponding encoding using bytesToHex()
	 * @param stringPass, which is the password to be encoded
	 * @return shaHex, which is the encoded password obtained by encoding stringPass.
	 * @throws NoSuchAlgorithmException
	 */
	public static String encode(String stringPass) throws NoSuchAlgorithmException {
		final MessageDigest digest = MessageDigest.getInstance("SHA-256");
		final byte[] encodedhash = digest.digest(stringPass.getBytes(StandardCharsets.UTF_8));
		String shaHex = bytesToHex(encodedhash);
		return shaHex;
	}

	/**
	 * @param hash encoded string information, converted from string to hex byte code
	 *
	 * @return converts hex byte code to decimal and obtain the ASCII code and return that ASCII code as string for
	 * the entire byte array
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
