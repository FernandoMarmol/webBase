package eu.fmm.sw.tags;

import java.io.IOException;

import javax.servlet.jsp.JspException;
import javax.servlet.jsp.PageContext;
import javax.servlet.jsp.tagext.TagSupport;

import eu.fmm.sw.lang.LanguageWorker;

public class LanguageTag extends TagSupport {
	
	private String key = null;
	
	public String getKey() {
		return key;
	}

	public void setKey(String key) {
		this.key = key;
	}
	
	@Override
	public int doEndTag() throws JspException {
		// TODO Auto-generated method stub
		return super.doEndTag();
	}

	@Override
	public int doStartTag() throws JspException {
		
		String message = getMessage(key, pageContext);
		
		try {
			pageContext.getOut().print(message);
		}
		catch (IOException e) {
			e.printStackTrace();
		}
		
		return super.doStartTag();
	}
	
	/**
	 * Devuelve el mensaje si existe, y si no, devuelve la clave introducida
	 * @param key
	 * @param pageContext
	 * @return
	 */
	public static String getMessage(String key, PageContext pageContext){
		try{
			return LanguageWorker.getMessage(key);
		}
		catch(Exception e){
			return key;
		}
	}

}
