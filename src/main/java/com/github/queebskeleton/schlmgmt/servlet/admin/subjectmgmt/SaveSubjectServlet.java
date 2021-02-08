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
 * Servlet that saves an subject from the request.
 * Parses an subject object then saves it to the repository.
 * 
 * @author Rian Carlo Reyes
 *
 */
@WebServlet(urlPatterns = {"/admin/subjects/save"})
public class SaveSubjectServlet extends HttpServlet {
	
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
		
		// Get the id from the request parameters
		String paramId = request.getParameter("id");
		
		// Placeholder for a subject object
		Subject subject = null;
		
		// If the id is null or empty, construct a new Subject
		if(paramId == null || paramId.contentEquals(""))
			subject =
					new Subject(
							request.getParameter("name"),
							request.getParameter("code"),
							Integer.parseInt(request.getParameter("instructor.id")),
							request.getParameter("description"));
		
		// If the id is not null, attempt to retrieve its associated subject from the repository,
		// then update its details
		else {
			subject = subjectRepository.getById(
					Integer.parseInt(request.getParameter("id")));
			subject.setName(request.getParameter("name"));
			subject.setCode(request.getParameter("code"));
			subject.setInstructorId(Integer.parseInt(request.getParameter("instructor.id")));
			subject.setDescription(request.getParameter("description"));
		}
		
		// Save the subject object
		subjectRepository.save(subject);
		
		// TODO: Handle error cases
		
		// Redirect to subjects panel
		response.sendRedirect(request.getContextPath() + "/admin/subjects/panel.jsp");
		
	}

}
