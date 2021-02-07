package com.github.queebskeleton.schlmgmt.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javax.sql.DataSource;

import com.github.queebskeleton.schlmgmt.domain.Instructor;
import com.github.queebskeleton.schlmgmt.domain.Subject;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * JDBC Implementation of an Instructor Repository.<br>
 * <br>
 * 
 * Maps a row of a certain `instructor` table to an Instructor domain object.
 * 
 * @author queenskeleton
 *
 */
public class SubjectJdbcRepositoryImpl implements Repository<Subject, Integer> {

	private final DataSource dataSource; // The datasource that this repository interacts with

	/**
	 * Constructs the repository with a specified datasource.
	 * 
	 * @param dataSource the datasource that this repository interacts with
	 */
	public SubjectJdbcRepositoryImpl(DataSource dataSource) {
		this.dataSource = dataSource;
	}

	/**
	 * @see com.github.queebskeleton.schlmgmt.repository.Repository#getAll()
	 */
	@Override
	public List<Subject> getAll() {
		// ArrayList of subjects
		List<Subject> subjectList = new ArrayList<>();

		try (
				// Grab a connection to the datasource
				Connection connection = dataSource.getConnection();
				// Create a SQL SELECT placeholder
				Statement retrieveSubjectsStatement = connection.createStatement();
				// Execute a SQL SELECT for every subject and its instructor, and grab the ResultSet
				ResultSet subjectsResultSet = retrieveSubjectsStatement
						.executeQuery("SELECT subject.id, subject.name, subject.code, subject.description, instructor.id, "
								+ "instructor.name, instructor.email_address, instructor.password "
								+ "FROM subject LEFT OUTER JOIN instructor ON instructor.id = subject.instructor_id")) {
			
			Map<Integer, Instructor> instructorMap = new HashMap<>();

			// Map each row of the ResultSet to a Subject object
			while (subjectsResultSet.next()) {
				Instructor subjectInstructor = null;
				
				// If this subject row has an instructor id, proceed retrieving the instructor
				if(subjectsResultSet.getInt(5) != 0) {
					// Grab the instructor
					if(instructorMap.containsKey(subjectsResultSet.getInt(5)))
						subjectInstructor = instructorMap.get(subjectsResultSet.getInt(5));
					else {
						subjectInstructor = new Instructor(
								subjectsResultSet.getInt(5),
								subjectsResultSet.getString(6),
								subjectsResultSet.getString(7),
								subjectsResultSet.getString(8));
						instructorMap.put(subjectInstructor.getId(), subjectInstructor);
					}
				}
				
				// Grab the subject
				Subject subject = new Subject(
						subjectsResultSet.getInt(1),
						subjectsResultSet.getString(2),
						subjectsResultSet.getString(3),
						subjectInstructor,
						subjectsResultSet.getString(4));
				subjectList.add(subject);
			}
		} catch (SQLException e) {
			// TODO: Propagate a proper exception for the Servlets
			e.printStackTrace();
		}

		// Return the final list
		return subjectList;
	}

	/**
	 * @see com.github.queebskeleton.schlmgmt.repository.Repository#getById(Object)
	 */
	@Override
	public Subject getById(Integer id) {
		// Placeholder for the retrieved instructor
		Subject subject = null;

		try (
				// Grab a connection to the datasource
				Connection connection = dataSource.getConnection();
				// Create a SQL SELECT placeholder
				Statement retrieveSubjectStatement = connection.createStatement();
				// Execute a SQL SELECT for subject by their id, and grab the ResultSet
				ResultSet subjectResultSet = retrieveSubjectStatement
						.executeQuery("SELECT subject.id, subject.name, subject.code, subject.description, instructor.id, "
								+ "instructor.name, instructor.email_address, instructor.password "
								+ "FROM subject LEFT OUTER JOIN instructor ON instructor.id = subject.instructor_id "
								+ "WHERE subject.id = " + id)) {

			// If the result set has a data row,
			// then parse that row into a subject
			if (subjectResultSet.next()) {
				
				// Retrieve the instructor if the id exists
				Instructor subjectInstructor = null;
				if(subjectResultSet.getInt(5) != 0)
					subjectInstructor = new Instructor(
							subjectResultSet.getInt(5),
							subjectResultSet.getString(6),
							subjectResultSet.getString(7),
							subjectResultSet.getString(8));
				
				// Parse the subject
				subject = new Subject(
						subjectResultSet.getInt(1),
						subjectResultSet.getString(2),
						subjectResultSet.getString(3),
						subjectInstructor,
						subjectResultSet.getString(4));
			}

			// If the result set has no data rows,
			// throw an illegal argument exception.
			else
				throw new IllegalArgumentException("Invalid identifier provided.");
		} catch (SQLException e) {
			e.printStackTrace();
		}

		// Return the parsed subject
		return subject;
	}

	@Override
	public void save(Subject subject) {
		// If the given instructor has no assigned id yet,
		// perform an insert
		if (subject.getId() == 0) {
			try (
				// Grab a connection to the datasource
				Connection connection = dataSource.getConnection();
				// Create a SQL INSERT placeholder
				PreparedStatement insertSubjectStatement = connection.prepareStatement(
						"INSERT INTO subject (name, code, instructor_id, description) VALUES (?, ?, ?, ?)")) {

				// Bind the subject fields to the insert statement
				insertSubjectStatement.setString(1, subject.getName());
				insertSubjectStatement.setString(2, subject.getCode());
				insertSubjectStatement.setInt(3, subject.getInstructor().getId());
				insertSubjectStatement.setString(4, subject.getDescription());

				// Execute the insert statement
				insertSubjectStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Else, perform an update
		else {
			try (
				// Grab a connection to the datasource
				Connection connection = dataSource.getConnection();
				// Create a SQL UPDATE placeholder
				PreparedStatement updateSubjectStatement = connection.prepareStatement(
						"UPDATE subject SET name = ?, code = ?, instructor_id = ?, description = ? WHERE id = ?")) {

				// Bind the subject fields to the update statement
				updateSubjectStatement.setString(1, subject.getName());
				updateSubjectStatement.setString(2, subject.getCode());
				updateSubjectStatement.setInt(3, subject.getInstructor().getId());
				updateSubjectStatement.setString(4, subject.getDescription());
				updateSubjectStatement.setInt(5, subject.getId());

				// Execute the update statement
				updateSubjectStatement.execute();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
	}

	@Override
	public void deleteById(Integer id) {
		try (
			// Grab a connection to the datasource
			Connection connection = dataSource.getConnection();
			// Create a SQL DELETE placeholder
			PreparedStatement deleteSubjectStatement = connection.prepareStatement(
					"DELETE FROM subject WHERE id = ?")) {

			// Bind the given id to the delete statement
			deleteSubjectStatement.setInt(1, id);

			// Execute the delete statement
			deleteSubjectStatement.execute();
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

}
