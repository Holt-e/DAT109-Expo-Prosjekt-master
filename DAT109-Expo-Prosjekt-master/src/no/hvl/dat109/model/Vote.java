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

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

/**
 * A vote, it has a score, a userId, and a standId that it is applied to.
 */
@Entity
@Table(schema = "Expo", name = "Votes")
public class Vote {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int voteId;
	private int score;

	@ManyToOne
	@JoinColumn(name = "userId")
	private User user;

	@ManyToOne
	@JoinColumn(name = "standId")
	private Stand stand;

	/**
	 * @param score
	 * @param user
	 * @param stand
	 * Constructor for Vote
	 */
	public Vote(int score, User user, Stand stand) {
		this.score = score;
		this.user = user;
		this.stand = stand;
	}

	/**
	 * Empty constructor for javabean comparability
	 */
	public Vote() {

	}

	public int getVoteId() {
		return voteId;
	}

	public int getScore() {
		return score;
	}

	public void setScore(int score) {
		this.score = score;
	}

	public void setVoteId(int voteId) {
		this.voteId = voteId;
	}

	public User getUser() {
		return user;
	}

	public void setUser(User user) {
		this.user = user;
	}

	public Stand getStand() {
		return stand;
	}

	public void setStand(Stand stand) {
		this.stand = stand;
	}

	@Override
	public String toString() {
		return "Vote{" +
			"voteId=" + voteId +
			", score=" + score +
			", user=" + user +
			", stand=" + stand +
			'}';
	}
}
