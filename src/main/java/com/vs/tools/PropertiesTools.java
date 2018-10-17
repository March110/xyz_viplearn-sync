package com.vs.tools;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Iterator;
import java.util.Map;
import java.util.Properties;

public class PropertiesTools {

	/**
	 * 类说明 :获取对应properties中的key值
	 * @author  joker
	 * 创建时间：2013-7-3
	 */
	public static String getValue(String propertiesName, String key){
		
		if("".equals(propertiesName) && propertiesName == null){
			return "the propertiesName is null";
		}
		if("".equals(key) && key == null){
			return "the key is null";
		}
		
		Properties properties = new Properties();
		InputStream inputStream = PropertiesTools.class.getResourceAsStream("/" + propertiesName + ".properties");
		
		try {
			properties.load(inputStream);
		} catch (IOException e) {
			// TODO Auto-generated catch block
			System.out.println("properties load error");
			e.printStackTrace();
		}
		
		return properties.getProperty(key);
	}

	/**
	 * 类说明 :写入对应properties中的key值
	 * @author  joker
	 * 创建时间：2013-7-3
	 */
	public void setValue(String propertiesName, Map<String, Object> keyValue){
		try {
			if("".equals(propertiesName) && propertiesName == null){
				return;
			}
			if(keyValue == null){
				return;
			}

			Properties properties = new Properties();
			String temp = getClass().getResource("/").toURI() + propertiesName + ".properties";
			String url = temp.replace("file:/", "");
			FileOutputStream oFile = new FileOutputStream(url, false);
			Iterator iterator = keyValue.entrySet().iterator();
			while (iterator.hasNext()){
				Map.Entry entry = (Map.Entry)iterator.next();
				properties.setProperty(entry.getKey().toString(), entry.getValue().toString());
			}
			properties.store(oFile, "write");
			oFile.close();
		}catch (Exception e){
			e.printStackTrace();
		}
	}


	
	public static void main(String[] args){
//		PropertiesTools pt = new PropertiesTools();
//		String stats = pt.getValue("location", "midAtlantic");
//		pt.setValue("weChat", "token", "123");

		String stats = PropertiesTools.getValue("mongodb", "mongo.dbname");
		System.out.println(stats);
	}
}
