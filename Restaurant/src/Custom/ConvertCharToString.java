package Custom;

import javax.swing.JPasswordField;

public class ConvertCharToString {
	
	public static String convert(JPasswordField mdp) {		
		String str = new String(mdp.getPassword());		
		return str;	
	}

}
