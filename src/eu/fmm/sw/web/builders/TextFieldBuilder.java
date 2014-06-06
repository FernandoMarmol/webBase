package eu.fmm.sw.web.builders;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.lang.LanguageWorker;
import eu.fmm.sw.web.annotations.TextField;

public class TextFieldBuilder {

	public static void build(TextField textField, StringBuffer htmlBuffer){
		if(textField.isPassword())
			htmlBuffer.append(" type=\"password\"");
		else
			htmlBuffer.append(" type=\"text\"");
		
		if(!StringUtils.isEmpty(textField.placeholder()))
			htmlBuffer.append(" placeholder=\"").append(LanguageWorker.getMessage(textField.placeholder())).append("\"");
		
		if(textField.maxlength() > -1)
			htmlBuffer.append(" maxlength=\"").append(textField.maxlength()).append("\"");
	}
	
}
