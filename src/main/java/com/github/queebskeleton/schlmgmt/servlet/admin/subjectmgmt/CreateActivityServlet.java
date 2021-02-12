package com.github.queebskeleton.schlmgmt.servlet.admin.subjectmgmt;

import java.io.IOException;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.queebskeleton.schlmgmt.domain.Activity;
import com.github.queebskeleton.schlmgmt.domain.Subject;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * Servlet that creates a new activity for the specified subject,
 * from the request.
 * 
 * @author Rian Carlo Reyes
 *
 */
@WebServlet(urlPatterns = {"/admin/subjects/activity_create"})
public class CreateActivityServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;

	// Subject Repository dependency
	private Repository<Subject, Integer> subjectRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		// Grab the subject repository object from the Servlet Context then inject it.
		subjectRepository = (Repository<Subject, Integer>)
				getServletContext().getAttribute("subjectRepository");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// TODO: Add Validation Layer + evaluation of subjectId retrieved
		
		// Grab subjectId parameter from request
		int subjectId = Integer.parseInt(request.getParameter("subject.id"));
		
		// Retrieve the associated subject
		Subject subject = subjectRepository.getById(subjectId);
		
		// Add a new activity parsed from the request, to the subject
		subject.addActivity(
				new Activity(
						request.getParameter("name"),
						LocalDateTime.parse(request.getParameter("deadline"), DateTimeFormatter.ISO_DATE_TIME),
						Integer.parseInt(request.getParameter("totalScore"))));
		
		// Save the subject
		subjectRepository.save(subject);
		
		// Redirect to subject view
		response.sendRedirect(request.getContextPath() + "/admin/subjects/view.jsp?id=" + subjectId);
		
	}

}
