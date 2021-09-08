package BUS;

import java.util.ArrayList;

import javax.swing.JOptionPane;

import Custom.monDialogue;
import DAO.compte_DAO;
import DTO.compteModele;

public class compte_BUS {
	
	private ArrayList<compteModele> listeCompte = null;
	private ArrayList<Integer> listeIDRH = null;
	private ArrayList<Integer> listeHabilitation = null;
	
	private compte_DAO compteDAO = new compte_DAO();

    public compte_BUS() {
        lireListeCompte();
    }

    public void lireListeCompte() {
    	listeCompte = compteDAO.initierListeCompte();
    }
    
    public ArrayList<compteModele> getNouveauListeCompte() {
        listeCompte = null;
        lireListeCompte();
        return listeCompte;
    }
    
    public void lireListeIDRH() {
    	listeIDRH = compteDAO.initierListeIDRH();
    }
    
    public ArrayList<Integer> getNouveauListeIDRH() {
        listeIDRH = null;
        lireListeIDRH();
        return listeIDRH;
    }
    
    public void lireListeHabilitation() {
    	listeHabilitation = compteDAO.initierListeHabilitation();
    }
    
    public ArrayList<Integer> getNouveauListeHabilitation() {
        listeHabilitation = null;
        lireListeHabilitation();
        return listeHabilitation;
    }
    
    //AJOUTER NOUVEAU COMPTE

	public boolean ajouterCompte (int idrh, String identifiant, String mdp, int habilitation){
	
		if (idrh < 1 || habilitation < 1) {
			
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return false;
		}	

		else {
			
			compteModele compte = new compteModele(idrh, identifiant, mdp, habilitation);
			
			if (compteDAO.ajouteCompte(compte)) {
				new monDialogue("Compte ajouté !", monDialogue.SUCCESS_DIALOG);
	            return true;
			}
			else {
				new monDialogue("Il y a un erreur. L'employé n'est pas ajouté !", monDialogue.ERROR_DIALOG);
	            return false;
			}  
	           
		}
	}
	
	//SUPPRIMER EMPLOYE
	
	public boolean supprimerCompte (int idrh) {
		
		if (idrh >=1) {
			return compteDAO.supprimerCompte(idrh);		
		}		
		return false;
	}
	
	//MODIFIER EMPLOYE
	public boolean modifierCompte(int idrh, int habilitation) {
		
		if (idrh < 1 || habilitation < 1) {			
			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
			return false;
		}
	
		else {
			compteModele compte = new compteModele(idrh, habilitation);
			
			if (compteDAO.modifierCompte(idrh, compte)) {
				new monDialogue("Compte modifié", monDialogue.SUCCESS_DIALOG);
				return true;
				
			}
			else {
				new monDialogue("Il y a un erreur. Le compte n'a pas été modifié !", monDialogue.ERROR_DIALOG);
	            return false;
	        }
	      
		}		
	}
		
	public ArrayList<compteModele> rechercherCompte(String identifiantRecherche) {
		
		ArrayList<compteModele> listeCompteTrouve;
		
		if (identifiantRecherche.equals("Recherche par identifiant...") || identifiantRecherche.equals("")) {
			new monDialogue("Merci de renseigner un identifiant!!!", monDialogue.ERROR_DIALOG);
			return null;
		}		
		else {
			listeCompteTrouve = compteDAO.rechercherCompte(identifiantRecherche);
			return listeCompteTrouve;	
		}				
	}
	
	public boolean compteAjoute(int idrh) {
		
		boolean trouve = false;
		
		if (idrh > 0) {
			trouve = compteDAO.compteAjoute(idrh);
		}
		return trouve;
		
	}
}
