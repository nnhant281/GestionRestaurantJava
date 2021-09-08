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
            new monDialogue("Veuillez saisir la catégorie !", monDialogue.ERROR_DIALOG);
            return false;
        }
        for (String cate : listeCategorie) {
            if (cate == libelle) {
            	new monDialogue("Cette catégorie existe déjà !", monDialogue.ERROR_DIALOG);
                return false;
            }
        }
        categorie cate = new categorie(libelle);
        if (categorie_DAO.ajouteCategorie(cate)) {
            new monDialogue("La catégorie est ajoutée !", monDialogue.SUCCESS_DIALOG);
            return true;
        } else {
            new monDialogue("Il y a un erreur. L'ajoute de catégorie est échue !", monDialogue.ERROR_DIALOG);
            return false;
        }
    }

    public boolean suppressionCategorie(String libelle) {
        if (libelle.trim().equals("")) {
        	 new monDialogue("Veuillez saisir la catégorie à supprimer !", monDialogue.ERROR_DIALOG);
            return false;
        }
        if (categorie_DAO.suppressionCategorie(libelle)) {
        	new monDialogue("La catégorie est supprimée !", monDialogue.SUCCESS_DIALOG);
            return true;
        } else {
            new monDialogue("Il y a un erreur. La suppression est échue !", monDialogue.ERROR_DIALOG);
            return false;
        }
    }

    
}
