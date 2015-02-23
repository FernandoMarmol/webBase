package eu.fmm.sw.servlets;

import java.io.IOException;
import java.sql.SQLException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.sql.DataSource;

import eu.fmm.sw.AjaxConstants;
import eu.fmm.sw.ContextConstants;
import eu.fmm.sw.SessionConstants;
import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractLoggedServlet;
import eu.fmm.sw.common.servlets.AbstractServlet;
import eu.fmm.sw.lang.LanguageWorker;

/**
 * Servlet implementation class LogoutServlet
 */
@WebServlet( name="LogoutServlet", displayName="Logout Servlet", urlPatterns = {"/Logout"} )
public class LogoutServlet extends AbstractLoggedServlet {

	/**
	 * @see AbstractServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		message.setMessageType(AjaxConstants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("login.message.error.general"));
		
		if(request.getSession().getAttribute(SessionConstants.SESSION_USER) != null){
			request.getSession().removeAttribute(SessionConstants.SESSION_USER);
			
			message.setMessageType(AjaxConstants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("logout.message.success"));
			
			try {
				String updateSessionLogIn = "UPDATE SESSIONS SET USER_LOGS_OUT = SYSDATE() WHERE SESSION_ID = '"+request.getSession().getId()+"'";
				((DataSource) request.getServletContext().getAttribute(ContextConstants.DATA_SOURCE)).getConnection().prepareStatement(updateSessionLogIn).executeUpdate();
				
				//invalidamos la session y creamos una nueva
				request.getSession(false).invalidate();
				request.getSession(true);
			}
			catch (SQLException e) {
				message.setMessageType(AjaxConstants.JS_AJAX_RESULT_ERROR);
				message.setDescription(LanguageWorker.getMessage("logout.message.error.general"));
			}
		}
		
		return message;
	}

	@Override
	public Message validate(HttpServletRequest request) {
		return null;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("LogoutServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("LogoutServlet - init()");
	}
	
}
