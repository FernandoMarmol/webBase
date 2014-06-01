package eu.fmm.sw.lang;

import java.util.Locale;
import java.util.ResourceBundle;

public class LanguageWorker {
	
	private static ResourceBundle language = null;
	
	public static void init(){
		try{
			language = ResourceBundle.getBundle(LanguageWorker.class.getPackage().getName() + ".translation", Locale.getDefault());
		}
		catch(Exception e){
			language = ResourceBundle.getBundle(LanguageWorker.class.getPackage().getName() + ".translation", Locale.ENGLISH);
		}
	}

	/**
	 * Devuelve el mensaje si existe, y si no, devuelve la clave introducida
	 * @param key
	 * @return
	 */
	public static String getMessage(String key){
		if(language == null)
			init();
			
		try{
			return language.getString(key);
		}
		catch(Exception e){
			return key;
		}
	}
	
}
