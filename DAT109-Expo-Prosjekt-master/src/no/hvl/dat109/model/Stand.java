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
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.util.ArrayList;
import java.util.List;

/**
 * A stand, it has a name, and an expo.
 */
@Entity
@Table(schema = "Expo", name = "Stands")
public class Stand {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int standId;
	private String standName;

	@ManyToOne
	@JoinColumn(name = "expoId")
	private Expo expo;

	public List<Vote> getVotes() {
		return votes;
	}

	@OneToMany(mappedBy = "stand")
	private List<Vote> votes = new ArrayList<>();

	/**
	 * @param standName
	 * @param expo
	 * Constructor for stand
	 */
	public Stand(String standName, Expo expo) {
		this.standName = standName;
		this.expo = expo;
	}

	/**
	 * Empty constructor for javabean comparability
	 */
	public Stand() {
	}

	public int getStandId() {
		return standId;
	}

	public String getStandName() {
		return standName;
	}

	public void setStandName(String standName) {
		this.standName = standName;
	}

	public Expo getExpo() {
		return expo;
	}

	public void setExpoId(Expo expo) {
		this.expo = expo;
	}

	public Integer getScore() {
		int score = 0;
		for (Vote vote : votes) {
			score += vote.getScore();
		}
		return score;
	}
}