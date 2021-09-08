package Custom;

import java.text.DateFormat;
import java.text.ParseException;
import java.text.SimpleDateFormat;
import java.util.Date;

public class ConvertStringToDate {
	
	public Date convert(String str) {
		
		Date dateFormat = null;
		
		DateFormat df = new SimpleDateFormat("yyyy/MM/dd"); 
	
		try {
		    dateFormat = df.parse(str);
		    return dateFormat;

		} catch (ParseException e) {
		    e.printStackTrace();
		    System.out.println("ConvertStringToDate-Exception: " + e.getMessage());
		}
		return dateFormat;
	}
}
