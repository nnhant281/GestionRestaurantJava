package Custom;


public class ConvertTime {
	
	public static java.sql.Date convert(java.util.Date date){
		
		java.sql.Date maDate = null;
		
		if (date != null) {
			maDate = new java.sql.Date(date.getTime());
			return maDate;	
		}
		
		return maDate;
	}

}
