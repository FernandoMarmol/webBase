package eu.fmm.sw.ddbb.managers;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import eu.fmm.sw.Constants;
import eu.fmm.sw.beans.UserData;
import eu.fmm.sw.beans.UserSettings;
import eu.fmm.sw.ddbb.MainManager;

public class UserManager extends MainManager {
	
	public UserManager(DataSource ds) {
		super(ds);
	}

	public static UserManager getInstance(ServletContext servletContext) {
		
		UserManager userManager = (UserManager) servletContext.getAttribute(Constants.MANAGER_USER);
		
		synchronized (servletContext){
			if(userManager == null){
				userManager = new UserManager((DataSource) servletContext.getAttribute(Constants.DATA_SOURCE));
				servletContext.setAttribute(Constants.MANAGER_USER, userManager);
			}
		}
		
		return userManager;
	}
	
	/**
	 * Registra un nuevo usuario en la BBDD
	 * @param name
	 * @param email
	 * @param pwd
	 * @return - true o false en funci�n de si se ha podido o no crear el usuario
	 */
	public boolean registerUser(String name, String email, String pwd){
		boolean registered = false;
		
		PreparedStatement stmt = null;
		try {
			String insert = "INSERT INTO users (USER_NAME, USER_EMAIL, USER_PWD) VALUES ('"+name+"', '"+email+"', sha1('"+pwd+"'))";
			
			stmt = getPreparedStatement(insert);
			stmt.executeUpdate();
			stmt.close();
			
			String insertProfile = "INSERT INTO users_settings (USER_ID) VALUES (SELECT USER_ID FROM users WHERE USER_EMAIL LIKE '" + email + "')";
			
			stmt = getPreparedStatement(insertProfile);
			stmt.executeUpdate();
			
			registered = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.getConnection().close();
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		return registered;
	}
	
	/**
	 * Obtiene el objeto de un usuario cuando este se loga
	 * @param email
	 * @param pwd
	 * @return - el usuario o null si no ha sido posible
	 */
	public UserData getUser(String email, String pwd){
		UserData user = null;
		
		PreparedStatement stmt = null;
		try {
			String select = 
			" SELECT users.USER_ID, USER_NAME, USER_EMAIL  " +  
			" FROM users left outer join users_settings on users.USER_ID = users_settings.USER_ID " +
			" WHERE USER_EMAIL = '" + email + "' AND USER_PWD = sha1('" + pwd + "') ";
			
			stmt = getPreparedStatement(select);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.first()){
				user = new UserData();
				user.setId(rs.getInt("USER_ID"));
				user.setName(rs.getString("USER_NAME"));
				user.setSurname(rs.getString("USER_SURNAME"));
				user.setEmail(rs.getString("USER_EMAIL"));
				
				UserSettings settings = new UserSettings();
				
				user.setSettings(settings);
			}
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.getConnection().close();
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return user;
	}
	
	/**
	 * Actualiza la vista principal que quiere recibir el usuario al acceder al sistema
	 * @param email
	 * @param pwd
	 * @return - el usuario o null si no ha sido posible
	 */
	public boolean updateUserPreferredMainView(int userId, int newPreferred){
		boolean updated = false;
		
		PreparedStatement stmt = null;
		try {
			String update = " UPDATE users_settings SET USER_PREFERRED_MAIN_VIEW = " + newPreferred + " WHERE USER_ID = " + userId;
			
			stmt = getPreparedStatement(update);
			stmt.executeUpdate();
			
			updated = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				stmt.getConnection().close();
				stmt.close();
			}
			catch (SQLException e) {
				e.printStackTrace();
			}
		}
		
		return updated;
	}

}
