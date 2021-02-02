package com.github.queebskeleton.schlmgmt.listener;

import javax.naming.InitialContext;
import javax.naming.NamingException;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import com.github.queebskeleton.schlmgmt.domain.Instructor;
import com.github.queebskeleton.schlmgmt.repository.Repository;
import com.github.queebskeleton.schlmgmt.repository.impl.InstructorJdbcRepositoryImpl;

/**
 * Startup Listener class; is a makeshift dependency injector.
 * Uses the ServletContext to store main application objects,
 * which is eventually wired together.
 * 
 * TODO: Remove this, replace with an actual CDI Framework.
 * 
 * @author queebskeleton
 *
 */
@WebListener
public class StartupListener implements ServletContextListener {

	/**
     * @see ServletContextListener#contextInitialized(ServletContextEvent)
     */
    @Override
    public void contextInitialized(ServletContextEvent sce) {
    	
    	// Get the ServletContext
    	ServletContext servletContext = sce.getServletContext();
    	
    	try {
    		// Grab the DataSource from Tomcat JNDI.
    		// The DataSource is defined as a JNDI Resource in the application's context.xml file.
			DataSource dataSource = (DataSource) new InitialContext().lookup("java:/comp/env/jdbc/school_system_db");
			
			// Construct an Instructor Repository object with the retrieved datasource.
			Repository<Instructor, Integer> instructorRepository = new InstructorJdbcRepositoryImpl(dataSource);
			servletContext.setAttribute("instructorRepository", instructorRepository);
		} catch (NamingException e) {
			e.printStackTrace();
		}
    	
    }
	
}
