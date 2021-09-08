package BUS;

import java.util.ArrayList;
import java.util.Date;
import Custom.isNumeric;
import Custom.monDialogue;
import DAO.employe_DAO;
import DTO.employeModele;

public class employe_BUS {
	
	private ArrayList<employeModele> listeEmploye = null;
    private employe_DAO employeDAO = new employe_DAO();

    public employe_BUS() {
        lireListeEmploye();
    }

    public void lireListeEmploye() {
    	listeEmploye = employeDAO.InitierListeEmploye();
    }
    
    public ArrayList<employeModele> getNouveauListeEmploye() {
        listeEmploye = null;
        lireListeEmploye();
        return listeEmploye;
    }
        
    //AJOUTER NOUVEAU EMPLOYE

	public boolean ajouterEmploye (String nom, String prenom ,Date dateNaissance, String choixTypeContrat,
								Date dateDebut, Date dateFin, String dureeHebdo, String choixEmploi,
								String adresse, String tel){
	
		if (dateNaissance == null || dateDebut == null
				|| nom.length()==0 || prenom.length()==0 || choixEmploi.length()==0
				|| adresse.length()==0 || tel.length()==0 && dureeHebdo.length() == 0
				|| (!choixTypeContrat.equals("CDI") && choixTypeContrat.length() == 0 )) {
			
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return false;
		}	
		else if (!isNumeric.verifier(dureeHebdo)){
			 new monDialogue("Durée hebdomadaire doit être un chiffre!!!", monDialogue.ERROR_DIALOG);
			 return false;
		}
		
		else if (!choixTypeContrat.equals("CDI") && dateFin.before(dateDebut) ) {
			new monDialogue("Date fin doit être supérieur que date début", monDialogue.ERROR_DIALOG);
			return false;						
		}
		else {
			employeModele employe = new employeModele(nom, prenom, dateNaissance, adresse, tel, 
        			choixTypeContrat, dateDebut, dateFin, dureeHebdo, choixEmploi);

			if (employeDAO.ajouteEmploye(employe)) {
               new monDialogue("L'employé est ajouté !", monDialogue.SUCCESS_DIALOG);
               return true;
			} 
			else {
               new monDialogue("Il y a un erreur. L'employé n'est pas ajouté !", monDialogue.ERROR_DIALOG);
               return false;
            }			
		}	           
	}
	
	//SUPPRIMER EMPLOYE
	
	public boolean supprimerEmploye (int idrh) {
		
		if (idrh >=0) {
			return employeDAO.supprimerEmploye(idrh);		
		}		
		return false;
	}
	
	//MODIFIER EMPLOYE
	public boolean modifierEmploye(int idrh,String nom, String prenom ,Date dateNaissance, String choixTypeContrat,
			Date dateDebut, Date dateFin, String dureeHebdo, String choixEmploi,
			String adresse, String tel) {
		
		if (dateNaissance == null || dateDebut == null
				|| nom.length()==0 || prenom.length()==0 || choixEmploi.length()==0
				|| adresse.length()==0 || tel.length()==0 && dureeHebdo.length() == 0
				|| (!choixTypeContrat.equals("CDI") && choixTypeContrat.length() == 0 )) {
			
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return false;
		}
		
		else if (!isNumeric.verifier(dureeHebdo)){
			 new monDialogue("Durée hebdomadaire doit être un chiffre!!!", monDialogue.ERROR_DIALOG);
			 return false;
		}
		
		else if (dateFin.before(dateDebut)){
			new monDialogue("Date fin doit être supérieur que date début", monDialogue.ERROR_DIALOG);
			return false;	
		}
		
		else if (idrh < 0) {
			return false;
		}		
		else {
			if (choixTypeContrat.equals("CDI")) {
				dateFin = null;
			}	
	           employeModele employe = new employeModele(nom, prenom, dateNaissance, adresse, tel, 
	        			choixTypeContrat, dateDebut, dateFin, dureeHebdo, choixEmploi);

	           if (employeDAO.modifierEmploye(idrh, employe)) {
	               new monDialogue("L'employé a été modifié !", monDialogue.SUCCESS_DIALOG);
	               return true;
	           } 
	           else {
	               new monDialogue("Il y a un erreur. L'employé n'a pas été modifié !", monDialogue.ERROR_DIALOG);
	               return false;
	            }
	        }		
	}
	
	public ArrayList<employeModele> rechercherEmploye(String nomRecherche) {
		
		ArrayList<employeModele> listeEmployeTrouve;
		
		if (nomRecherche.equals("Recherche par nom...") || nomRecherche.equals("")) {
			new monDialogue("Merci de renseigner un nom!!!", monDialogue.ERROR_DIALOG);
			return null;
		}		
		else {
			listeEmployeTrouve = employeDAO.rechercherEmploye(nomRecherche);
			return listeEmployeTrouve;	
		}				
	}
}
