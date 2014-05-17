package eu.fmm.sw.listeners;

import javax.annotation.Resource;
import javax.servlet.ServletContext;
import javax.servlet.ServletContextEvent;
import javax.servlet.ServletContextListener;
import javax.servlet.annotation.WebListener;
import javax.sql.DataSource;

import eu.fmm.sw.Constants;

@WebListener
public class CustomServletContextListener implements ServletContextListener {

	@Resource(name="jdbc/base")
	private DataSource ds;
	
	@Override
	public void contextDestroyed(ServletContextEvent servletContextEvent) {
		System.out.println("CustomServletContextListener - contextDestroyed");
	}

	@Override
	public void contextInitialized(ServletContextEvent servletContextEvent) {
		System.out.println("CustomServletContextListener - contextInitialized");
		
		try{
			ServletContext servletContext = servletContextEvent.getServletContext();
			servletContext.setAttribute(Constants.DATA_SOURCE, ds);
			
			//Language parameter
			String lp = servletContext.getInitParameter(Constants.LANG_PACKAGE);
			servletContext.setAttribute(Constants.LANG_PACKAGE, lp);
		}
		catch(Exception e){
			System.out.println("CustomServletContextListener - contextInitialized - ERROR DATASOURCE");
		}
	}

}
