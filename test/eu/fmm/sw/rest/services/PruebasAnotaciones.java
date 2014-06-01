package eu.fmm.sw.rest.services;

import java.lang.reflect.Field;

import eu.fmm.sw.ddbb.beans.User;
import eu.fmm.sw.web.annotations.TextField;

public class PruebasAnotaciones {

	public static void main(String[] args) {

		User user = new User();
		Field[] fields = user.getClass().getDeclaredFields();

		for (Field field : fields) {
			if(field.isAnnotationPresent(TextField.class)){
				TextField textField = field.getAnnotation(TextField.class);
				
				System.out.println(field.getName() + " - " + textField.annotationType().getName());
			}
		}
	}
}
