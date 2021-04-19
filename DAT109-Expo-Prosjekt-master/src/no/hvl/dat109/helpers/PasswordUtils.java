package no.hvl.dat109.helpers;

import javax.crypto.SecretKeyFactory;
import javax.crypto.spec.PBEKeySpec;
import javax.xml.bind.DatatypeConverter;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.security.spec.InvalidKeySpecException;
import java.security.spec.KeySpec;
import java.util.Arrays;

/**
 * Utils for hashing passwords with PBKDF2WithHmacSHA1 algorithm, uses a 32byte salt.
 * Has a method for hashing and a method for checking an input password vs. a stored password.
 * This class is not verified by any third-party, and may contain vulnerabilities.
 */
public class PasswordUtils {

	private static final int SALT_LENGTH = 32;
	private static final int HASH_ITERATIONS = 65536;

	/**
	 * @param password the wanted password to be hashed
	 * @return a hashed password+salt string
	 *
	 * Hashes a password using the PBKDF2WithHmacSHA1 algorithm
	 */
	public static String hashPassword(String password) {
		byte[] salt = generateSalt();
		return hashWithSalt(salt, password);
	}

	/**
	 * @param password the password to be checked
	 * @param hashed the actual password in the database
	 * @return true if equal, false else
	 *
	 * Checks if a input password matches the input hashed password.
	 */
	public static boolean checkPassword(String password, String hashed) {
		byte[] salt = getSaltFromHashedString(hashed);
		return hashWithSalt(salt, password).equals(hashed);
	}

	private static String hashWithSalt(byte[] salt, String password) {

		String kryptert = "";
		try {
			KeySpec spec = new PBEKeySpec(password.toCharArray(), salt, 65536, 128);
			SecretKeyFactory factory = SecretKeyFactory.getInstance("PBKDF2WithHmacSHA1");
			byte[] hash = factory.generateSecret(spec).getEncoded();
			byte[] saltPlusDigest = addTogether(salt, hash);

			kryptert = DatatypeConverter.printBase64Binary(saltPlusDigest);

		} catch(NoSuchAlgorithmException | InvalidKeySpecException e) {
			e.printStackTrace();
			System.out.println("Something went wrong with the password hashing.");
		}
		return kryptert;
	}

	private static byte[] generateSalt() {
		byte[] salt = new byte[SALT_LENGTH];
		new SecureRandom().nextBytes(salt);
		return salt;
	}

	private static byte[] getSaltFromHashedString(String kryptert) {
		byte[] saltPlusDigest = DatatypeConverter.parseBase64Binary(kryptert);
		return Arrays.copyOf(saltPlusDigest, SALT_LENGTH);
	}

	private static byte[] addTogether(byte[] tabell1, byte[] tabell2) {

		byte[] oneAndTwo = new byte[tabell1.length + tabell2.length];
		System.arraycopy(tabell1, 0, oneAndTwo, 0, tabell1.length);
		System.arraycopy(tabell2, 0, oneAndTwo, tabell1.length, tabell2.length);
		return oneAndTwo;
	}

}