package eu.fmm.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.fmm.sw.beans.User;
import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractLoggedServlet;
import eu.fmm.sw.common.servlets.AbstractServlet;
import eu.fmm.sw.workers.SessionWorker;

/**
 * Servlet implementation class BranchRegistrationServlet
 */
@WebServlet( name="MainUserPageServlet", displayName="MainUserPage Servlet", urlPatterns = {"/MainUserPage"} )
public class MainUserPageServlet extends AbstractLoggedServlet {
	
	/**
	 * @see AbstractServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		User user = SessionWorker.getUser(request.getSession());
		
		Message message = new Message();
		message.setNextToDo("/MainUserPageLayout");
		
		return message;
	}
	
	@Override
	public Message validate(HttpServletRequest request) {
		return null;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("MainUserPageServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("MainUserPageServlet - init()");
	}
	
}
