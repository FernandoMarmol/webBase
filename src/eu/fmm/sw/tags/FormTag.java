package eu.fmm.sw.tags;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.RandomStringUtils;
import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.HiddenField;
import eu.fmm.sw.web.annotations.NotFormField;
import eu.fmm.sw.web.annotations.SelectField;
import eu.fmm.sw.web.annotations.TextField;
import eu.fmm.sw.web.annotations.TextareaField;
import eu.fmm.sw.web.builders.CheckBoxFieldBuilder;
import eu.fmm.sw.web.builders.CommonFieldBuilder;
import eu.fmm.sw.web.builders.HiddenFieldBuilder;
import eu.fmm.sw.web.builders.SelectFieldBuilder;
import eu.fmm.sw.web.builders.TextFieldBuilder;
import eu.fmm.sw.web.builders.TextareaFieldBuilder;

public class FormTag extends TagSupport {

	private Object bean;
	private String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Object getBean() {
		return bean;
	}

	public void setBean(Object bean) {
		this.bean = bean;
	}

	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		
		StringBuffer form = new StringBuffer();
		String formId = bean.getClass().getSimpleName() + "Form" + RandomStringUtils.randomAlphanumeric(5);
		
		try {
			form.append("<div class=\"form1\">");
			
			form.append("<form name=\"").append(bean.getClass().getSimpleName()).append("Form\"");
			form.append(" id=\"").append(formId).append("\"");
			form.append(" action=\"").append(action).append("\"");
			form.append(" method=\"").append("POST").append("\"");
			form.append(">");
			
			Field[] fields = bean.getClass().getDeclaredFields();

			for (Field field : fields) {
				
				//Si el campo tiene anotaciones
				if(field.getAnnotations().length > 0){
					//Si no se debe ignorar el campo para el formulario
					if(!field.isAnnotationPresent(NotFormField.class)){
						
						//Input fields
						if(field.isAnnotationPresent(HiddenField.class)
							|| field.isAnnotationPresent(TextField.class)
							|| field.isAnnotationPresent(CheckBoxField.class)){
							
							form.append("<input name=\"").append(field.getName()).append("\"");
							
							String id = addId(field, form);
							
							CommonField commonField = null;
							if(field.isAnnotationPresent(CommonField.class)){
								commonField = field.getAnnotation(CommonField.class);
								CommonFieldBuilder.build(commonField, form);
							}
							
							if(field.isAnnotationPresent(TextField.class)){
								TextField textField = field.getAnnotation(TextField.class);
								TextFieldBuilder.build(textField, form);
							}
							else if(field.isAnnotationPresent(HiddenField.class)){
								HiddenField hiddenField = field.getAnnotation(HiddenField.class);
								HiddenFieldBuilder.build(hiddenField, form);
							}
							
							else if(field.isAnnotationPresent(CheckBoxField.class)){
								CheckBoxField checkboxField = field.getAnnotation(CheckBoxField.class);
								CheckBoxFieldBuilder.build(checkboxField, form);
							}
							
							addValue(field, form);
							
							form.append("/>");
							
							addLabel(commonField, id, form);
						}
						//Select field
						else if(field.isAnnotationPresent(SelectField.class)){
							form.append("<select name=\"").append(field.getName()).append("\"");
							SelectField selectField = field.getAnnotation(SelectField.class);
							SelectFieldBuilder.build(selectField, form);
							
							if(field.isAnnotationPresent(CommonField.class)){
								CommonField commonField = field.getAnnotation(CommonField.class);
								CommonFieldBuilder.build(commonField, form);
							}
							
							form.append(">");
							
							SelectFieldBuilder.buildOptions(selectField, getValue(field), form);
							
							form.append("</select>");
						}
						else if(field.isAnnotationPresent(TextareaField.class)){
							form.append("<textarea name=\"").append(field.getName()).append("\"");
							TextareaField textareaField = field.getAnnotation(TextareaField.class);
							TextareaFieldBuilder.build(textareaField, form);
							
							if(field.isAnnotationPresent(CommonField.class)){
								CommonField commonField = field.getAnnotation(CommonField.class);
								CommonFieldBuilder.build(commonField, form);
							}
							
							form.append(">");
							
							addValue(field, form);
							
							form.append("</textarea>");
						}
						
						form.append("<br><br>");
					}
				}
			}
			
			form.append("<button type=\"button\" onclick=\"javascript:sendFormThroughAjax('").append(formId).append("');\">Enviar</button>"); //TODO: El boton submit debe personalizarse
			
			form.append("</form>");			
			form.append("</div>");
		}
		catch(Exception e){
			form = new StringBuffer();
		}
		
		try {
			pageContext.getOut().print(form.toString());
		}
		catch (IOException e) {	e.printStackTrace(); }
		
		return TagSupport.SKIP_BODY;
	}
	
	private String addId(Field field, StringBuffer htmlBuffer){
		String id = "";
		try{
			id = field.getName() + "id" + RandomStringUtils.randomAlphanumeric(5);
			htmlBuffer.append(" id=\"").append(id).append("\"");
		}
		catch(Exception e){
			System.out.println("No ha sido posible generar el id del campo " + bean.getClass().getSimpleName() + " - " + field.getName());
		}
		
		return id;
	}
	
	private void addLabel(CommonField commonField, String id, StringBuffer htmlBuffer){
		try{
			htmlBuffer.append("<label for=\"").append(id).append("\">").append(LanguageWorker.getMessage(commonField.label())).append("</label>");
		}
		catch(Exception e){
			System.out.println("No ha sido posible generar el label del campo con id " + id);
		}
	}
	
	/**
	 * Añade el valor si lo hubiese del atributo del bean al código html
	 * @param field
	 * @param htmlBuffer
	 */
	private void addValue(Field field, StringBuffer htmlBuffer){
		try{
			String value = getValue(field);
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
	 * @param field
	 * @return
	 */
	private String getValue(Field field){
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
