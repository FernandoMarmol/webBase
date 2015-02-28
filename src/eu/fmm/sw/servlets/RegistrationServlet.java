package eu.fmm.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.apache.commons.lang3.StringUtils;
import org.apache.commons.validator.routines.EmailValidator;

import eu.fmm.sw.AjaxConstants;
import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractServlet;
import eu.fmm.sw.ddbb.managers.DBUserManager;
import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.workers.SessionWorker;

/**
 * Servlet implementation class RegistrationServlet
 */
@WebServlet( name="RegistrationServlet", displayName="Registration Servlet", urlPatterns = {"/Registration"} )
public class RegistrationServlet extends AbstractServlet {

	/**
	 * @see AbstractServlet#customExecute(String method, HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		Message message = new Message();
		
		message.setMessageType(AjaxConstants.JS_AJAX_RESULT_INFO);
		message.setDescription(LanguageWorker.getMessage("registration.message.error.general"));
		
		String name = request.getParameter("name");
		String alias = request.getParameter("alias");
		String email = request.getParameter("email");
		String pwd = request.getParameter("pwd");
		
		boolean registered = DBUserManager.getInstance(getServletContext()).registerUser(name, alias, email, pwd);
		
		if(registered){
			
			SessionWorker.setUser(request.getSession(), DBUserManager.getInstance(getServletContext()).getUser(email, pwd));
			
			message.setMessageType(AjaxConstants.JS_AJAX_RESULT_SUCCESS);
			message.setDescription(LanguageWorker.getMessage("registration.message.success"));
			
			message.setNextToDo("MainUserPage");
		}
		else{
			message.setMessageType(AjaxConstants.JS_AJAX_RESULT_ERROR);
			message.setError(true);
			message.setDescription(LanguageWorker.getMessage("registration.message.error"));
		}
		
		return message;
	}
	
	
	@Override
	public Message validate(HttpServletRequest request) {
		
		Message message = null;
		
		try{
			String alias = request.getParameter("alias");
			String name = request.getParameter("name");
			String email = request.getParameter("email");
			String pwd = request.getParameter("pwd");
			
			if(StringUtils.isEmpty(alias) || StringUtils.isEmpty(name) || StringUtils.isEmpty(email) || StringUtils.isEmpty(pwd)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.message.emptydata"));
				message.setMessageType(AjaxConstants.JS_AJAX_RESULT_INFO);
				message.setRelatedField("name");
			}
			
			if(message == null && !EmailValidator.getInstance().isValid(email)){
				message = new Message(true);
				message.setDescription(LanguageWorker.getMessage("registration.message.error.email"));
				message.setMessageType(AjaxConstants.JS_AJAX_RESULT_ERROR);
				message.setRelatedField("email");
			}
		}
		catch(Exception e){}
		
		return message;
	}

	@Override
	public void destroy() {
		super.destroy();
		System.out.println("RegistrationServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("RegistrationServlet - init()");
	}

}
