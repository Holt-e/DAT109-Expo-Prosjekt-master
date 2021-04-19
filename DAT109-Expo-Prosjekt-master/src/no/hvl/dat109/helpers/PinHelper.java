package no.hvl.dat109.helpers;

import java.util.Random;

/**
 * Generates random pin codes
 */
public class PinHelper {

	private static final int DIGITS = 4;

	/**
	 * @return a randomly generated 4-digit pin-code String
	 */
	public static String newPin() {
		return newPin(DIGITS);
	}

	/**
	 * @param digits amount of digits the pin should contain
	 * @return a randomly generated 4-digit pin-code String
	 */
	public static String newPin(int digits) {
		Random r = new Random();
		StringBuilder pin = new StringBuilder();
		for(int i = 0; i < digits; i++) {
			pin.append(r.nextInt(10));
		}

		return pin.toString();
	}
}