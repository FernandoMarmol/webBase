package eu.fmm.sw.web.builders;

import java.lang.reflect.Field;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.CheckBoxField;
import eu.fmm.sw.web.annotations.CommonField;

public class CheckBoxFieldBuilder {

	public static void build(Object bean, Field field, CheckBoxField checkBoxField, CommonField commonField, StringBuffer htmlBuffer){
		
		htmlBuffer.append("<input name=\"").append(field.getName()).append("\"");
		
		String id = CommonFieldBuilder.addId(field, htmlBuffer);
		
		CommonFieldBuilder.appendCommonAttributes(commonField, htmlBuffer);
		
		htmlBuffer.append(" type=\"checkbox\"");
		
		if(checkBoxField.defaultChecked())
			htmlBuffer.append(" checked");
		
		if(!StringUtils.isEmpty(checkBoxField.defaultValue()))
			htmlBuffer.append(" defaultValue=\"").append(checkBoxField.defaultValue()).append("\"");
		
		CommonFieldBuilder.addValue(bean, field, htmlBuffer);
		
		htmlBuffer.append("/>");
		
		CommonFieldBuilder.addLabel(commonField, id, htmlBuffer);
	}
	
}
