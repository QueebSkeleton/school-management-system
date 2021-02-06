package com.github.queebskeleton.schlmgmt.servlet.admin.instructormgmt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.queebskeleton.schlmgmt.domain.Instructor;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * Servlet that saves an instructor from the request.
 * Parses an instructor object then saves it to the repository.
 * 
 * @author Rian Carlo Reyes
 *
 */
@WebServlet(urlPatterns = {"/admin/instructors/save"})
public class SaveInstructorServlet extends HttpServlet {
	
	private static final long serialVersionUID = 1L;
	
	// Instructor Repository dependency
	private Repository<Instructor, Integer> instructorRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		// Grab the instructor repository object from the Servlet Context then inject it.
		instructorRepository = (Repository<Instructor, Integer>)
				getServletContext().getAttribute("instructorRepository");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the id from the request parameters
		String paramId = request.getParameter("id");
		
		// Placeholder for an instructor object
		Instructor instructor = null;
		
		// If the id is null or empty, construct a new Instructor
		if(paramId == null || paramId.contentEquals(""))
			instructor =
					new Instructor(
							request.getParameter("name"),
							request.getParameter("emailAddress"),
							request.getParameter("password"));
		
		// If the id is not null, attempt to retrieve its associated instructor from the repository,
		// then update its details
		else {
			instructor = instructorRepository.getById(
					Integer.parseInt(request.getParameter("id")));
			instructor.setName(request.getParameter("name"));
			instructor.setEmailAddress(request.getParameter("emailAddress"));
			instructor.setPassword(request.getParameter("password"));
		}
		
		// Save the instructor object
		instructorRepository.save(instructor);
		
		// TODO: Handle error cases
		
		// Redirect to instructors panel
		response.sendRedirect(request.getContextPath() + "/admin/instructors/panel.jsp");
		
	}

}
