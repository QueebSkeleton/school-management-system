package com.github.queebskeleton.schlmgmt.servlet.admin.subjectmgmt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.queebskeleton.schlmgmt.domain.Subject;
import com.github.queebskeleton.schlmgmt.repository.Repository;

/**
 * Servlet that deletes a subject from the repository.
 * Retrieves the id of the subject to be deleted from
 * the request.
 * 
 * @author Rian Carlo Reyes
 *
 */
@WebServlet(urlPatterns = {"/admin/subjects/delete"})
public class DeleteSubjectServlet extends HttpServlet {
	
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
	protected void doGet(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {

		// Get the id from the request parameters
		String paramId = request.getParameter("id");
		
		// Parse the id from the request as an int
		int id = Integer.parseInt(paramId);
		
		// Delete the subject with the specified id
		subjectRepository.deleteById(id);
		
		// TODO: Handle error cases
		
		// Redirect to subjects panel
		response.sendRedirect(request.getContextPath() + "/admin/subjects/panel.jsp");
	}
	

}
