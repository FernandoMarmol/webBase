package eu.fmm.sw.web.builders;

import java.lang.reflect.Field;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.TextareaField;

public class CommonFieldBuilder {
	
	public static void appendCommonAttributes(CommonField commonField, StringBuffer htmlBuffer){
		if(commonField.disabled())
			htmlBuffer.append(" disabled");
			
		if(commonField.required())
			htmlBuffer.append(" required");
		
		if(commonField.autofocus())
			htmlBuffer.append(" autofocus");
		
		if(!StringUtils.isEmpty(commonField.value()))
			htmlBuffer.append(" value=\"").append(commonField.value()).append("\"");
	}
	
	protected static String addId(Field field, StringBuffer htmlBuffer){
		String id = "";
		try{
			id = field.getName() + "id" + RandomStringUtils.randomAlphanumeric(5);
			htmlBuffer.append(" id=\"").append(id).append("\"");
		}
		catch(Exception e){
			System.out.println("No ha sido posible generar el id del campo " + field.getDeclaringClass().getSimpleName() + " - " + field.getName());
		}
		
		return id;
	}
	
	protected static void addLabel(CommonField commonField, String id, StringBuffer htmlBuffer){
		try{
			htmlBuffer.append("<label for=\"").append(id).append("\">").append(LanguageWorker.getMessage(commonField.label())).append("</label>");
		}
		catch(Exception e){
			System.out.println("No ha sido posible generar el label del campo con id " + id);
		}
	}
	
	/**
	 * Añade el valor si lo hubiese del atributo del bean al código html
	 * @param bean
	 * @param field
	 * @param htmlBuffer
	 */
	protected static void addValue(Object bean, Field field, StringBuffer htmlBuffer){
		try{
			String value = getValue(bean, field);
			if(value != null){
				if(field.isAnnotationPresent(TextareaField.class))
					htmlBuffer.append(value);
				else
					htmlBuffer.append(" value=\"").append(value).append("\"");
			}
		}
		catch(Exception e){
			System.out.println("No ha sido posible pintar el valor del campo " + bean.getClass().getSimpleName() + " - " + field.getName());
		}
	}
	
	/**
	 * Devuelve el valor si lo hubiese del atributo del bean
	 * @param bean
	 * @param field
	 * @return
	 */
	protected static String getValue(Object bean, Field field){
		try{
			if(!field.isAccessible())
				field.setAccessible(true);
			
			//Añadimos el value si el bean viene informado
			Object value = field.get(bean);
			if(value != null && !StringUtils.isEmpty(value.toString()))
				return value.toString();
		}
		catch(Exception e){
			System.out.println("No ha sido posible pintar el valor del campo " + bean.getClass().getSimpleName() + " - " + field.getName());
		}
		
		return null;
	}

}
