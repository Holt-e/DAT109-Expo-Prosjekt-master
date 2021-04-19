package no.hvl.dat109.helpers.helpersTest;

import no.hvl.dat109.helpers.PasswordUtils;
import org.junit.jupiter.api.Test;

import java.nio.charset.Charset;
import java.util.Random;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class PasswordUtilsTest {

	/**
	 * Tests with a random string
	 */
	@Test
	public void testHashing() {

		byte[] array = new byte[7]; // length is bounded by 7
		new Random().nextBytes(array);
		String generatedString = new String(array, Charset.forName("UTF-8"));
		String falseString = "THisaintrIGHT123";

		String hashed = PasswordUtils.hashPassword(generatedString);
		assertTrue(PasswordUtils.checkPassword(generatedString, hashed));


		assertFalse(PasswordUtils.checkPassword(falseString, hashed));
	}

	@Test
	public void testNormalString() {
		String s = "Fødselsdato1998æøå";
		String hashed = PasswordUtils.hashPassword(s);

		assertTrue(PasswordUtils.checkPassword(s, hashed));
		assertFalse(PasswordUtils.checkPassword("DetteStemmer Ikkeøæå", hashed));
	}
}