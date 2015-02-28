package eu.fmm.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.validator.routines.EmailValidator;

import eu.fmm.sw.AjaxConstants;
import eu.fmm.sw.beans.User;
import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractServlet;
import eu.fmm.sw.ddbb.managers.DBSessionManager;
import eu.fmm.sw.ddbb.managers.DBUserManager;
import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.workers.SessionWorker;

/**
 * Servlet implementation class LoginServlet
 */
@WebServlet( name="LoginServlet", displayName="Login Servlet", urlPatterns = {"/Login"} )
public class LoginServlet extends AbstractServlet {
	
	/**
	 * @see AbstractServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		
		message.setMessageType(AjaxConstants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("login.message.error.general"));
		
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		User user = DBUserManager.getInstance(getServletContext()).getUser(email, pwd);
		if(!user.isAnonymous()){
			message.setMessageType(AjaxConstants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("login.message.success"));
			
			SessionWorker.setUser(request.getSession(), user);
			
			message.setNextToDo("InitServlet");
			
			DBSessionManager.getInstance(getServletContext()).associateUserToSession(user.getId(), request.getSession().getId());
		}
		else{
			message.setMessageType(AjaxConstants.JS_AJAX_RESULT_ERROR);
			message.setDescription(LanguageWorker.getMessage("login.message.error"));
			message.setRelatedField("loginEmail");
		}
		
		return message;
	}

	@Override
	public Message validate(HttpServletRequest request) {
		Message message = null;
		
		try{
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			
			if(email.equalsIgnoreCase("") || pwd.equalsIgnoreCase("")){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("login.message.emptydata"));
				message.setMessageType(AjaxConstants.JS_AJAX_RESULT_INFO);
				message.setRelatedField("loginEmail");
			}
			
			if(message == null && !EmailValidator.getInstance().isValid(email)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("login.message.error.email"));
				message.setMessageType(AjaxConstants.JS_AJAX_RESULT_ERROR);
				message.setRelatedField("loginEmail");
			}
		}
		catch(Exception e){}
		
		return message;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("LoginServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("LoginServlet - init()");
	}

}
