package eu.fmm.sw.ddbb.sql;

import java.lang.reflect.Field;

import javax.servlet.http.HttpServletRequest;

import eu.fmm.sw.beans.User;
import eu.fmm.sw.ddbb.DataTypes;
import eu.fmm.sw.ddbb.annotations.ColumnDefinition;
import eu.fmm.sw.ddbb.annotations.TableDefinition;

public class SqlBuilder {

	public static String generateInsert(Class<?> type, HttpServletRequest request){
		StringBuffer query = new StringBuffer("INSERT INTO ");
		
		if(type.isAnnotationPresent(TableDefinition.class)){
			query.append(type.getAnnotation(TableDefinition.class).tableName());
		}

		Field[] fields = type.getDeclaredFields();
		
		if(fields.length > 0){
			StringBuffer columns = new StringBuffer();
			StringBuffer values = new StringBuffer();

			boolean firstColumn = true;
			ColumnDefinition columnDefinition = null;
			for (Field field : fields) {
				//Si el campo tiene anotaciones
				if(field.getAnnotations().length > 0){
					//Si no se debe ignorar el campo para el formulario
					if(field.isAnnotationPresent(ColumnDefinition.class)){
						columnDefinition = field.getAnnotation(ColumnDefinition.class);
						
						if(!(columnDefinition.isPK() && columnDefinition.dataType() == DataTypes.INT_AUTOINCREMENT)){
							if(firstColumn){
								firstColumn = false;
							}
							else{
								columns.append(", ");
								values.append(", ");
							}
							
							columns.append(columnDefinition.columnName().toUpperCase());
							values.append(getRequestAttributeValue(request, field));
						}
					}
				}
			}
			
			query.append(" (").append(columns).append(") values (").append(values).append(")");
		}
		
		return query.toString();
	}
	
	public static String generateUpdate(Class<?> type, HttpServletRequest request){
		
		StringBuffer query = new StringBuffer("UPDATE ");
		
		if(User.class.isAnnotationPresent(TableDefinition.class)){
			query.append(User.class.getAnnotation(TableDefinition.class).tableName());
		}
		
		Field[] fields = User.class.getDeclaredFields();
		
		if(fields.length > 0){
			StringBuffer set = new StringBuffer(" SET ");
			StringBuffer where = new StringBuffer(" WHERE ");

			boolean firstColumn = true;
			boolean firstWhereColumn = true;
			ColumnDefinition columnDefinition = null;
			for (Field field : fields) {
				//Si el campo tiene anotaciones
				if(field.getAnnotations().length > 0){
					//Si no se debe ignorar el campo para el formulario
					if(field.isAnnotationPresent(ColumnDefinition.class)){
						columnDefinition = field.getAnnotation(ColumnDefinition.class);
						if(!columnDefinition.isPK()){
							if(firstColumn)
								firstColumn = false;
							else
								set.append(", ");
							
							set.append(columnDefinition.columnName().toUpperCase()).append(" = '").append(getRequestAttributeValue(request, field)).append("'");
						}
						else{
							if(firstWhereColumn)
								firstWhereColumn = false;
							else
								set.append(", ");
							
							where.append(set.append(columnDefinition.columnName().toUpperCase()).append(" = '").append(getRequestAttributeValue(request, field)).append("'"));
						}
					}
				}
			}
			
			query.append(set).append(where);
		}
		
		return query.toString();
	}
	
	public static void generateDelete(Class<?> type, HttpServletRequest request){
		StringBuffer query = new StringBuffer("DELETE FROM ");
		
		if(User.class.isAnnotationPresent(TableDefinition.class)){
			query.append(User.class.getAnnotation(TableDefinition.class).tableName());
		}
		
		Field[] fields = User.class.getDeclaredFields();
		
		if(fields.length > 0){
			StringBuffer where = new StringBuffer(" WHERE ");

			boolean firstWhereColumn = true;
			ColumnDefinition columnDefinition = null;
			for (Field field : fields) {
				//Si el campo tiene anotaciones
				if(field.getAnnotations().length > 0){
					//Si no se debe ignorar el campo para el formulario
					if(field.isAnnotationPresent(ColumnDefinition.class)){
						columnDefinition = field.getAnnotation(ColumnDefinition.class);
						if(columnDefinition.isPK()){
							if(firstWhereColumn)
								firstWhereColumn = false;
							else
								where.append(", ");
							
							where.append(columnDefinition.columnName().toUpperCase()).append(" = '").append(getRequestAttributeValue(request, field)).append("'");
						}
					}
				}
			}
			
			query.append(where);
		}
		
		query.toString();
	}
	
	private static String getRequestAttributeValue(HttpServletRequest request, Field field){
		if(request.getAttribute(field.getName()) != null)
			return "'"+request.getAttribute(field.getName())+"'";
		else
			return "";
	}
	
}
