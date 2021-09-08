package Custom;

public class isNumeric {
	
	public static boolean verifier(String str) {
		
		return str.matches("-?\\d+(.\\d+)?");
		
	}		
}
