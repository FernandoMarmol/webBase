package eu.fmm.sw.beans;


public class User {
	
	public static int ANONYMOUS_USER_ID = -1;
	
	/**
	 * Usarlo para crear un nuevo Usuario
	 */
	public User(){}
	
	/**
	 * id del usuario 
	 * @param id
	 */
	public User(int id){
		setId(id);
	}
	
	/**
	 * Usarlo para el login del usuario
	 * @param alias
	 * @param password
	 */
	public User(String aliasMail, String password){
		//TODO: Login
	}
	
	private int id = ANONYMOUS_USER_ID;
	
	private boolean userOff = false;
	
	private UserData userData = new UserData();
	
	private UserSettings userSettings = new UserSettings();

	
	public boolean isAnonymous() {
		return id == ANONYMOUS_USER_ID;
	}

	public int getId() {
		return id;
	}

	public void setId(int id) {
		this.id = id;
	}

	public boolean isUserOff() {
		return userOff;
	}

	public void setUserOff(boolean userOff) {
		this.userOff = userOff;
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
