package eu.fmm.sw.beans;


public class UserData {

	private String alias = "";
	
	private String name = "";
	
	private String surname = "";
	
	private String email = "";

	private String password = "";
	
	private UserSettings settings;
	
	
	public String getAlias() {
		return alias;
	}

	public void setAlias(String alias) {
		this.alias = alias;
	}
	
	public String getName() {
		return name;
	}
	
	public void setName(String name) {
		this.name = name;
	}
	
	public String getSurname() {
		return surname;
	}
	
	public void setSurname(String surname) {
		this.surname = surname;
	}
	
	public String getEmail() {
		return email;
	}
	
	public void setEmail(String email) {
		this.email = email;
	}
	
	public String getPassword() {
		return password;
	}

	public void setPassword(String password) {
		this.password = password;
	}
	
	public UserSettings getSettings() {
		return settings;
	}
	
	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}
	
}
