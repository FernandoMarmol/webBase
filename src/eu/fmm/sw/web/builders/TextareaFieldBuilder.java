package eu.fmm.sw.web.builders;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.TextareaField;

public class TextareaFieldBuilder {

	public static void build(Object bean, Field field, TextareaField textareaField, CommonField commonField, StringBuffer htmlBuffer){
		
		htmlBuffer.append("<textarea name=\"").append(field.getName()).append("\"");
		
		CommonFieldBuilder.appendCommonAttributes(commonField, htmlBuffer);
		
		if(textareaField.cols() != -1)
			htmlBuffer.append(" cols=\"").append(textareaField.cols()).append("\"");
		
		if(textareaField.rows() != -1)
			htmlBuffer.append(" rows=\"").append(textareaField.rows()).append("\"");
		
		if(textareaField.maxlength() != -1)
			htmlBuffer.append(" maxlength=\"").append(textareaField.maxlength()).append("\"");
		
		if(!StringUtils.isEmpty(textareaField.wrap()))
			htmlBuffer.append(" wrap=\"").append(textareaField.wrap()).append("\"");
		
		htmlBuffer.append(">");
		
		CommonFieldBuilder.addValue(bean, field, htmlBuffer);
		
		htmlBuffer.append("</textarea>");
	}

}
