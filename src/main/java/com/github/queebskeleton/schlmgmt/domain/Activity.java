package com.github.queebskeleton.schlmgmt.domain;

import java.time.LocalDateTime;

public class Activity {
	
	private int id;
	private String name;
	private LocalDateTime createdOn;
	private LocalDateTime deadline;
	private int totalScore;
	
	public Activity(String name, LocalDateTime createdOn, LocalDateTime deadline, int totalScore) {
		super();
		this.name = name;
		this.createdOn = createdOn;
		this.deadline = deadline;
		this.totalScore = totalScore;
	}

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
