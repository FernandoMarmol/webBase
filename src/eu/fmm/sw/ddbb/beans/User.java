package eu.fmm.sw.ddbb.beans;

import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.NotFormField;
import eu.fmm.sw.web.annotations.TextField;

public class User {

	@NotFormField
	private int id = -1;
	
	@CommonField(required = true)
	@TextField (placeholder = "user.alias.placeholder")
	private String alias = "";
	
	@CommonField
	@TextField (placeholder = "user.name.placeholder")
	private String name = "";
	
	@CommonField
	@TextField (placeholder = "user.surname.placeholder")
	private String surname = "";
	
	@CommonField
	@TextField (placeholder = "user.email.placeholder")
	private String email = "";
	
	@CommonField(required = true)
	@TextField(isPassword = true, placeholder = "user.password.placeholder")
	private String password = "";
	
	@CommonField(label = "user.isAsian.label")
	@CheckBoxField(defaultValue = "ok")
	private boolean isAsian;
	
	private UserSettings settings;
	
	public UserSettings getSettings() {
		return settings;
	}
	public void setSettings(UserSettings settings) {
		this.settings = settings;
	}
	public String getSurname() {
		return surname;
	}
	public void setSurname(String surname) {
		this.surname = surname;
	}
	public int getId() {
		return id;
	}
	public void setId(int id) {
		this.id = id;
	}
	public String getName() {
		return name;
	}
	public void setName(String name) {
		this.name = name;
	}
	public String getEmail() {
		return email;
	}
	public void setEmail(String email) {
		this.email = email;
	}
	
}
