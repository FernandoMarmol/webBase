package eu.fmm.sw.web.annotations;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;

/**
 * Representa un campo select en un formulario
 * @author fmm
 *
 */
@Target(value = ElementType.FIELD)
@Retention(value = RetentionPolicy.RUNTIME)
public @interface SelectField {

	/**
	 * Lista de opciones (Etiqueta)
	 * @return
	 */
	public String[] optionLabelList() default {""};
	
	/**
	 * Lista de opciones (valor)
	 * @return
	 */
	public String[] optionValueList() default {""};
	
	/**
	 * Indica si se pueden seleccionar varios valores
	 * @return
	 */
	public boolean multiple() default false;
	
	/**
	 * Indica el numero de valores visibles al desplegar el select
	 * @return
	 */
	public int size() default -1;
	
}
