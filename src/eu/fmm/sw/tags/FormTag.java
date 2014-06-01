package eu.fmm.sw.tags;

import java.io.IOException;
import java.lang.reflect.Field;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.tagext.TagSupport;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.web.annotations.TextField;

public class FormTag extends TagSupport {

	private Class<?> bean;
	private String action;
	
	public String getAction() {
		return action;
	}

	public void setAction(String action) {
		this.action = action;
	}

	public Class<?> getBean() {
		return bean;
	}

	public void setBean(Class<?> bean) {
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
		
		try {
			form.append("<div class=\"form1\"");
			
			form.append("<form name=\"").append(bean.getSimpleName()).append("Form\"");
			form.append(" action=\"").append(action).append("\"");
			form.append(" method=\"").append("POST").append("\"");
			form.append(">");
			
			Field[] fields = bean.getDeclaredFields();

			for (Field field : fields) {
				if(field.isAnnotationPresent(TextField.class)){
					TextField textField = field.getAnnotation(TextField.class);
					
					form.append("<input name=\"").append(field.getName()).append("\"");
					if(textField.isPassword())
						form.append(" type=\"password\"");
					else
						form.append(" type=\"text\"");
					
					if(!StringUtils.isEmpty(textField.placeholder()))
						form.append(" placeholder=\"").append(LanguageWorker.getMessage(textField.placeholder())).append("\"");
					
					form.append("/>");
					form.append("<br>");
				}
			}
			
			form.append("<input type=\"submit\" value=\"Proceder\"/>");
			
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
	
}
