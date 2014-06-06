package eu.fmm.sw.web.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Representa un campo de texto en un formulario
 * @author fmm
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface TextField {
	
	public String placeholder() default "";
	
	public boolean isPassword() default false;
	
	public int maxlength() default -1;
	
	public int size() default -1;

}