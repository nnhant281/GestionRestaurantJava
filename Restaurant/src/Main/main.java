package Main;

import java.nio.charset.Charset;
import java.nio.charset.StandardCharsets;
import java.util.Locale;

import GUI.login_GUI;

public class main {
	
	

	public static void main(String[] args) {
		 System.setProperty("user.language", "fr");
		 System.setProperty("user.language.display", "fr");
		 System.setProperty("user.language.format", "fr");
		 System.setProperty("user.country.display", "FR");
		 Locale.setDefault(new Locale("fr", "FR"));
		 StandardCharsets.UTF_8.name();
		changeLNF("Nimbus");
		login_GUI loginGUI = new login_GUI();
		
		/*
		ModuleEmployee employee = new ModuleEmployee();		
		
		*/

	}
	
	 public static void changeLNF(String nameLNF) {
        try {
            for (javax.swing.UIManager.LookAndFeelInfo info : javax.swing.UIManager.getInstalledLookAndFeels()) {
                if (nameLNF.equals(info.getName())) {
                    javax.swing.UIManager.setLookAndFeel(info.getClassName());
                    break;
                }
            }
        } catch (ClassNotFoundException | InstantiationException | IllegalAccessException | javax.swing.UnsupportedLookAndFeelException ex) {
        }
      
    }
}
