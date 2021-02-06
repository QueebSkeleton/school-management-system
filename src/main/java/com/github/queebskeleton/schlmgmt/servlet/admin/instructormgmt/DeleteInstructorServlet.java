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
 * Servlet that deletes an instructor from the repository.
 * Retrieves the id of the instructor to be deleted from
 * the request.
 * 
 * @author Rian Carlo Reyes
 *
 */
@WebServlet(urlPatterns = {"/admin/instructors/delete"})
public class DeleteInstructorServlet extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id from the request parameters
		String paramId = request.getParameter("id");
		
		// Parse the id from the request as an int
		int id = Integer.parseInt(paramId);
		
		// Delete the instructor with the specified id
		instructorRepository.deleteById(id);
		
		// TODO: Handle error cases
		
		// Redirect to instructors panel
		response.sendRedirect(request.getContextPath() + "/admin/instructors/panel.jsp");
	}
	

}
