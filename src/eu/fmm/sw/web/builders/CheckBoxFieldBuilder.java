package eu.fmm.sw.web.builders;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.CheckBoxField;

public class CheckBoxFieldBuilder {

	public static void build(CheckBoxField checkboxField, StringBuffer htmlBuffer){
		htmlBuffer.append(" type=\"checkbox\"");
		
		if(checkboxField.defaultChecked())
			htmlBuffer.append(" checked");
		
		if(!StringUtils.isEmpty(checkboxField.defaultValue()))
			htmlBuffer.append(" defaultValue=\"").append(checkboxField.defaultValue()).append("\"");
	}
	
}
