package eu.fmm.sw.beans;

import eu.fmm.sw.ddbb.DataTypes;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.annotations.TableDefinition;
import eu.fmm.sw.web.annotations.NotFormField;

@TableDefinition(tableName = "user_settings")
public class UserSettings {
	
	public UserSettings(int id){
		this.id = id;
	}
	
	@ColumnDefinition(columnName = "user_id", dataType = DataTypes.INT, isPK = true, isFK = true, fkTableReference = User.class)
	@NotFormField
	private int id = -1;

}
