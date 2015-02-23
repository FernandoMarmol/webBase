package eu.fmm.sw.common.servlets;

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import com.google.gson.Gson;

import eu.fmm.sw.RequestConstants;
import eu.fmm.sw.SessionConstants;
import eu.fmm.sw.common.beans.Message;

/**
 * Servlet implementation class MainLoggedServlet
 */
public abstract class AbstractLoggedServlet extends AbstractServlet {
	
	protected void execute(String method, HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		
		System.out.println("ENCODING!! " + request.getCharacterEncoding());
		System.out.println("METHOD!! " + method);
		
		if(request.getSession().getAttribute(SessionConstants.SESSION_USER) == null){
			request.getRequestDispatcher("/HomeLayout").forward(request, response);;
		}
		else{
		
			boolean isAjax = false;
			
			if(request.getParameter("isAjax") != null && request.getParameter("isAjax").equalsIgnoreCase("true")){
				isAjax = true;
			}
			
			Message message = validate(request);
			
			if(message == null){
				message = customExecute(request, response);
			}
			
			if(isAjax && message != null){
				response.setContentType("application/json");
				// Get the printwriter object from response to write the required json object to the output stream      
				PrintWriter out = response.getWriter();
				Gson gson = new Gson();
				out.print(gson.toJson(message));
				out.flush();
			}
			else {
				request.setAttribute(RequestConstants.MESSAGE, message);
				
				if(message != null && message.getNextToDo() != null)
					request.getRequestDispatcher(message.getNextToDo()).forward(request, response);
			}
		}
	}
	
}
