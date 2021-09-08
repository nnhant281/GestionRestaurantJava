package BUS;

import java.util.ArrayList;

import Custom.monDialogue;
import DAO.categorie_DAO;
import DTO.categorie;

public class categorie_BUS {
	private categorie_DAO categorie_DAO = new categorie_DAO();
    private ArrayList<String> listeCategorie = null;
    
    public categorie_BUS() {
        lireListeCategorie();
    }

    public void lireListeCategorie() {
        this.listeCategorie = categorie_DAO.getListeCategorie();
    }

    public ArrayList<String> getListeCategorie() {
        listeCategorie = null ;
        lireListeCategorie();
        
        return this.listeCategorie;
    }
    

    public boolean ajouteCategorie(String libelle) {
        if (libelle.trim().equals("")) {
            new monDialogue("Veuillez saisir la cat�gorie !", monDialogue.ERROR_DIALOG);
            return false;
        }
        for (String cate : listeCategorie) {
            if (cate == libelle) {
            	new monDialogue("Cette cat�gorie existe d�j� !", monDialogue.ERROR_DIALOG);
                return false;
            }
        }
        categorie cate = new categorie(libelle);
        if (categorie_DAO.ajouteCategorie(cate)) {
            new monDialogue("La cat�gorie est ajout�e !", monDialogue.SUCCESS_DIALOG);
            return true;
        } else {
            new monDialogue("Il y a un erreur. L'ajoute de cat�gorie est �chue !", monDialogue.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suppressionCategorie(String libelle) {
        if (libelle.trim().equals("")) {
        	 new monDialogue("Veuillez saisir la cat�gorie � supprimer !", monDialogue.ERROR_DIALOG);
            return false;
        }
        if (categorie_DAO.suppressionCategorie(libelle)) {
        	new monDialogue("La cat�gorie est supprim�e !", monDialogue.SUCCESS_DIALOG);
            return true;
        } else {
            new monDialogue("Il y a un erreur. La suppression est �chue !", monDialogue.ERROR_DIALOG);
            return false;
        }
    }

    
}
