package BUS;

import Custom.monDialogue;
import DAO.login_DAO;
import DTO.compteModele;

public class login_BUS {
	
	private login_DAO loginDAO = new login_DAO();	
	private compteModele compte = new compteModele();
	
	public compteModele authentifier(String identifiantRecherche, String mdpRecherche) {		
		
		if (identifiantRecherche.length() == 0 || mdpRecherche.length() == 0) {
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return compte;
		}		
		else {			
			compte= loginDAO.authentifier(identifiantRecherche, mdpRecherche);	
			
			if (compte.getHabilitation() == 0) {
				new monDialogue("Identifiant ou mot de passe incorrect!!!", monDialogue.ERROR_DIALOG);		
			}
			return compte;
		}
	}
}
