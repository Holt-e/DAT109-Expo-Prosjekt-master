package no.hvl.dat109.helpers.helpersTest;

import no.hvl.dat109.helpers.VotingHelper;
import no.hvl.dat109.model.Expo;
import org.junit.jupiter.api.Test;

import java.sql.Timestamp;

import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

public class VotingHelperTest {

	@Test
	public void testIsExpoActive() {

		Timestamp start = new Timestamp(System.currentTimeMillis());
		Timestamp end = new Timestamp(System.currentTimeMillis() + 86000000);
		Timestamp s1 = new Timestamp(System.currentTimeMillis() + 1);

		Expo expo = new Expo("VotingHelperTest", start, end);
		Expo expo2 = new Expo("VotingHelperTest", end, start);
		Expo expo3 = new Expo("VotingHelperTest", start, s1);

		assertTrue(VotingHelper.isExpoActive(expo));
		assertFalse(VotingHelper.isExpoActive(expo2));
		assertFalse(VotingHelper.isExpoActive(expo3));

	}
}