package com.github.queebskeleton.schlmgmt.domain;

public class Subject {
	
	private int id;
	private String name;
	private String code;
	private Instructor instructor;
	private String description;
	
	public Subject(int id, String name, String code, Instructor instructor, String description) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.instructor = instructor;
		this.description = description;
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

	public String getCode() {
		return code;
	}

	public void setCode(String code) {
		this.code = code;
	}

	public Instructor getInstructor() {
		return instructor;
	}

	public void setInstructor(Instructor instructor) {
		this.instructor = instructor;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
