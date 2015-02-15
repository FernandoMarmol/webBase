package eu.fmm.sw.beans;

import eu.fmm.sw.ddbb.DataTypes;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.annotations.TableDefinition;
import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.NotFormField;
import eu.fmm.sw.web.annotations.TextField;

@TableDefinition(tableName = "user_data")
public class UserData {

	/**
	 * id del usuario 
	 * @param id
	 */
	public UserData(int id){
		this.id = id;
	}
	
	@ColumnDefinition(columnName = "user_id", dataType = DataTypes.INT, isPK = true, isFK = true, fkTableReference = User.class)
	@NotFormField
	private int id = -1;
	
	@ColumnDefinition(columnName = "user_alias")
	@CommonField(required = true)
	@TextField (placeholder = "user.alias.placeholder")
	private String alias = "";
	
	@ColumnDefinition(columnName = "user_name")
	@CommonField
	@TextField (placeholder = "user.name.placeholder")
	private String name = "";
	
	@ColumnDefinition(columnName = "user_surname")
	@CommonField
	@TextField (placeholder = "user.surname.placeholder")
	private String surname = "";
	
	@ColumnDefinition(columnName = "user_email")
	@CommonField
	@TextField (placeholder = "user.email.placeholder")
	private String email = "";
	
	@ColumnDefinition(columnName = "user_password", containsPassword = true)
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
