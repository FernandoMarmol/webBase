package eu.fmm.sw.web.builders;

import java.lang.reflect.Field;

import eu.fmm.sw.web.annotations.CommonField;
import eu.fmm.sw.web.annotations.HiddenField;

public class HiddenFieldBuilder {

	public static void build(Object bean, Field field, HiddenField hiddenField, CommonField commonField, StringBuffer htmlBuffer){
		
		htmlBuffer.append("<input name=\"").append(field.getName()).append("\"");
		
		String id = CommonFieldBuilder.addId(field, htmlBuffer);
		
		CommonFieldBuilder.appendCommonAttributes(commonField, htmlBuffer);
		
		htmlBuffer.append(" type=\"hidden\"");
		
		CommonFieldBuilder.addValue(bean, field, htmlBuffer);
		
		htmlBuffer.append("/>");
		
		CommonFieldBuilder.addLabel(commonField, id, htmlBuffer);
	}

}
