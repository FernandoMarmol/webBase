package eu.fmm.sw.listeners;

import javax.servlet.ServletRequestEvent;
import javax.servlet.ServletRequestListener;
import javax.servlet.annotation.WebListener;
import javax.servlet.http.HttpServletRequest;

import eu.fmm.sw.ddbb.managers.DBSessionManager;

@WebListener
public class CustomServletRequestListener implements ServletRequestListener {

	@Override
	public void requestInitialized(ServletRequestEvent requestEvent) {
		System.out.println("CustomServletRequestListener - requestInitialized");
		
		/*Si la sesión es nueva guardamos ciertos datos en la BBDD*/
		if(((HttpServletRequest) requestEvent.getServletRequest()).getSession().isNew()){
			DBSessionManager.getInstance(requestEvent.getServletContext()).associateUserAgentToSession(((HttpServletRequest) requestEvent.getServletRequest()).getSession().getId());
			System.out.println("CustomServletRequestListener - Actualizado User Agent");
		}
	}
	
	@Override
	public void requestDestroyed(ServletRequestEvent requestEvent) {
		System.out.println("CustomServletRequestListener - requestDestroyed");
	}
}
