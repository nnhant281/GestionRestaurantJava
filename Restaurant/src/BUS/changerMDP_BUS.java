package BUS;

import javax.swing.JPasswordField;
import javax.swing.JTextField;
import Custom.ConvertCharToString;
import Custom.monDialogue;
import DAO.changerMDP_DAO;

public class changerMDP_BUS {
	
	private changerMDP_DAO changerMDPDAO = new changerMDP_DAO();	
	
	public boolean changerMDP(JTextField txtIdentifiant, JPasswordField txtAncienMDP, JPasswordField txtNouveauMDP, JPasswordField txtConfirmeMDP) {	
			
		String identifiant = txtIdentifiant.getText();		
		String ancienMDP = ConvertCharToString.convert(txtAncienMDP);		
		String nouveauMDP = ConvertCharToString.convert(txtNouveauMDP);		
		String confirmeMDP = ConvertCharToString.convert(txtConfirmeMDP);	
		
		//VERIFIER SI TOUS LES CHAMPS SONT REMPLIS
		if (identifiant.length() == 0 ||ancienMDP.length() == 0 || nouveauMDP.length() == 0 || confirmeMDP.length() == 0) {
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return false;
		}
		
		else if (ancienMDP.equals(nouveauMDP)) {
			new monDialogue("Le nouveau mot de passe doit être différent de l'ancien", monDialogue.ERROR_DIALOG);
			return false;
			
		}
		else if (!nouveauMDP.equals(confirmeMDP)) {
			new monDialogue("Les nouveaux mots de passe doivent être identique!!!", monDialogue.ERROR_DIALOG);
			return false;
		}
		else {
			if (changerMDPDAO.changerMDP(identifiant, confirmeMDP)) {
				return true;				
			}
			else {
				return false;
			}
		}		
	}
}
