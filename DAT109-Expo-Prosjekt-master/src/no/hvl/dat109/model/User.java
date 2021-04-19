/*
 * Copyright (c) 2019 Grim Thomas Hammerstad (182722@stud.hvl.no)
 * Ørjan Enes (180337@stud.hvl.no)
 * Joakim Johesan (182719@stud.hvl.no)
 * Eirik Alvestav (180339@stud.hvl.no)
 * Adrian Holthe (182714@stud.hvl.no)
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

package no.hvl.dat109.model;

import no.hvl.dat109.helpers.PinHelper;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A user, is saved in the database for login and voting purposes.
 */
@Entity
@Table(schema = "Expo", name = "Users")
public class User {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int userId;            //Auto-generated SERIAL type, is made by the database, is empty until user object is persisted

	private String username;    //This is the users phone number
	private String pin;         //This is the users "PIN" code
	private boolean isJury;     //If the user is a jury member
	private boolean isAdmin;    //If the user is an admin
	private boolean isVerified; //If the users phone number is verified

	@OneToMany(mappedBy = "user")
	private List<Vote> votes = new ArrayList<>();    //All the users votes

	/**
	 * @param username
	 * @param isJury if the user is a jury member
	 * @param isAdmin if the user is an admin
	 * @param isVerified if the users phone number is verified
	 */
	public User(String username, boolean isJury, boolean isAdmin, boolean isVerified) {
		this.username = username;
		this.pin = PinHelper.newPin();
		this.isJury = isJury;
		this.isAdmin = isAdmin;
		this.isVerified = isVerified;
	}

	/**
	 * Empty constructor for javabean comparability
	 */
	public User() {
    }

	public String getPin() {
		return pin;
	}

	public void setPin(String pin) {
		this.pin = pin;
	}

	public int getUserId() {
		return userId;
	}

	public void setUserId(int userId) {
		this.userId = userId;
	}

	public String getUsername() {
		return username;
	}

	public void setUsername(String username) {
		this.username = username;
	}

	public boolean isJury() {
		return isJury;
	}

	public void setJury(boolean jury) {
		isJury = jury;
	}

	public List<Vote> getVotes() {
		return votes;
	}

	public void setVotes(List<Vote> votes) {
		this.votes = votes;
	}

	public boolean isAdmin() {
		return isAdmin;
	}

	public void setAdmin(boolean admin) {
		isAdmin = admin;
	}

	public boolean isVerified() {
		return isVerified;
	}

	public void setVerified(boolean verified) {
		isVerified = verified;
	}

	@Override
	public String toString() {
		return "User{" +
				"userId=" + userId +
				", username='" + username + '\'' +
				", pin='" + pin + '\'' +
				", isJury=" + isJury +
				'}';
	}
}