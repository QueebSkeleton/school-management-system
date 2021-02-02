package com.github.queebskeleton.schlmgmt.repository.impl;

import java.sql.Connection;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import javax.sql.DataSource;

import com.github.queebskeleton.schlmgmt.domain.Instructor;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * JDBC Implementation of an Instructor Repository.<br><br>
 * 
 * Maps a row of a certain `instructor` table to an Instructor domain object.
 * 
 * @author queenskeleton
 *
 */
public class InstructorJdbcRepositoryImpl implements Repository<Instructor, Integer> {
	
	private final DataSource dataSource;	// The datasource that this repository interacts with
	
	/**
	 * Constructs the repository with a specified datasource.
	 * 
	 * @param dataSource the datasource that this repository interacts with
	 */
	public InstructorJdbcRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @see com.github.queebskeleton.schlmgmt.repository.Repository#getAll()
	 */
	@Override
	public List<Instructor> getAll() {
		// ArrayList of instructors
		List<Instructor> instructorList = new ArrayList<>();
		
		try(
			// Grab a connection to the datasource
			Connection connection = dataSource.getConnection();
			// Create a SQL SELECT placeholder
			Statement retrieveInstructorsStatement = connection.createStatement();
			// Execute a SQL SELECT for every instructor, and grab the ResultSet
			ResultSet instructorsResultSet =
					retrieveInstructorsStatement.executeQuery(
							"SELECT id, name, email_address, password FROM instructor")) {
			
			// Map each row of the ResultSet to an Instructor object
			while(instructorsResultSet.next())
				instructorList.add(new Instructor(
						instructorsResultSet.getInt(1),
						instructorsResultSet.getString(2),
						instructorsResultSet.getString(3),
						instructorsResultSet.getString(4)));
		} catch(SQLException e) {
			// TODO: Propagate a proper exception for the Servlets
			e.printStackTrace();
		}
		
		// Return the final list
		return instructorList;
	}


	/**
	 * @see com.github.queebskeleton.schlmgmt.repository.Repository#getById(Object)
	 */
	@Override
	public Instructor getById(Integer id) {
		// Placeholder for the retrieved instructor
		Instructor instructor = null;
		
		try(
			// Grab a connection to the datasource
			Connection connection = dataSource.getConnection();
			// Create a SQL SELECT placeholder
			Statement retrieveInstructorStatement = connection.createStatement();
			// Execute a SQL SELECT for instructors by their id, and grab the ResultSet
			ResultSet instructorResultSet =
					retrieveInstructorStatement.executeQuery(
							"SELECT id, name, email_address, password FROM instructor WHERE id = " + id)) {
			
			// If the result set has a data row,
			// then parse that row into an instructor
			if(instructorResultSet.next())
				instructor = new Instructor(
						instructorResultSet.getInt(1),
						instructorResultSet.getString(2),
						instructorResultSet.getString(3),
						instructorResultSet.getString(4));
			
			// If the result set has no data rows,
			// throw an illegal argument exception.
			else
				throw new IllegalArgumentException("Invalid identifier provided.");
		} catch(SQLException e) {
			e.printStackTrace();
		}
		
		// Return the parsed instructor
		return instructor;
	}

	@Override
	public void save(Instructor instructor) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

	@Override
	public void deleteById(Integer id) {
		throw new UnsupportedOperationException("Not implemented yet.");
	}

}
