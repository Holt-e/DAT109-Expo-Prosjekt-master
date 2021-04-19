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

import com.twilio.Twilio;
import com.twilio.rest.api.v2010.account.Message;
import com.twilio.type.PhoneNumber;
import no.hvl.dat109.constants.TwillioAuth;
import no.hvl.dat109.model.User;

public class TwilloHelper {

	public static boolean sendMessage(String to, String body) {
		try {
			Twilio.init(TwillioAuth.SID, TwillioAuth.TOKEN);

			Message message = Message
				.creator(new PhoneNumber(to), new PhoneNumber(TwillioAuth.PHONE), body)
				.create();

			return true;

		} catch(Exception e) {
			return false;
		}
	}

	public static void sendPinMessage(User user) {
		if (PhoneNumberHelper.phoneNumberValid(user.getUsername())) {
			if (!user.isVerified()) {
				String pin = user.getPin();
				String body = String.format("Welcome to Expo! Here is your verification code '%s'.", pin);

				System.out.println(user.getUsername());
				System.out.println(body);
				TwilloHelper.sendMessage(user.getUsername(), body);

			} else {
				// TODO - Forgotten PIN
			}
		}
	}
}
