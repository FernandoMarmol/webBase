package eu.fmm.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractServlet;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name="UserServlet", displayName="User Servlet", urlPatterns = {"/User/*"}, loadOnStartup=2)
public class UserServlet extends AbstractServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String PATH_NEW="./User/new";
	public static final String PATH_UPDATE="./User/updt";
	public static final String PATH_REMOVE="./User/rmv";
	public static final String PATH_GET="./User/get";
	
	/**
	 * @see AbstractServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet - customExecute");
		
		if(PATH_NEW.endsWith(request.getPathInfo())){
			//SqlBuilder.generateInsert(User.class, request);
		}
		else if(PATH_UPDATE.endsWith(request.getPathInfo())){
			//SqlBuilder.generateUpdate(User.class, request);
		}
		else if(PATH_REMOVE.endsWith(request.getPathInfo())){
			//SqlBuilder.generateDelete(User.class, request);
		}
		
		return null;
	}
	
	@Override
	public Message validate(HttpServletRequest request) {
		return null;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("UserServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("UserServlet - init()");
	}

}
