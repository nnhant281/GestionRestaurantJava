package BUS;

import java.util.ArrayList;
import java.util.Date;

import Custom.isNumeric;
import Custom.monDialogue;
import DAO.employe_DAO;
import DAO.typeContrat_DAO;
import DTO.contratModele;
import DTO.employeModele;

public class typeContrat_BUS {
	
	private ArrayList<contratModele> listeTypeContrat = null;
    private typeContrat_DAO typeContratDAO = new typeContrat_DAO();

    public typeContrat_BUS() {
        lireListeTypeContrat();
    }

    public void lireListeTypeContrat() {
    	listeTypeContrat = typeContratDAO.InitierListeTypeContrat();
    }
    
    public ArrayList<contratModele> getNouveauListeTypeContrat() {
        listeTypeContrat = null;
        lireListeTypeContrat();
        return listeTypeContrat;
    }
    
    //AJOUTER NOUVEAU TYPE CONTRAT

   	public boolean ajouterTypeContrat (String nouveauTypeContrat){
   		
   		boolean doublon = false;   		
   		listeTypeContrat = getNouveauListeTypeContrat();
   		
   		if (nouveauTypeContrat.length() == 0) {  			
   			new monDialogue("Merci de renseigner tous les champs nécessaires!!!", monDialogue.ERROR_DIALOG);
   			return false;
   		}  		
   		for (contratModele typeContrat : listeTypeContrat) {
	   			if (typeContrat.getTypeContrat().toUpperCase().equals(nouveauTypeContrat.toUpperCase())) {
	   				doublon = true;
	   				break;
	   			}
   		}	   			
   		
	   	if (doublon) {
	   		new monDialogue("Ce type de contrat existe déjà!!!", monDialogue.ERROR_DIALOG);
	   		return false;
	   	}
	   	else {
	   		contratModele contrat = new contratModele(nouveauTypeContrat);
	   		typeContratDAO.ajouteTypeContrat(contrat);
	   		return true;
	   	}	   		  	  		
   	}
   	
   	//SUPPRIMER UN TYPE CONTRAT
	
  	public boolean supprimerTypeContrat (String typeContrat) {
  		
  		boolean trouve = false;
  		
  		
  		if (typeContrat.length() ==0) {
  			new monDialogue("Merci de choisir un type contrat  à supprimer!!!", monDialogue.ERROR_DIALOG); 
  			return trouve;
  		}
  		else {
  			if (typeContratExiste(typeContrat)) {
  	  			return false;
  	  		}
  	  		else {
  	  			return (typeContratDAO.supprimerTypeContrat(typeContrat));
  	  		}			
  		}
  		
  	}
  	
  	//AVANT DE SUPPRIMER, VERIFIER SI CE TYPE DE CONTRAT EST ASSOCIE A AU MOINS UN CONSEILLER
  	
  	public boolean typeContratExiste(String typeContrat) {
		
  		boolean trouve = false;
  						
		if (typeContratDAO.typeContratExiste(typeContrat)) {
			new monDialogue("Impossible de supprimer car ce type de contrat est associé à certains employés!!!", monDialogue.ERROR_DIALOG);
			trouve = true;
		}
		return trouve;
	}  	
}
