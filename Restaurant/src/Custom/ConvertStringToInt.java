package Custom;

public class ConvertStringToInt {
	
	public static int convert(String str) {
		int number = -1;
	
		if (str.length() != 0) {		
			try {
				number = Integer.parseInt(str);
				System.out.println(number);				
				return number;
			} catch (NumberFormatException e){
				e.printStackTrace();
				System.out.println("ConvertStringToInt-Exception: " + e.getMessage());
			}
			return number;	
		}		
		return number;		
	}
}
