package com.github.queebskeleton.schlmgmt.domain;

/**
 * Domain class for a Subject.
 * 
 * @author Rian Carlo Reyes
 *
 */
public class Subject {
	
	// Database Id
	private int id;
	// Subject Name
	private String name;
	// Subject Code
	private String code;
	// Associated Instructor
	private int instructorId;
	// Description of the Subject
	private String description;
	
	/**
	 * Constructs a fresh Subject with the given name, code and description.
	 * 
	 * @param name name of the subject
	 * @param code code of the subject
	 * @param description description of the subject
	 */
	public Subject(String name, String code, int instructorId, String description) {
		super();
		this.name = name;
		this.code = code;
		this.instructorId = instructorId;
		this.description = description;
	}
	
	/**
	 * Constructs a new Subject with pre-initialized data.
	 * Used by JDBC when fetching already persisted Subjects.
	 * 
	 * @param id database id
	 * @param name name of the subject
	 * @param code code of the subject
	 * @param instructor the associated instructor
	 * @param description description of the subject
	 */
	public Subject(int id, String name, String code, int instructorId, String description) {
		super();
		this.id = id;
		this.name = name;
		this.code = code;
		this.instructorId = instructorId;
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

	public int getInstructorId() {
		return instructorId;
	}

	public void setInstructorId(int instructorId) {
		this.instructorId = instructorId;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

}
