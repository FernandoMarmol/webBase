package eu.fmm.sw.web.builders;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.TextareaField;

public class TextareaFieldBuilder {

	public static void build(TextareaField textareaField, StringBuffer htmlBuffer){
		if(textareaField.cols() != -1)
			htmlBuffer.append(" cols=\"").append(textareaField.cols()).append("\"");
		
		if(textareaField.rows() != -1)
			htmlBuffer.append(" rows=\"").append(textareaField.rows()).append("\"");
		
		if(textareaField.maxlength() != -1)
			htmlBuffer.append(" maxlength=\"").append(textareaField.maxlength()).append("\"");
		
		if(!StringUtils.isEmpty(textareaField.wrap()))
			htmlBuffer.append(" wrap=\"").append(textareaField.wrap()).append("\"");
	}
	
}
