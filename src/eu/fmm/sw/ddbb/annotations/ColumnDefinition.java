package eu.fmm.sw.ddbb.annotations;

import eu.fmm.sw.beans.None;
import eu.fmm.sw.ddbb.DataTypes;

public @interface ColumnDefinition {
	
	public String columnName();
	
	public boolean isPK() default false;
	
	public boolean isFK() default false;
	
	public Class<?> fkTableReference() default None.class;
	
	/**
	 * @return uno de los valores de DataTypes
	 */
	public int dataType() default DataTypes.VARCHAR;

}
