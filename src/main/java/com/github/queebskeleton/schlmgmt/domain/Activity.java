package com.github.queebskeleton.schlmgmt.domain;

import java.time.LocalDateTime;

/**
 * Domain class for a Subject activity.
 * 
 * @author Rian Carlo Reyes
 *
 */
public class Activity {
	
	// Database Id
	private int id;
	// Activity Name
	private String name;
	// When this Activity was created
	private LocalDateTime createdOn;
	// Deadline of this Activity
	private LocalDateTime deadline;
	// Total Score of this Activity. For grading purposes
	private int totalScore;
	
	/**
	 * Creates a fresh activity with the specified properties.
	 * 
	 * @param name the name of the activity
	 * @param deadline deadline to be set
	 * @param totalScore highest achievable score of this activity
	 */
	public Activity(String name, LocalDateTime deadline, int totalScore) {
		super();
		this.name = name;
		this.createdOn = LocalDateTime.now();
		this.deadline = deadline;
		this.totalScore = totalScore;
	}

	/**
	 * Constructs a new Activity with pre-initialized data.
	 * Used by JDBC when fetching already persisted Activities.
	 * 
	 * @param id the database id
	 * @param name the name of the activity
	 * @param createdOn when this activity was created
	 * @param deadline deadline to be set
	 * @param totalScore highest achievable score of this activity
	 */
	public Activity(int id, String name, LocalDateTime createdOn, LocalDateTime deadline, int totalScore) {
		super();
		this.id = id;
		this.name = name;
		this.createdOn = createdOn;
		this.deadline = deadline;
		this.totalScore = totalScore;
	}

	public int getId() {
		return id;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public LocalDateTime getCreatedOn() {
		return createdOn;
	}

	public void setCreatedOn(LocalDateTime createdOn) {
		this.createdOn = createdOn;
	}

	public LocalDateTime getDeadline() {
		return deadline;
	}

	public void setDeadline(LocalDateTime deadline) {
		this.deadline = deadline;
	}

	public int getTotalScore() {
		return totalScore;
	}

	public void setTotalScore(int totalScore) {
		this.totalScore = totalScore;
	}

}
