package eu.fmm.sw.web.builders;

import org.apache.commons.lang3.StringUtils;

import eu.fmm.sw.web.annotations.CommonField;

public class CommonFieldBuilder {
	
	public static void build(CommonField commonField, StringBuffer htmlBuffer){
		if(commonField.disabled())
			htmlBuffer.append(" disabled");
			
		if(commonField.required())
			htmlBuffer.append(" required");
		
		if(commonField.autofocus())
			htmlBuffer.append(" autofocus");
		
		if(!StringUtils.isEmpty(commonField.value()))
			htmlBuffer.append(" value=\"").append(commonField.value()).append("\"");
	}

}
