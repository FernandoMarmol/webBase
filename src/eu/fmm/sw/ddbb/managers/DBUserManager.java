package eu.fmm.sw.ddbb.managers;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;

import javax.servlet.ServletContext;
import javax.sql.DataSource;

import eu.fmm.sw.ContextConstants;
import eu.fmm.sw.beans.User;
import eu.fmm.sw.beans.UserData;
import eu.fmm.sw.ddbb.MainManager;

public class DBUserManager extends MainManager {
	
	public DBUserManager(DataSource ds) {
		super(ds);
	}

	public static DBUserManager getInstance(ServletContext servletContext) {
		
		DBUserManager userManager = (DBUserManager) servletContext.getAttribute(ContextConstants.MANAGER_USER);
		
		synchronized (servletContext){
			if(userManager == null){
				userManager = new DBUserManager((DataSource) servletContext.getAttribute(ContextConstants.DATA_SOURCE));
				servletContext.setAttribute(ContextConstants.MANAGER_USER, userManager);
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
			Connection con = getConnection(false);
			
			String insert = "INSERT INTO users (USER_EMAIL, USER_PWD) VALUES ('"+email+"', sha1('"+pwd+"'))";
			
			stmt = con.prepareStatement(insert);
			stmt.executeUpdate();
			stmt.close();
			
			String insertData= "INSERT INTO user_data (USER_ID, USER_ALIAS, USER_NAME) VALUES ((SELECT USER_ID FROM users WHERE USER_EMAIL LIKE '" + email + "'), '"+name+"', '"+name+"')";
			
			stmt = con.prepareStatement(insertData);
			stmt.executeUpdate();
			stmt.close();
			
			String insertSettings = "INSERT INTO user_settings (USER_ID) SELECT USER_ID FROM users WHERE USER_EMAIL LIKE '" + email + "'";
			
			stmt = con.prepareStatement(insertSettings);
			stmt.executeUpdate();
			
			
			registered = true;
		}
		catch (SQLException e) {
			e.printStackTrace();
		}
		finally{
			try {
				if(registered)
					stmt.getConnection().commit();
				
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
	public User getUser(String email, String pwd){
		User user = new User();
		UserData userData = null;
		
		PreparedStatement stmt = null;
		try {
			String select = 
			" SELECT users.USER_ID, users.USER_IS_OFF, users.USER_EMAIL, user_data.USER_NAME " +  
			" FROM users left outer join user_settings on users.USER_ID = user_settings.USER_ID " +
			" left outer join user_data on users.USER_ID = user_data.USER_ID " +
			" WHERE users.USER_EMAIL = '" + email + "' AND users.USER_PWD = sha1('" + pwd + "') ";
			
			stmt = getPreparedStatement(select);
			ResultSet rs = stmt.executeQuery();
			
			if(rs.first()){
				user = new User(rs.getInt("USER_ID"));
				user.setUserOff((rs.getInt("USER_IS_OFF") == 1)?true:false);
				userData = user.getUserData();
				userData.setName(rs.getString("USER_NAME"));
				userData.setSurname(rs.getString("USER_SURNAME"));
				userData.setEmail(rs.getString("USER_EMAIL"));
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
