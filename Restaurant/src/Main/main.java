package Main;

import GUI.login_GUI;

public class main {

	@SuppressWarnings("unused")
	public static void main(String[] args) {
		changeLNF("Nimbus");
		login_GUI loginGUI = new login_GUI();

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
