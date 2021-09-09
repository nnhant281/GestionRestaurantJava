package BUS;

import java.util.ArrayList;
import java.util.Vector;

import DAO.categorie_DAO;
import DAO.chiffreAffaireMoisAnModele_DAO;
import DAO.classement_DAO;
import DAO.compte_DAO;
import DAO.produit_DAO;
import DTO.chiffreAffaireMoisAnModele;
import DTO.classementModele;
import DTO.compteModele;

public class chiffreAffaire_BUS {
	
	@SuppressWarnings("unused")
	private String categorie;

	private classement_DAO chiffreAffaireDAO = new classement_DAO();
	private categorie_DAO categorie_DAO = new categorie_DAO();
	private ArrayList<classementModele> listeClassement = null;
	private ArrayList<String> listeCategorie = null;

    public chiffreAffaire_BUS() {
    	
    }
    
    public void lireListeClassement(String categorie ) {
    	listeClassement = chiffreAffaireDAO.InitierListeClassement(categorie);
    }
    
    public ArrayList<classementModele> getNouveauListeClassement(String categorie) {
        listeClassement = null;
        lireListeClassement(categorie);
        return listeClassement;
    }
      
    public void lireListeCategorie() {
        this.listeCategorie = categorie_DAO.getListeCategorie();
    }

    public ArrayList<String> getListeCategorie() {
        listeCategorie = null ;
        lireListeCategorie();        
        return this.listeCategorie;
    }
}
