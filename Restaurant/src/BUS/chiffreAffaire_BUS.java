package BUS;

import java.util.ArrayList;
import java.util.Vector;

import DAO.categorie_DAO;
import DAO.chiffreAffaire_DAO;
import DAO.compte_DAO;
import DAO.produit_DAO;
import DTO.classementModele;
import DTO.compteModele;

public class chiffreAffaire_BUS {
	
	@SuppressWarnings("unused")
	private ArrayList<classementModele> listeClassement = null;
	private String categorie;

	private chiffreAffaire_DAO chiffreAffaireDAO = new chiffreAffaire_DAO();
	private categorie_DAO categorie_DAO = new categorie_DAO();
	

    public chiffreAffaire_BUS(String categorie) {
    	this.categorie = categorie;
        lireListeClassement(categorie);
        
    }
    
    public void lireListeClassement(String categorie) {
    	listeClassement = chiffreAffaireDAO.InitierListeClassement(categorie);
    }
    
    public ArrayList<classementModele> getNouveauListeClassement() {
        listeClassement = null;
        lireListeClassement(categorie);
        return listeClassement;
    }
    
    private ArrayList<String> listeCategorie = null;
  
    public void lireListeCategorie() {
        this.listeCategorie = categorie_DAO.getListeCategorie();
    }

    public ArrayList<String> getListeCategorie() {
        listeCategorie = null ;
        lireListeCategorie();
        
        return this.listeCategorie;
    }



}
