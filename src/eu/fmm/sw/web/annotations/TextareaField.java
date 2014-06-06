package eu.fmm.sw.web.annotations;

public @interface TextareaField {

	public int cols() default -1;
	
	public int rows() default -1;
	
	public int maxlength() default -1;
	
	/**
	 * Specifies how the text in a text area is to be wrapped when submitted in a form
	 * <br>
	 * Possible values <b>"soft"</b> and <b>"hard"</b>
	 */
	public String wrap() default "soft";
	
}
