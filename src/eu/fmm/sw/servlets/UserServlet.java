package eu.fmm.sw.servlets;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.ServletException;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import eu.fmm.sw.beans.User;
import eu.fmm.sw.common.beans.Message;
import eu.fmm.sw.common.servlets.AbstractServlet;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.sql.SqlBuilder;
import eu.fmm.sw.web.annotations.NotFormField;

/**
 * Servlet implementation class UserServlet
 */
@WebServlet(name="UserServlet", displayName="User Servlet", urlPatterns = {"/User/*"}, loadOnStartup=2)
public class UserServlet extends AbstractServlet {
	
	private static final long serialVersionUID = 1L;
	
	public static final String PATH_CREATE="./User/crt";
	public static final String PATH_UPDATE="./User/updt";
	public static final String PATH_REMOVE="./User/rmv";
	public static final String PATH_GET="./User/get";
	
	/**
	 * @see MainServlet#customExecute(HttpServletRequest request, HttpServletResponse response)
	 */
	public Message customExecute(HttpServletRequest request, HttpServletResponse response) throws ServletException, IOException {
		System.out.println("UserServlet - customExecute");
		
		if(PATH_CREATE.endsWith(request.getPathInfo())){
			
			Field[] fields = User.class.getDeclaredFields();

			for (Field field : fields) {
				
				//Si el campo tiene anotaciones
				if(field.getAnnotations().length > 0){
					//Si no se debe ignorar el campo para el formulario
					if(field.isAnnotationPresent(ColumnDefinition.class)){
						//TODO:Continuar aqui
					}
				}
				
			}
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
