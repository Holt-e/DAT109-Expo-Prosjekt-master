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

import javax.persistence.CascadeType;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import java.sql.Timestamp;
import java.util.ArrayList;
import java.util.List;

/**
 * An expo, it has a name, a startTime, and an endTime.
 */
@Entity
@Table(schema = "expo", name = "expos")
public class Expo {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	private int expoId;
	private String expoName;
	private Timestamp startTime;
	private Timestamp endTime;

	@OneToMany(mappedBy = "expo", cascade = CascadeType.ALL, orphanRemoval = true)
	@JoinColumn(name = "standId")
	private List<Stand> stands = new ArrayList<>();

	/**
	 * @param expoName name of the expo
	 * @param startTime a Timestamp object, defines time in milliseconds since 1970
	 * @param endTime a Timestamp object, defines time in milliseconds since 1970
	 *
	 * Constructor for Expo object
	 */
	public Expo(String expoName, Timestamp startTime, Timestamp endTime) {
		this.expoName = expoName;
		this.startTime = startTime;
		this.endTime = endTime;

	}

	/**
	 * Empty constructor for javabean comparability
	 *
	 */
	public Expo() {

	}

	public int getId() {
		return expoId;
	}

	public String getExpoName() {
		return expoName;
	}

	public Timestamp getStartTime() {
		return startTime;
	}

	public Timestamp getEndTime() {
		return endTime;
	}

	public void setExpoName(String expoName) {
		this.expoName = expoName;
	}

	public void setStartTime(Timestamp startTime) {
		this.startTime = startTime;
	}

	public void setEndTime(Timestamp endTime) {
		this.endTime = endTime;
	}

	public void addStand(Stand s) {
		stands.add(s);
	}

	public void removeStand(Stand s) {
		stands.remove(s);
	}

	public int getExpoId() {
		return expoId;
	}

	public void setExpoId(int expoId) {
		this.expoId = expoId;
	}

	public List<Stand> getStands() {
		return stands;
	}

	public void setStands(List<Stand> stands) {
		this.stands = stands;
	}

	@Override
	public String toString() {
		return "Expo{" +
			"expoId=" + expoId +
			", expoName='" + expoName + '\'' +
			", startTime=" + startTime +
			", endTime=" + endTime +
			", stands=" + stands +
			'}';
	}
}