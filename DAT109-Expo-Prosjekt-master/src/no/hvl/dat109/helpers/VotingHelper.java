/*
 * Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
 * Ørjan Enes (180337@stud.hvl.no)
 * Joakim Johesan (182719@stud.hvl.no)
 * Eirik Alvestav (180339@stud.hvl.no)
 * Adrian Holte (182714@stud.hvl.no)
 * Kjetil Hunshammer (182718@stud.hvl.no)
 *
 * All Rights Reserved.
 *
 * Unauthorized copying of this file via any medium is strictly prohibited
 * Proprietary and confidential.
 * Distribution for testing purposes is only permitted within the
 * Kronstad campus of the Western Norway University of
 * Applied Sciences (Høgskulen på Vestlandet, HVL) in Bergen, Norway.
 *
 */

package no.hvl.dat109.helpers;

import no.hvl.dat109.model.Expo;

import javax.servlet.http.HttpServletRequest;
import java.sql.Timestamp;

public class VotingHelper {

	public static int getScore(HttpServletRequest request) {
		String scoreRaw = request.getParameter("rating");
		int score = -1;

		try {
			score = Integer.parseInt(scoreRaw);
			score = MathsHelper.clamp(score, 0, 5);

		} catch(NumberFormatException e) {

		}

		return score;
	}

	/**
	 * @param expo expo to check against
	 * @return true if expo is active, false if expo is over
	 *
	 * Checks if an expo is still active against current server time.
	 */
	public static boolean isExpoActive(Expo expo) {
		Timestamp expoStartTime = expo.getStartTime();
		Timestamp expoEndTime = expo.getEndTime();
		Timestamp actualTime = new Timestamp(System.currentTimeMillis());

		return (expoEndTime.compareTo(actualTime) > 0 && expoStartTime.compareTo(actualTime) < 0);
	}
}