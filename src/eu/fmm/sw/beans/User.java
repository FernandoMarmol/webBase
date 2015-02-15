package eu.fmm.sw.beans;

import eu.fmm.sw.ddbb.DataTypes;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.annotations.TableDefinition;
import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.HiddenField;
import eu.fmm.sw.web.annotations.NotFormField;

@TableDefinition(tableName = "users")
public class User {
	
	public static int ANONYMOUS_USER_ID = -1;
	
	/**
	 * Usarlo para crear un nuevo Usuario
	 */
	public User(){}
	
	/**
	 * Usarlo para el login del usuario
	 * @param alias
	 * @param password
	 */
	public User(String aliasMail, String password){
		//TODO: Login
	}
	
	@ColumnDefinition(columnName = "user_id", dataType = DataTypes.INT_AUTOINCREMENT, isPK = true)
	@NotFormField
	private int id = ANONYMOUS_USER_ID;
	
	@CommonField
	@CheckBoxField(defaultValue = "ok")
	private boolean userOff = false;
	
	@HiddenField
	private boolean anonymous = true;
	
	private UserData userData = new UserData(id);
	
	private UserSettings userSettings = new UserSettings(id);

	
	public boolean isAnonymous() {
		return anonymous;
	}

	public void setAnonymous(boolean anonymous) {
		this.anonymous = anonymous;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public UserData getUserData() {
		return userData;
	}

	public void setUserData(UserData userData) {
		this.userData = userData;
	}

	public UserSettings getUserSettings() {
		return userSettings;
	}

	public void setUserSettings(UserSettings userSettings) {
		this.userSettings = userSettings;
	}
}
