package BUS;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;

import Custom.monDialogue;
import DAO.articleCommande_DAO;
import DTO.articleCommande;
import DTO.commande;


public class articleCommande_BUS {

	private ArrayList<articleCommande> listeArticleCommande = null;
	private articleCommande_DAO articleDAO = new articleCommande_DAO();
	
	public ArrayList<articleCommande> getListeArticleParTable(int idTable){
		listeArticleCommande = null;
		listeArticleCommande = articleDAO.getListeArticleParTable(idTable);
        return listeArticleCommande;
	}
}
