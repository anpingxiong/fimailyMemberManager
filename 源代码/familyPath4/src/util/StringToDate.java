package util;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.Date;

 

public class StringToDate {
	 public   static    Date parseStringToDate(String date) throws Exception{     
		     Date result=null;
              String parse=date;
                parse=parse.replaceFirst("^[0-9]{4}([^0-9]?)", "yyyy$1");
              parse=parse.replaceFirst("^[0-9]{2}([^0-9]?)", "yy$1");
            parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1MM$2");
           parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}( ?)", "$1dd$2");
               parse=parse.replaceFirst("( )[0-9]{1,2}([^0-9]?)", "$1HH$2");
             parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1mm$2");
	       parse=parse.replaceFirst("([^0-9]?)[0-9]{1,2}([^0-9]?)", "$1ss$2");
	       DateFormat format=new SimpleDateFormat(parse);
	   result=format.parse(date);
      return result; 
        }
	        
}
