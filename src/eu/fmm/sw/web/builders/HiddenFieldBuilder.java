package eu.fmm.sw.web.builders;

import eu.fmm.sw.web.annotations.HiddenField;

public class HiddenFieldBuilder {

	public static void build(HiddenField hiddenField, StringBuffer htmlBuffer){
		htmlBuffer.append(" type=\"hidden\"");		
	}
	
}
