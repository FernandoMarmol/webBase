package eu.fmm.sw.web.builders;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.TextField;

public class TextFieldBuilder {

	public static void build(Object bean, Field field, TextField textField, CommonField commonField, StringBuffer htmlBuffer){
		
		htmlBuffer.append("<input name=\"").append(field.getName()).append("\"");
		
		String id = CommonFieldBuilder.addId(field, htmlBuffer);
		
		CommonFieldBuilder.appendCommonAttributes(commonField, htmlBuffer);
		
		if(textField.isPassword())
			htmlBuffer.append(" type=\"password\"");
		else
			htmlBuffer.append(" type=\"text\"");
		
		if(!StringUtils.isEmpty(textField.placeholder()))
			htmlBuffer.append(" placeholder=\"").append(LanguageWorker.getMessage(textField.placeholder())).append("\"");
		
		if(textField.maxlength() > -1)
			htmlBuffer.append(" maxlength=\"").append(textField.maxlength()).append("\"");
		
		CommonFieldBuilder.addValue(bean, field, htmlBuffer);
		
		htmlBuffer.append("/>");
		
		CommonFieldBuilder.addLabel(commonField, id, htmlBuffer);
	}
	
}
