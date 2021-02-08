package com.github.queebskeleton.schlmgmt.repository.impl;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Collection;
import java.util.Iterator;
import java.util.List;

import javax.sql.DataSource;

import com.github.queebskeleton.schlmgmt.domain.Activity;
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
				Statement retrieveSubjectsStatement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
				// Execute a SQL SELECT for every subject and its instructor, and grab the ResultSet
				ResultSet subjectsResultSet = retrieveSubjectsStatement
						.executeQuery("SELECT subject.id, subject.name, subject.code, subject.instructor_id, subject.description,"
								+ "activity.id, activity.name, activity.created_on, activity.deadline, activity.total_score "
								+ "FROM subject LEFT JOIN activity ON activity.subject_id = subject.id")) {
			
			// Map each row of the ResultSet to a Subject object
			while(subjectsResultSet.next()) {
				int id = subjectsResultSet.getInt(1);
				String name = subjectsResultSet.getString(2);
				String code = subjectsResultSet.getString(3);
				String description = subjectsResultSet.getString(5);
				int instructorId = subjectsResultSet.getInt(4);
				
				List<Activity> subjectActivities = new ArrayList<>();

				// Retrieve this subject's activities
				subjectsResultSet.previous();
				while(subjectsResultSet.next() && subjectsResultSet.getInt(6) != 0) {
					Activity activity = new Activity(
						subjectsResultSet.getInt(6),
						subjectsResultSet.getString(7),
						LocalDateTime.parse(subjectsResultSet.getString(8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(subjectsResultSet.getString(9), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						subjectsResultSet.getInt(10));
					subjectActivities.add(activity);
				}
				if(!subjectActivities.isEmpty())
					subjectsResultSet.previous();
				
				subjectList.add(new Subject(id, name, code, instructorId, description, subjectActivities));
			}
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
				"SELECT subject.id, subject.name, subject.code, subject.instructor_id, subject.description,"
						+ "activity.id, activity.name, activity.created_on, activity.deadline, activity.total_score "
						+ "FROM subject LEFT JOIN activity ON activity.subject_id = subject.id WHERE subject.id IN (" + idsIterator.next());
		while(idsIterator.hasNext()) {
			retrieveSubjectsQueryBuilder.append(",");
			retrieveSubjectsQueryBuilder.append(idsIterator.next());
		}
		retrieveSubjectsQueryBuilder.append(")");

		try (
			// Grab a connection to the datasource
			Connection connection = dataSource.getConnection();
			// Create a SQL SELECT placeholder
			PreparedStatement retrieveSubjectsStatement = connection.prepareStatement(
					retrieveSubjectsQueryBuilder.toString(),
					ResultSet.TYPE_SCROLL_INSENSITIVE,
					ResultSet.CONCUR_READ_ONLY);
			// Execute a SQL SELECT for every subject, and grab the ResultSet
			ResultSet subjectsResultSet = retrieveSubjectsStatement.executeQuery()) {
			
			// Map each row of the ResultSet to a Subject object
			while(subjectsResultSet.next()) {
				int id = subjectsResultSet.getInt(1);
				String name = subjectsResultSet.getString(2);
				String code = subjectsResultSet.getString(3);
				String description = subjectsResultSet.getString(5);
				int instructorId = subjectsResultSet.getInt(4);
				
				List<Activity> subjectActivities = new ArrayList<>();

				// Retrieve this subject's activities
				subjectsResultSet.previous();
				while(subjectsResultSet.next() && subjectsResultSet.getInt(6) != 0) {
					Activity activity = new Activity(
						subjectsResultSet.getInt(6),
						subjectsResultSet.getString(7),
						LocalDateTime.parse(subjectsResultSet.getString(8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						LocalDateTime.parse(subjectsResultSet.getString(9), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						subjectsResultSet.getInt(10));
					subjectActivities.add(activity);
				}
				if(!subjectActivities.isEmpty())
					subjectsResultSet.previous();
				
				subjectList.add(new Subject(id, name, code, instructorId, description, subjectActivities));
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
			Statement retrieveSubjectStatement = connection.createStatement(ResultSet.TYPE_SCROLL_INSENSITIVE, ResultSet.CONCUR_READ_ONLY);
			// Execute a SQL SELECT for subject by their id, and grab the ResultSet
			ResultSet subjectResultSet = retrieveSubjectStatement
					.executeQuery("SELECT subject.id, subject.name, subject.code, subject.instructor_id, subject.description,"
							+ "activity.id, activity.name, activity.created_on, activity.deadline, activity.total_score "
							+ "FROM subject LEFT JOIN activity ON activity.subject_id = subject.id WHERE subject.id = " + id)) {

			// If the result set has a data row,
			// then parse that row into a subject
			if(subjectResultSet.next()) {
				String name = subjectResultSet.getString(2);
				String code = subjectResultSet.getString(3);
				String description = subjectResultSet.getString(5);
				int instructorId = subjectResultSet.getInt(4);
				
				List<Activity> subjectActivities = new ArrayList<>();
				
				// Retrieve this subject's activities
				subjectResultSet.previous();
				while(subjectResultSet.next() && subjectResultSet.getInt(6) != 0) {
					Activity activity = new Activity(
							subjectResultSet.getInt(6),
							subjectResultSet.getString(7),
						LocalDateTime.parse(subjectResultSet.getString(8), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
 						LocalDateTime.parse(subjectResultSet.getString(9), DateTimeFormatter.ofPattern("yyyy-MM-dd HH:mm:ss")),
						subjectResultSet.getInt(10));
					subjectActivities.add(activity);
				}
				if(!subjectActivities.isEmpty())
					subjectResultSet.previous();
				
				subject = new Subject(id, name, code, instructorId, description, subjectActivities);
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
						"INSERT INTO subject (name, code, instructor_id, description) VALUES (?, ?, ?, ?)", Statement.RETURN_GENERATED_KEYS)) {
				
				// Start database transaction
				connection.setAutoCommit(false);

				// Bind the subject fields to the insert statement
				insertSubjectStatement.setString(1, subject.getName());
				insertSubjectStatement.setString(2, subject.getCode());
				insertSubjectStatement.setInt(3, subject.getInstructorId());
				insertSubjectStatement.setString(4, subject.getDescription());

				// Execute the insert statement
				insertSubjectStatement.execute();
				
				// Grab the inserted generated key
				int subjectId = 0;
				try(ResultSet generatedKeyResultSet = insertSubjectStatement.getGeneratedKeys()) {
					generatedKeyResultSet.next();
					subjectId = generatedKeyResultSet.getInt(1);
				}
				
				// Insert the activities
				try(PreparedStatement insertActivitiesStatement = connection.prepareStatement(
						"INSERT INTO activity (subject_id, name, created_on, deadline, total_score) VALUES (?, ?, ?, ?, ?)")) {
					for(Activity activity : subject.getActivities()) {
						insertActivitiesStatement.setInt(1, subjectId);
						insertActivitiesStatement.setString(2, activity.getName());
						insertActivitiesStatement.setString(3, activity.getCreatedOn().toString());
						insertActivitiesStatement.setString(4, activity.getDeadline().toString());
						insertActivitiesStatement.setInt(5, activity.getTotalScore());
						insertActivitiesStatement.addBatch();
					}
					insertActivitiesStatement.executeBatch();
				}
				
				// End database transaction
				connection.commit();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}

		// Else, perform an update
		// TODO: Update or Insert activities
		else {
			// Construct the delete activities not in -- statement string
			StringBuilder deleteActivitiesStatementBuilder = new StringBuilder("DELETE FROM activity WHERE id = ? AND subject_id NOT IN (");
			if(!subject.getActivities().isEmpty()) {
				Iterator<Activity> subjectActivitiesIterator = subject.getActivities().iterator();
				deleteActivitiesStatementBuilder.append(subjectActivitiesIterator.next().getId());
				while(subjectActivitiesIterator.hasNext()) {
					deleteActivitiesStatementBuilder.append(",");
					deleteActivitiesStatementBuilder.append(subjectActivitiesIterator.next().getId());
				}
			}
			deleteActivitiesStatementBuilder.append(")");
			
			try (
				// Grab a connection to the datasource
				Connection connection = dataSource.getConnection();
				// Create a SQL UPDATE placeholder
				PreparedStatement updateSubjectStatement = connection.prepareStatement(
						"UPDATE subject SET name = ?, code = ?, instructor_id = ?, description = ? WHERE id = ?");
				// Create a SQL DELETE placeholder for deleting activities not present in the aggregate
				PreparedStatement deleteActivitiesStatement = connection.prepareStatement(deleteActivitiesStatementBuilder.toString());
				// Create a SQL INSERT/UPDATE placeholder for updating the activities of this aggregate
				PreparedStatement updateActivitiesStatement = connection.prepareStatement(
						"INSERT INTO activity (id, subject_id, name, created_on, deadline, total_score) "
						+ "VALUES (?, ?, ?, ?, ?, ?) ON DUPLICATE KEY "
						+ "UPDATE name = VALUES(name), created_on = VALUES(created_on), deadline = VALUES(deadline), total_score = VALUES(total_score)")) {

				// Begin Database Transaction
				connection.setAutoCommit(false);
				
				// Bind the subject fields to the update statement
				updateSubjectStatement.setString(1, subject.getName());
				updateSubjectStatement.setString(2, subject.getCode());
				updateSubjectStatement.setInt(3, subject.getInstructorId());
				updateSubjectStatement.setString(4, subject.getDescription());
				updateSubjectStatement.setInt(5, subject.getId());

				// Execute the update statement
				updateSubjectStatement.execute();
				
				// Delete the activities with ids not present in the subject
				deleteActivitiesStatement.setInt(1, subject.getId());
				deleteActivitiesStatement.execute();
				
				// Insert/Update the new activities
				for(Activity activity : subject.getActivities()) {
					updateActivitiesStatement.setInt(1, activity.getId());
					updateActivitiesStatement.setInt(2, subject.getId());
					updateActivitiesStatement.setString(3, activity.getName());
					updateActivitiesStatement.setString(4, activity.getCreatedOn().toString());
					updateActivitiesStatement.setString(5, activity.getDeadline().toString());
					updateActivitiesStatement.setInt(6, activity.getTotalScore());
					updateActivitiesStatement.addBatch();
				}
				updateActivitiesStatement.executeBatch();
				
				// Commit the Transaction
				connection.commit();
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
