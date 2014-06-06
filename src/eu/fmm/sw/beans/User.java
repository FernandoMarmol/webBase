package eu.fmm.sw.beans;

import eu.fmm.sw.ddbb.DataTypes;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.annotations.TableDefinition;
import eu.fmm.sw.web.annotations.NotFormField;

@TableDefinition(tableName = "users")
public class User {
	
	@ColumnDefinition(columnName = "user_id", dataType = DataTypes.INT, isPK = true)
	@NotFormField
	private int id = -1;
	
	private UserData userData;
	
	private UserSettings userSettings;

}
