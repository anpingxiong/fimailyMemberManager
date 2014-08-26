package util;

import java.io.FileInputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.Properties;

public class ReadProperties {
   public  static  String   getValue(String str){
	  String  value=null;
	   Properties  pro   = new Properties();
	  InputStream   ip   = ReadProperties.class.getClassLoader().getResourceAsStream("DBConnectionConfigue.properties");
	  try {
		pro.load(ip);
      	value=	pro.getProperty(str);
	   } catch (IOException e) {
	 	e.printStackTrace();
	}finally{
		try {
			ip.close();
		} catch (IOException e) {
		 	e.printStackTrace();
		}
	} 
	  return  value;
   }
}
