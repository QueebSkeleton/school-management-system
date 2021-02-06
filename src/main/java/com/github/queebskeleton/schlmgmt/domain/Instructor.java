package com.github.queebskeleton.schlmgmt.domain;

/**
 * Domain class for an Instructor.
 * 
 * @author queebskeleton
 *
 */
public class Instructor {
	
	private int id;					// Database ID
	private String name;			// Instructor Name
	private String emailAddress;	// Instructor Email Address
	private String password;		// Instructor Password
	
	/**
	 * Constructs a new Instructor.
	 * 
	 * @param name name of the instructor
	 * @param emailAddress email address of the instructor
	 * @param password password of the instructor for authentication
	 */
	public Instructor(String name, String emailAddress, String password) {
		super();
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = password;
	}
	
	/**
	 * Constructs a new Instructor with pre-initialized data.
	 * Used by JDBC when fetching already persisted Instructors.
	 * 
	 * @param id the database id 
	 * @param name name of the instructor
	 * @param emailAddress email address of the instructor
	 * @param password password of the instructor for authentication
	 */
	public Instructor(int id, String name, String emailAddress, String password) {
		super();
		this.id = id;
		this.name = name;
		this.emailAddress = emailAddress;
		this.password = password;
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

	public String getEmailAddress() {
		return emailAddress;
	}

	public void setEmailAddress(String emailAddress) {
		this.emailAddress = emailAddress;
	}

	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}

}
