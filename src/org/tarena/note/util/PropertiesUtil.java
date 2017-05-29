package org.tarena.note.util;

import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class PropertiesUtil {
	private static Properties prop = new Properties();
	static{
		try {
			InputStream is = PropertiesUtil.class.getClassLoader().getResourceAsStream("opt.properties");
			prop.load(is);
		} catch (IOException e) {
			e.printStackTrace();
		}
	}
	
	public static String getValue(String key){
		String value = prop.getProperty(key);
		if(value==null){
			return "";
		}else{
			return value;
		}
	}
	
}
