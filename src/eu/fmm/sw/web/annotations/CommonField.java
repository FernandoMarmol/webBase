package eu.fmm.sw.web.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface CommonField {
	
	public boolean disabled() default false;
	
	public boolean required() default false;
	
	public boolean autofocus() default false;
	
	public String value() default "";
	
	/**
	 * label a mostrar junto con el campo.
	 */
	public String label() default "";

}
