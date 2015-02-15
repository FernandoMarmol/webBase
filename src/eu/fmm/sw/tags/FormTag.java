package eu.fmm.sw.tags;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.RandomStringUtils;

import eu.fmm.sw.ddbb.annotations.TableDefinition;
import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.HiddenField;
import eu.fmm.sw.web.annotations.NotFormField;
import eu.fmm.sw.web.annotations.SelectField;
import eu.fmm.sw.web.annotations.TextField;
import eu.fmm.sw.web.annotations.TextareaField;
import eu.fmm.sw.web.builders.CheckBoxFieldBuilder;
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
			
			encodeBeanToHTML(bean, form);
			
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
	
	public void encodeBeanToHTML(Object bean, StringBuffer form){
		
		Field[] fields = bean.getClass().getDeclaredFields();

		for (Field field : fields) {
			
			//Si el campo tiene anotaciones
			if(field.getAnnotations().length > 0){
				//Si no se debe ignorar el campo para el formulario
				if(!field.isAnnotationPresent(NotFormField.class)){
					
					CommonField commonField = field.getAnnotation(CommonField.class);
					
					if(field.isAnnotationPresent(TextField.class)){
						TextField textField = field.getAnnotation(TextField.class);
						TextFieldBuilder.build(bean, field, textField, commonField, form);
					}
					else if(field.isAnnotationPresent(CheckBoxField.class)){
						CheckBoxField checkboxField = field.getAnnotation(CheckBoxField.class);
						CheckBoxFieldBuilder.build(bean, field, checkboxField, commonField, form);
					}
					else if(field.isAnnotationPresent(HiddenField.class)){
						HiddenField hiddenField = field.getAnnotation(HiddenField.class);
						HiddenFieldBuilder.build(bean, field, hiddenField, commonField, form);
					}
					else if(field.isAnnotationPresent(SelectField.class)){
						SelectField selectField = field.getAnnotation(SelectField.class);
						SelectFieldBuilder.build(bean, field, selectField, commonField, form);
					}
					else if(field.isAnnotationPresent(TextareaField.class)){
						TextareaField textareaField = field.getAnnotation(TextareaField.class);
						TextareaFieldBuilder.build(bean, field, textareaField, commonField, form);
					}
				}
			}
			else if(field.getDeclaringClass().getAnnotation(TableDefinition.class) != null) {
				try {
					if(!field.isAccessible())
						field.setAccessible(true);
					
					Object subBean = field.get(bean);
					encodeBeanToHTML(subBean, form);
				}
				catch (Exception e) {}
				
			}
			
			form.append("<br><br>");
		}
		
	}
	
}
