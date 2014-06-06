package eu.fmm.sw.rest.services;

import java.lang.reflect.Field;

import eu.fmm.sw.beans.UserData;
import eu.fmm.sw.web.annotations.TextField;

public class PruebasAnotaciones {

	public static void main(String[] args) {

		UserData user = new UserData();
		Field[] fields = user.getClass().getDeclaredFields();

		for (Field field : fields) {
			if(field.isAnnotationPresent(TextField.class)){
				TextField textField = field.getAnnotation(TextField.class);
				
				System.out.println(field.getName() + " - " + textField.annotationType().getName());
			}
		}
	}
}
