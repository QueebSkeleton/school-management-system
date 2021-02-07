package com.github.queebskeleton.schlmgmt.servlet.admin.subjectmgmt;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.github.queebskeleton.schlmgmt.domain.Instructor;
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
	// Instructor Repository dependency
	private Repository<Instructor, Integer> instructorRepository;
	
	@SuppressWarnings("unchecked")
	@Override
	public void init() {
		// Grab the instructor repository object from the Servlet Context then inject it.
		instructorRepository = (Repository<Instructor, Integer>)
				getServletContext().getAttribute("instructorRepository");
		// Grab the subject repository object from the Servlet Context then inject it.
		subjectRepository = (Repository<Subject, Integer>)
				getServletContext().getAttribute("subjectRepository");
	}

	@Override
	protected void doPost(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		// Get the id from the request parameters
		String paramId = request.getParameter("id");
		String paramInstructorId = request.getParameter("instructor.id");
		
		// Placeholder for a subject object
		Subject subject = null;
		// Placeholder for the instructor object
		Instructor subjectInstructor = null;
		
		// If the instructor id is not null or empty, retrieve the associated instructor from the repository
		if(paramInstructorId != null && !paramInstructorId.contentEquals(""))
			// TODO: Handle error for invalid instructor id
			subjectInstructor = instructorRepository.getById(Integer.parseInt(paramInstructorId));
			
		
		// If the id is null or empty, construct a new Subject
		if(paramId == null || paramId.contentEquals(""))
			subject =
					new Subject(
							request.getParameter("name"),
							request.getParameter("code"),
							request.getParameter("description"));
		
		// If the id is not null, attempt to retrieve its associated subject from the repository,
		// then update its details
		else {
			subject = subjectRepository.getById(
					Integer.parseInt(request.getParameter("id")));
			subject.setName(request.getParameter("name"));
			subject.setCode(request.getParameter("code"));
			subject.setDescription(request.getParameter("description"));
		}
		
		subject.setInstructor(subjectInstructor);
		
		// Save the subject object
		subjectRepository.save(subject);
		
		// TODO: Handle error cases
		
		// Redirect to subjects panel
		response.sendRedirect(request.getContextPath() + "/admin/subjects/panel.jsp");
		
	}

}
