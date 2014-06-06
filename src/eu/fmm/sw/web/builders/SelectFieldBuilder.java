package eu.fmm.sw.web.builders;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.SelectField;

public class SelectFieldBuilder {

	public static void build(SelectField selectField, StringBuffer htmlBuffer){
		if(selectField.multiple())
			htmlBuffer.append(" multiple");
		
		if(selectField.size() > -1)
			htmlBuffer.append(" size=\"").append(selectField.size()).append("\"");
	}
	
	public static void buildOptions(SelectField selectField, String selectedValue, StringBuffer htmlBuffer){
		if(selectField.optionValueList().length > 0){
			for(int i=0; i<selectField.optionValueList().length; i++){
				htmlBuffer.append("<option value=\"").append(selectField.optionValueList()[i]);
				
				if(!StringUtils.isEmpty(selectedValue) && selectedValue.equals(selectField.optionValueList()[i]))
					htmlBuffer.append(" selected");
				
				htmlBuffer.append("\">").append(selectField.optionLabelList()[i]).append("</option>");
			}
		}
	}
	
}
