package eu.fmm.sw.servlets;

import java.io.IOException;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractServlet;

/**
 * Servlet implementation class InitServlet
 */
@WebServlet( name="InitServlet", displayName="Init Servlet", urlPatterns = {"/InitServlet"}, loadOnStartup=1 )
public class InitServlet extends AbstractServlet {

    /**
	 * @see AbstractServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("InitServlet - customExecute");
		
		request.getRequestDispatcher("HomeLayout").forward(request, response);
		
		return null;
	}

	@Override
	public Message validate(HttpServletRequest request) {
		return null;
	}
	
	@Override
	public void destroy() {
		super.destroy();
		System.out.println("InitServlet - destroy()");
	}

	@Override
	public void init() throws ServletException {
		super.init();
		System.out.println("InitServlet - init()");
	}
}
