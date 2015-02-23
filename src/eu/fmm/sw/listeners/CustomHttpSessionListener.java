package eu.fmm.sw.listeners;

import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpSessionEvent;
import javax.servlet.http.HttpSessionListener;

import eu.fmm.sw.ddbb.managers.DBSessionManager;

@WebListener
public class CustomHttpSessionListener implements HttpSessionListener {

	@Override
	public void sessionCreated(HttpSessionEvent sessionEvent) {
		System.out.println("CustomHttpSessionListener - sessionCreated");
		
		if(sessionEvent.getSession().isNew()){
			DBSessionManager.getInstance(sessionEvent.getSession().getServletContext()).saveSession(sessionEvent.getSession().getId());
			System.out.println("CustomHttpSessionListener - sessionCreated - inserted");
		}
	}

	@Override
	public void sessionDestroyed(HttpSessionEvent sessionEvent) {
		System.out.println("CustomHttpSessionListener - sessionDestroyed");
		
		DBSessionManager.getInstance(sessionEvent.getSession().getServletContext()).updateEndSessionTime(sessionEvent.getSession().getId());
		System.out.println("CustomHttpSessionListener - sessionDestroyed - updated");
	}

}
