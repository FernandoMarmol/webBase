package eu.fmm.sw.workers;

import javax.servlet.http.HttpSession;

import eu.fmm.sw.SessionConstants;
import eu.fmm.sw.beans.User;

public class SessionWorker {
	
	public static User getUser(HttpSession session){
		User user = (User) session.getAttribute(SessionConstants.SESSION_USER);
		if(user == null)
			user = new User();
		
		return user;
	}
	
	public static void setUser(HttpSession session, User user){
		session.setAttribute(SessionConstants.SESSION_USER, user);
	} 

}
