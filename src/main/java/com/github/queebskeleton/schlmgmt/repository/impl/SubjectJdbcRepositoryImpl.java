package com.github.queebskeleton.schlmgmt.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.github.queebskeleton.schlmgmt.domain.Subject;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * JDBC Implementation of a Subject Repository.<br>
 * <br>
 * 
 * Maps a row of a certain `subject` table to a Subject domain object.
 * 
 * @author Rian Carlo Reyes
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
						.executeQuery("SELECT id, name, code, description, instructor_id FROM subject")) {

			// Map each row of the ResultSet to a Subject object
			while (subjectsResultSet.next())
				subjectList.add(new Subject(
						subjectsResultSet.getInt(1),
						subjectsResultSet.getString(2),
						subjectsResultSet.getString(3),
						subjectsResultSet.getInt(5),
						subjectsResultSet.getString(4)));
		} catch (SQLException e) {
			// TODO: Propagate a proper exception for the Servlets
			e.printStackTrace();
		}

		// Return the final list
		return subjectList;
	}

	/**
	 * @see com.github.queebskeleton.schlmgmt.repository.Repository#getAll(Object)
	 */
	@Override
	public List<Subject> getAll(Collection<Integer> ids) {
		// Check if the given collection is null
		if(ids == null || ids.size() == 0)
			throw new IllegalArgumentException("Invalid id collection given.");
		
		// ArrayList of subjects
		List<Subject> subjectList = new ArrayList<>();
		
		// Construct the string query
		Iterator<Integer> idsIterator = ids.iterator();
		StringBuilder retrieveSubjectsQueryBuilder = new StringBuilder(
				"SELECT id, name, code, description, instructor_id FROM subject WHERE id IN (" + idsIterator.next());
		while(idsIterator.hasNext()) {
			retrieveSubjectsQueryBuilder.append(",");
			retrieveSubjectsQueryBuilder.append(idsIterator.next());
		}
		retrieveSubjectsQueryBuilder.append(")");

		try (
			// Grab a connection to the datasource
			Connection connection = dataSource.getConnection();
			// Create a SQL SELECT placeholder
			PreparedStatement retrieveSubjectsStatement = connection.prepareStatement(retrieveSubjectsQueryBuilder.toString());
			// Execute a SQL SELECT for every subject, and grab the ResultSet
			ResultSet subjectsResultSet = retrieveSubjectsStatement.executeQuery()) {

			// Map each row of the ResultSet to a Subject object
			while (subjectsResultSet.next())
				subjectList.add(new Subject(
						subjectsResultSet.getInt(1),
						subjectsResultSet.getString(2),
						subjectsResultSet.getString(3),
						subjectsResultSet.getInt(5),
						subjectsResultSet.getString(4)));
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
						.executeQuery("SELECT id, name, code, description, instructor_id FROM subject WHERE id = " + id)) {

			// If the result set has a data row,
			// then parse that row into a subject
			if (subjectResultSet.next()) {
				// Parse the subject
				subject = new Subject(
						subjectResultSet.getInt(1),
						subjectResultSet.getString(2),
						subjectResultSet.getString(3),
						subjectResultSet.getInt(5),
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
				insertSubjectStatement.setInt(3, subject.getInstructorId());
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
				updateSubjectStatement.setInt(3, subject.getInstructorId());
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
